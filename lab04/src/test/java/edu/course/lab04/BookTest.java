package edu.course.lab04;

import org.junit.jupiter.api.Test;
import java.util.HashMap;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.*;

class BookTest {

    @Test
    void booksWithSameIsbnAreEqual() {
        Book book1 = new Book("42", "Война и мир");
        Book book2 = new Book("42", "Война и мир (другое издание)");

        assertEquals(book1, book2);
        assertEquals(book1.hashCode(), book2.hashCode());
    }

    @Test
    void booksWithDifferentIsbnAreNotEqual() {
        Book book1 = new Book("42", "Война и мир");
        Book book2 = new Book("67", "Война и мир");

        assertNotEquals(book1, book2);
    }

    @Test
    void bookWorksAsKeyInHashMap() {
        Map<Book, String> library = new HashMap<>();
        Book book1 = new Book("42", "Оригинал");
        library.put(book1, "Полка 1");

        Book book2 = new Book("42", "Копия с тем же ISBN");

        assertEquals("Полка 1", library.get(book2));
    }
}