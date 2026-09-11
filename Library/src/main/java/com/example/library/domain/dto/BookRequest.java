package com.example.library.domain.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record BookRequest(
        @NotBlank(message = "Le titre est obligatoire.")
        @Size(max = 200, message = "Le titre ne doit pas dépasser 200 caractères.")
        String title,
        @NotBlank(message = "L'auteur est obligatoire.")
        @Size(max = 120, message = "L'auteur ne doit pas dépasser 120 caractères.")
        String author
) { }
