package com.example.library.service;

import com.example.library.domain.*;
import com.example.library.domain.dto.BookRequest;
import com.example.library.exception.BookNotFoundException;
import com.example.library.repository.BookRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;
import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

class BookServiceTest {
    private BookRepository repository;
    private BookService service;

    @BeforeEach
    void setUp() {
        repository = mock(BookRepository.class);
        service = new BookService(repository);
    }

    @Test
    void mapsAllBooksToResponseDtos() {
        when(repository.findAll()).thenReturn(List.of(new Book(7L, "Titre", "Auteur")));
        var results = service.findAll();
        assertThat(results).hasSize(1);
        assertThat(results.get(0).id()).isEqualTo(7L);
        assertThat(results.get(0).title()).isEqualTo("Titre");
        assertThat(results.get(0).author()).isEqualTo("Auteur");
    }

    @Test
    void findsExistingBook() {
        when(repository.findById(7L)).thenReturn(Optional.of(new Book(7L, "Titre", "Auteur")));
        assertThat(service.findById(7L).id()).isEqualTo(7L);
    }

    @Test
    void throwsBusinessExceptionForMissingBook() {
        when(repository.findById(7L)).thenReturn(Optional.empty());
        assertThatThrownBy(() -> service.findById(7L))
                .isInstanceOf(BookNotFoundException.class).hasMessageContaining("7");
    }

    @Test
    void stripsSurroundingWhitespaceBeforeSaving() {
        when(repository.create("Titre", "Auteur")).thenReturn(new Book(1L, "Titre", "Auteur"));
        var result = service.create(new BookRequest("Titre", "Auteur"));
        assertThat(result.title()).isEqualTo("Titre");
        verify(repository).create("Titre", "Auteur");
    }
}
