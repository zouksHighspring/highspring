package com.example.library.domain;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Book {
    private String title;
    private Author author;
    private String isbn;
    private int pages;
    private double price;
    private Category category;
    private String status;
}
