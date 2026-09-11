package com.example.library.repository.impl;

import com.example.library.domain.Book;
import com.example.library.repository.BookRepository;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class BookRepositoryImpl implements BookRepository {
    private final Map<Long, Book> books = new HashMap<>();
    private long nextId = 1;

    @Override
    public synchronized List<Book> findAll() {
        return List.copyOf(books.values());
    }

    @Override
    public synchronized Optional<Book> findById(Long id) {
        return Optional.ofNullable(books.get(id));
    }

    @Override
    public synchronized Book create(String title, String author) {
        Book book = new Book(nextId++, title, author);
        books.put(book.id(), book);
        return book;
    }
}