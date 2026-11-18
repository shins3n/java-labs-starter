package edu.course.lab06;

import org.junit.jupiter.api.Test;
import java.util.List;
import java.util.Map;
import java.util.Set;
import static org.junit.jupiter.api.Assertions.*;

class AnalyticsTest {

    @Test
    void countsTasksByStatus() {
        List<Task> tasks = List.of(
                new Task("1", "backend", "READY", 5),
                new Task("2", "frontend", "READY", 3),
                new Task("3", "backend", "DONE", 8)
        );
        Analytics analytics = new Analytics();
        Map<String, Integer> counts = analytics.countByStatus(tasks);

        assertEquals(2, counts.get("READY"));
        assertEquals(1, counts.get("DONE"));
    }

    @Test
    void findsUniqueCategories() {
        List<Task> tasks = List.of(
                new Task("1", "backend", "READY", 5),
                new Task("2", "backend", "DONE", 3),
                new Task("3", "frontend", "READY", 8)
        );
        Analytics analytics = new Analytics();
        Set<String> categories = analytics.uniqueCategories(tasks);

        assertEquals(2, categories.size());
        assertTrue(categories.contains("backend"));
        assertTrue(categories.contains("frontend"));
    }

    @Test
    void sortsTasksByHoursThenId() {
        List<Task> tasks = List.of(
                new Task("B", "cat", "NEW", 5),
                new Task("A", "cat", "NEW", 5),
                new Task("C", "cat", "NEW", 2)
        );
        Analytics analytics = new Analytics();
        List<Task> sorted = analytics.sortByHoursAndId(tasks);

        assertEquals("C", sorted.get(0).getId()); // 2 часа
        assertEquals("A", sorted.get(1).getId()); // 5 часов, ID "A"
        assertEquals("B", sorted.get(2).getId()); // 5 часов, ID "B"
    }
}