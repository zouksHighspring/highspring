package com.example.library.exception;

public class BookNotFoundException extends RuntimeException {
    public BookNotFoundException(Long id) {
        super("Aucun livre trouvé avec l'identifiant " + id + ".");
    }
}
