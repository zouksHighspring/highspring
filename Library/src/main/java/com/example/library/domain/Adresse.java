package com.example.library.domain;

import com.example.library.domain.enumeration.AdresseStatus;
import lombok.Builder;

@Builder
public record Adresse(
        String city,
        String state,
        String country,
        String postalCode,
        AdresseStatus status
) {
}