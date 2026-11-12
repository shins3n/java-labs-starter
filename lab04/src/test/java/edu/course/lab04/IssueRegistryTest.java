package edu.course.lab04;

import org.junit.jupiter.api.Test;
import java.util.HashMap;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.*;

class IssueRegistryTest {

    @Test
    void logicalEqualKeysFindSameValue() {
        IssueRegistry registry = new IssueRegistry();
        registry.addOrReplace(new IssueId("PROJ", 1), new ProjectTask("Task 1"));

        ProjectTask found = registry.get(new IssueId("PROJ", 1));
        assertNotNull(found);
        assertEquals("Task 1", found.getTitle());
    }

    @Test
    void addingSameKeyReplacesValueAndDoesNotIncreaseSize() {
        IssueRegistry registry = new IssueRegistry();
        registry.addOrReplace(new IssueId("PROJ", 1), new ProjectTask("Old Task"));
        assertEquals(1, registry.size());

        registry.addOrReplace(new IssueId("PROJ", 1), new ProjectTask("New Task"));

        assertEquals(1, registry.size());
        assertEquals("New Task", registry.get(new IssueId("PROJ", 1)).getTitle());
    }

    @Test
    void collisionDoesNotMixValues() {
        class CollisionKey {
            private final String value;
            CollisionKey(String value) { this.value = value; }

            @Override
            public boolean equals(Object o) {
                if (this == o) return true;
                if (o == null || getClass() != o.getClass()) return false;
                return value.equals(((CollisionKey) o).value);
            }

            @Override
            public int hashCode() { return 42; }
        }

        Map<CollisionKey, String> map = new HashMap<>();
        CollisionKey key1 = new CollisionKey("A");
        CollisionKey key2 = new CollisionKey("B");

        map.put(key1, "Value A");
        map.put(key2, "Value B");

        assertEquals(2, map.size());
        assertEquals("Value A", map.get(key1));
        assertEquals("Value B", map.get(key2));
    }

    @Test
    void comparisonWithNullAndOtherTypeReturnsFalse() {
        IssueId id = new IssueId("PROJ", 1);
        assertNotEquals(id, null);
        assertNotEquals(id, "PROJ-1");
    }

    @Test
    void mutableKeyProblemExperiment() {
        class MutableKey {
            String name;
            MutableKey(String name) { this.name = name; }
            @Override
            public boolean equals(Object o) {
                if (this == o) return true;
                if (o == null || getClass() != o.getClass()) return false;
                return name.equals(((MutableKey) o).name);
            }
            @Override
            public int hashCode() { return name.hashCode(); }
        }

        Map<MutableKey, String> map = new HashMap<>();
        MutableKey key = new MutableKey("OldName");
        map.put(key, "Value");

        key.name = "NewName";

        assertNull(map.get(key));
    }
}