package edu.course.lab06;

import org.junit.jupiter.api.Test;
import java.util.NoSuchElementException;
import static org.junit.jupiter.api.Assertions.*;

class StackTest {

    @Test
    void newStackIsEmpty() {
        Stack<String> stack = new Stack<>();
        assertTrue(stack.isEmpty());
        assertEquals(0, stack.size());
    }

    @Test
    void pushAndPeekWorks() {
        Stack<Integer> stack = new Stack<>();
        stack.push(10);
        stack.push(20);

        assertEquals(2, stack.size());
        assertEquals(20, stack.peek()); // Последний добавленный
        assertEquals(2, stack.size());  // Peek не удаляет
    }

    @Test
    void popWorks() {
        Stack<String> stack = new Stack<>();
        stack.push("A");
        stack.push("B");

        assertEquals("B", stack.pop());
        assertEquals("A", stack.pop());
        assertTrue(stack.isEmpty());
    }

    @Test
    void pushNullThrowsException() {
        Stack<String> stack = new Stack<>();
        assertThrows(IllegalArgumentException.class, () -> stack.push(null));
    }

    @Test
    void popFromEmptyStackThrowsException() {
        Stack<String> stack = new Stack<>();
        assertThrows(NoSuchElementException.class, () -> stack.pop());
    }

    @Test
    void peekFromEmptyStackThrowsException() {
        Stack<String> stack = new Stack<>();
        assertThrows(NoSuchElementException.class, () -> stack.peek());
    }
}