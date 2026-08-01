package com.example.library.domain;

import lombok.*;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Loan {
    private Book book;
    private LocalDateTime loanDate;
    private LocalDateTime dueDate;
    private Boolean isDueDateRespected;
}
