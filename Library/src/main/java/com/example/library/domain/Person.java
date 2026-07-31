package com.example.library.domain;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Person {
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private Adresse address;
}
