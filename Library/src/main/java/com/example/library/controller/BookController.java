package com.example.library.controller;

import com.example.library.domain.dto.BookRequest;
import com.example.library.domain.dto.BookResponse;
import com.example.library.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    @GetMapping
    public List<BookResponse> getBooks() {
        return bookService.findAll();
    }

    @GetMapping("/{id}")
    public BookResponse getBook(@PathVariable Long id){
        return bookService.findById(id);
    }

    @PostMapping
    public BookResponse createBook(@RequestBody BookRequest request){
        return bookService.save(request);
    }
}
