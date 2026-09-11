package com.example.library.service;

import com.example.library.domain.dto.BookRequest;
import com.example.library.domain.dto.BookResponse;
import com.example.library.exception.BookNotFoundException;
import com.example.library.mapper.BookMapper;
import com.example.library.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {
    private final BookRepository repository;

    public BookService(BookRepository repository) {
        this.repository = repository;
    }

    public List<BookResponse> findAll() {
        return repository.findAll().stream().map(BookMapper::toResponse).toList();
    }

    public BookResponse findById(Long id) {
        return repository.findById(id).map(BookMapper::toResponse)
                .orElseThrow(() -> new BookNotFoundException(id));
    }

    public BookResponse create(BookRequest request) {
        return BookMapper.toResponse(repository.create(
                request.title().strip(), request.author().strip()));
    }
}
