package com.example.library.mapper;

import com.example.library.domain.Book;
import com.example.library.domain.dto.BookRequest;
import com.example.library.domain.dto.BookResponse;
import com.example.library.domain.enumeration.BookStatus;
import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicLong;

@Component
public class BookMapper {
    private final AtomicLong idGenerator = new AtomicLong(1);

    public BookResponse toResponse(Book book){

        return new BookResponse(
                book.getId(),
                book.getTitle(),
                book.getIsbn(),
                book.getCategory()
        );
    }

    public Book toBook(BookRequest request) {

        return Book.builder()
                .id(idGenerator.getAndIncrement())
                .title(request.title())
                .isbn(request.isbn())
                .category(request.category())
                .status(BookStatus.AVAILABLE)
                .pages(0).price(0).build();
    }
}
