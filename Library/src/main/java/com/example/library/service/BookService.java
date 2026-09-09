package com.example.library.service;

import com.example.library.domain.dto.BookRequest;
import com.example.library.domain.dto.BookResponse;

import java.util.List;

public interface BookService {
    List<BookResponse> findAll();

    BookResponse findById(Long id);

    BookResponse save(BookRequest request);

    void delete(Long id);
}
