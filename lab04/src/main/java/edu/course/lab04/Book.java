package edu.course.lab04;
import java.util.Objects;

public final class Book {
    private final String isbn;
    private final String title;
    public Book(String isbn, String title) {
        if (isbn == null || isbn.isBlank()) {
            throw new IllegalArgumentException("ISBN не может быть пустым");
        }
        if (title == null || title.isBlank()) {
            throw new IllegalArgumentException("Title не может быть пустым");
        }
        this.isbn = isbn;
        this.title = title;
    }
    public String getIsbn() {
        return isbn;
    }
    public String getTitle() {
        return title;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return Objects.equals(isbn, book.isbn);
    }
    @Override
    public int hashCode() {
        return Objects.hash(isbn);
    }
}