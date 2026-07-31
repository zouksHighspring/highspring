package com.example.library.domain;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Adresse {
    private String city;
    private String state;
    private String country;
    private String postalCode;
}
