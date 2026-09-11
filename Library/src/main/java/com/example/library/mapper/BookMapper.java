package com.example.library.mapper;

import com.example.library.domain.Book;
import com.example.library.domain.dto.BookResponse;

public final class BookMapper {
    private BookMapper() { }

    public static BookResponse toResponse(Book book) {
        return new BookResponse(book.id(), book.title(), book.author());
    }
}
