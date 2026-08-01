package com.example.library.domain;

import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
@SuperBuilder
public class Person {
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private List<Adresse> address;
    @Builder.Default
    private List<Loan> loans = new ArrayList<>();
}
