package com.example.library;

import com.example.library.domain.*;
import com.example.library.domain.enumeration.AdresseStatus;
import com.example.library.domain.enumeration.BookCategory;
import com.example.library.domain.enumeration.BookStatus;
import com.example.library.service.BookService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.Collections;

@SpringBootApplication
@Slf4j
public class LibraryApplication {

	public static void main(String[] args) {
		SpringApplication.run(LibraryApplication.class, args);

		BookService service = new BookService();

		Author martin = Author.builder()
				.firstName("Robert")
				.lastName("Martin")
				.email("robert.martin@gmail.com")
				.phoneNumber("5141234567")
				.address(
                        Collections.singletonList(Adresse.builder()
                                .city("Montreal")
                                .state("Quebec")
                                .country("Canada")
                                .postalCode("H1A 1A1")
								.status(AdresseStatus.CURRENT)
                                .build())
				)
				.books(new ArrayList<>())
				.build();

		Reader reader = Reader.builder()
				.firstName("highspring")
				.lastName("modeste")
				.email("robert.martin@highspring.com")
				.phoneNumber("6132198504")
				.address(
                        Collections.singletonList(Adresse.builder()
                                .city("Gatineau")
                                .state("Quebec")
                                .country("Canada")
                                .postalCode("J8T 2M6")
								.status(AdresseStatus.CURRENT)
                                .build())
				)
				.build();

		Book cleanCode = Book.builder()
				.title("Clean Code")
				.author(martin)
				.isbn("12345")
				.pages(100).price(1200)
				.category(BookCategory.TECHNOLOGY)
				.status(BookStatus.AVAILABLE)
				.build();

		Book cleanArchitecture = Book.builder()
				.title("Clean Architecture")
				.author(martin)
				.isbn("45678")
				.pages(100).price(1200)
				.category(BookCategory.FICTION)
				.status(BookStatus.AVAILABLE)
				.build();

		service.addBook(cleanCode);
		service.addBook(cleanArchitecture);

		log.info("Recherche par ISBN 12345 : {}", service.findByIsbn("12345"));
		log.info("Livres de technologie : {}", service.findByCategory(BookCategory.TECHNOLOGY));
		log.info("Livres disponibles : {}", service.findByStatus(BookStatus.AVAILABLE));
		log.info("Nombre de livres de Martin : {}", service.countBooksByAuthor(martin));
		service.borrowBook(reader, cleanCode);
		log.info("Nombre de livres encore disponibles : {}", service.countBooksByStatus(BookStatus.AVAILABLE));
		log.info("Reader apres emprunt : {}", reader.toString());
	}
}
