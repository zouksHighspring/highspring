package com.example.library.service;

import com.example.library.domain.*;
import com.example.library.domain.enumeration.BookCategory;
import com.example.library.domain.enumeration.BookStatus;
import com.example.library.exception.BookAlreadyBorrowedException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

class BookServiceTest {

    private BookService bookService;
    private Author author;
    private Reader reader;
    private Book book;

    @BeforeEach
    void setUp() {
        bookService = new BookService();

        author = Author.builder()
                .firstName("Robert")
                .lastName("Martin")
                .build();

        reader = Reader.builder()
                .firstName("Modeste")
                .lastName("Highspring")
                .build();

        book = Book.builder()
                .isbn("12345")
                .title("Clean Code")
                .author(author)
                .category(BookCategory.TECHNOLOGY)
                .status(BookStatus.AVAILABLE)
                .pages(100).price(100).build();
    }

    @Test
    void shouldAddAndFindBookByIsbn() {
        // Given
        bookService.addBook(book);

        // When
        Optional<Book> found = bookService.findByIsbn("12345");

        // Then
        assertThat(found).isPresent();
        assertThat(found.get().getTitle()).isEqualTo("Clean Code");
    }

    @Test
    void shouldReturnEmptyWhenIsbnNotFound() {
        // When
        Optional<Book> found = bookService.findByIsbn("99999");

        // Then
        assertThat(found).isEmpty();
    }

    @Test
    void shouldFindByCategory() {
        // Given
        bookService.addBook(book);

        // When
        List<Book> techBooks = bookService.findByCategory(BookCategory.TECHNOLOGY);

        // Then
        assertThat(techBooks).hasSize(1);
        assertThat(techBooks.getFirst().getCategory()).isEqualTo(BookCategory.TECHNOLOGY);
    }

    @Test
    void shouldFindByStatus() {
        // Given
        bookService.addBook(book);

        // When
        List<Book> availBooks = bookService.findByStatus(BookStatus.AVAILABLE);

        // Then
        assertThat(availBooks).hasSize(1);
        assertThat(availBooks.getFirst().getStatus()).isEqualTo(BookStatus.AVAILABLE);
    }

    @Test
    void shouldCountBooksByAuthor() {
        // Given
        bookService.addBook(book);

        // When
        long count = bookService.countBooksByAuthor(author);

        // Then
        assertThat(count).isEqualTo(1);
    }

    @Test
    void shouldSuccessfullyBorrowBook() {
        // Given
        bookService.addBook(book);

        // When
        bookService.borrowBook(reader, book);

        // Then
        assertThat(book.getStatus()).isEqualTo(BookStatus.BORROWED);
        assertThat(reader.getLoans()).hasSize(1);
        assertThat(reader.getLoans().getFirst().getBook()).isEqualTo(book);
    }

    @Test
    void shouldThrowExceptionWhenBookAlreadyBorrowed() {
        // Given
        book.setStatus(BookStatus.BORROWED);
        bookService.addBook(book);

        // When / Then
        assertThatThrownBy(() -> bookService.borrowBook(reader, book))
                .isInstanceOf(BookAlreadyBorrowedException.class)
                .hasMessageContaining("already borrowed");
    }

    @Test
    void shouldCountBooksByStatus() {
        // Given
        bookService.addBook(book);

        // When
        long availableCount = bookService.countBooksByStatus(BookStatus.AVAILABLE);

        // Then
        assertThat(availableCount).isEqualTo(1);
    }
}