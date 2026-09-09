package com.example.library.service.impl;

import com.example.library.domain.Book;
import com.example.library.domain.dto.BookRequest;
import com.example.library.domain.dto.BookResponse;
import com.example.library.exception.BookAlreadyExistsException;
import com.example.library.exception.BookNotFoundException;
import com.example.library.mapper.BookMapper;
import com.example.library.repository.BookRepository;
import com.example.library.service.BookService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final BookMapper bookMapper;

    public BookServiceImpl(BookRepository bookRepository, BookMapper bookMapper) {
        this.bookRepository = bookRepository;
        this.bookMapper = bookMapper;
    }

    @Override
    public List<BookResponse> findAll() {
        return bookRepository.findAll().stream().map(BookResponse.class::cast).toList();
    }

    @Override
    public BookResponse findById(Long id) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() ->
                        new BookNotFoundException("Book " + id + " not found"));
        return bookMapper.toResponse(book);
    }

    @Override
    public BookResponse save(BookRequest request) {
        bookRepository.findByIsbn(request.isbn())
                .ifPresent(book -> {
                    throw new BookAlreadyExistsException(
                            "Book with ISBN " + request.isbn() + " already exists."
                    );
                });

        Book book = bookMapper.toBook(request);
        book = bookRepository.save(book);

        if(book == null) throw new BookAlreadyExistsException("Book with ISBN " + request.isbn() + " already exists.";
        
        return bookMapper.toResponse(book);
    }

    @Override
    public void delete(Long id) {
        bookRepository.deleteById(id);
    }
}
