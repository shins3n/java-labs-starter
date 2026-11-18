package edu.course.lab06;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class BoxTest {

    @Test
    void boxWorksWithString() {
        Box<String> stringBox = new Box<>("Hello");
        assertEquals("Hello", stringBox.get());

        stringBox.set("World");
        assertEquals("World", stringBox.get());
    }

    @Test
    void boxWorksWithInteger() {
        Box<Integer> intBox = new Box<>(123);
        assertEquals(123, intBox.get());

        intBox.set(456);
        assertEquals(456, intBox.get());
    }

    // Пример некомпилируемого кода
    // Box<String> box = new Box<>("Text");
    // box.set(123); 
}