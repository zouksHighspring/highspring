package com.example.library.domain;

import com.example.library.domain.enumeration.BookCategory;
import com.example.library.domain.enumeration.BookStatus;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Book {
    private Long id;
    private String title;
    private Author author;
    private String isbn;
    private int pages;
    private double price;
    private BookCategory category;
    private BookStatus status;
}
