package com.example.library.domain.dto;

import com.example.library.domain.enumeration.BookCategory;

public record BookRequest(

        String title,
        String isbn,
        BookCategory category

) {
}
