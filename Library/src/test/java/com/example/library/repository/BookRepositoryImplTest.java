package com.example.library.repository;

import com.example.library.domain.Book;
import com.example.library.repository.impl.BookRepositoryImpl;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class BookRepositoryImplTest {
    @Test
    void startsEmptyAndMissingIdReturnsEmpty() {
        var repository = new BookRepositoryImpl();
        assertThat(repository.findAll()).isEmpty();
        assertThat(repository.findById(1L)).isEmpty();
    }

    @Test
    void preservesInsertionOrderAndReturnsImmutableSnapshot() {
        var repository = new BookRepositoryImpl();
        Book first = repository.create("Titre", "Auteur");
        List<Book> snapshot = repository.findAll();
        Book second = repository.create("Autre titre", "Autre auteur");
        assertThat(first.id()).isEqualTo(1L);
        assertThat(second.id()).isEqualTo(2L);
        assertThat(repository.findById(first.id())).contains(first);
        assertThat(repository.findAll()).containsExactly(first, second);
        assertThat(snapshot).containsExactly(first);
        assertThatThrownBy(snapshot::clear).isInstanceOf(UnsupportedOperationException.class);
    }

    @Test
    void concurrentCreatesDoNotLoseBooksOrDuplicateIds() throws Exception {
        var repository = new BookRepositoryImpl();
        var executor = Executors.newFixedThreadPool(8);
        try {
            List<Callable<Book>> tasks = IntStream.range(0, 100)
                    .mapToObj(i -> (Callable<Book>) () -> repository.create("Livre " + i, "Auteur"))
                    .toList();
            for (var result : executor.invokeAll(tasks)) {
                assertThat(result.get().id()).isPositive();
            }
            assertThat(repository.findAll()).hasSize(100);
            assertThat(repository.findAll()).extracting(Book::id).doesNotHaveDuplicates();
        } finally {
            executor.shutdownNow();
        }
    }
}
