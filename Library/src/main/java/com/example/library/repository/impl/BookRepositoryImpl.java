package com.example.library.repository.impl;

import com.example.library.domain.Book;
import com.example.library.repository.BookRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class BookRepositoryImpl implements BookRepository {
    private final Map<Long, Book> books = new ConcurrentHashMap<>();

    @Override
    public List<Book> findAll() {
        return new ArrayList<>(books.values());
    }

    @Override
    public Optional<Book> findById(Long id) {
        return Optional.ofNullable(books.get(id));
    }

    @Override
    public Optional<Book> findByIsbn(String isbn) {

        return books.values()
                .stream()
                .filter(book -> book.getIsbn().equals(isbn))
                .findFirst();
    }

    @Override
    public Book save(Book book) {
        Book book1 = books.get(book.getId());
        if (book1 == null) {
            books.put(book.getId(), book);
            return book;
        }
        return null;
    }

    @Override
    public void deleteById(Long id) {
        books.remove(id);
    }
}
