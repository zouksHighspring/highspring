package com.example.library.repository;

import com.example.library.domain.Book;

import java.util.List;
import java.util.Optional;

public interface BookRepository {
    List<Book> findAll();

    Optional<Book> findById(Long id);

    Optional<Book> findByIsbn(String isbn);

    Book save(Book book);

    void deleteById(Long id);
}
