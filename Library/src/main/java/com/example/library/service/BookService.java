package com.example.library.service;

import com.example.library.domain.*;
import com.example.library.domain.enumeration.BookCategory;
import com.example.library.domain.enumeration.BookStatus;
import com.example.library.exception.BookAlreadyBorrowedException;

import java.time.LocalDateTime;
import java.util.*;

public class BookService {
    private final Map<String, Book> books = new HashMap<>();
    private static final int DEFAULT_BOOK_BORROWED_DAYS = 14;

    public void addBook(Book book) {
        if (Objects.nonNull(book) && book.getIsbn() != null) {
            books.put(book.getIsbn(), book);
        }
    }

    public Optional<Book> findByIsbn(String isbn) {
        return Optional.ofNullable(books.get(isbn));
    }

    public List<Book> findByCategory(BookCategory category) {

        return books.values().stream()
                .filter(Objects::nonNull)
                .filter(book -> Objects.nonNull(book.getCategory()))
                .filter(book -> book.getCategory() == category)
                .toList();
    }

    public List<Book> findByStatus(BookStatus status) {

        return books.values().stream()
                .filter(Objects::nonNull)
                .filter(book -> Objects.nonNull(book.getStatus()))
                .filter(book -> book.getStatus() == status)
                .toList();
    }

    public long countBooksByAuthor(Author author) {

        return books.values().stream()
                .filter(Objects::nonNull)
                .filter(book -> Objects.nonNull(book.getAuthor()))
                .filter(book -> book.getAuthor().equals(author))
                .count();
    }

    public long countBooksByStatus(BookStatus status) {

        return books.values().stream()
                .filter(Objects::nonNull)
                .filter(book -> Objects.nonNull(book.getStatus()))
                .filter(book -> book.getStatus().equals(status))
                .count();
    }

    public void borrowBook(Person person, Book book) {
        if (person == null || book == null) return;

        if (Objects.nonNull(book.getStatus()) && book.getStatus() == BookStatus.BORROWED) {
            throw new BookAlreadyBorrowedException("Book '" + book.getTitle() + "' is already borrowed.");
        }

        if (person instanceof Author || person instanceof Reader) {
            createLoan(person, book);
        }
    }

    private void createLoan(Person person, Book book) {
        Loan loan = Loan.builder()
                .book(book)
                .loanDate(LocalDateTime.now())
                .dueDate(LocalDateTime.now().plusDays(DEFAULT_BOOK_BORROWED_DAYS))
                .isDueDateRespected(true)
                .build();

        person.getLoans().add(loan);
        book.setStatus(BookStatus.BORROWED);
    }
}
