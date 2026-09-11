# Library Management System

## Description
Ce projet est une application de gestion de bibliothèque simple développée en Java 21 avec Spring Boot 4. Ce projet implémente les exigences du document `Week 2 - Spring Boot 3.x(1).pdf` : architecture en trois couches, injection par constructeur, DTOs, stockage en mémoire et gestion des erreurs.

## 1. Correspondance avec le sujet

| Exigence | Réalisation |
|---|---|
| Spring Boot 3.x | Parent Maven 3.5.16 |
| Controller → Service → Repository | `BookController` → `BookService` → `BookRepository` |
| Repository en mémoire | `BookRepositoryImpl`, basé sur `Map<Long, Book> books` |
| Injection par constructeur | Constructeurs du contrôleur et du service |
| DTOs | `BookRequest`, `BookResponse` |
| Liste des livres | `GET /books` |
| Livre par identifiant | `GET /books/{id}` |
| Création | `POST /books` |
| Livre absent | `BookNotFoundException`, réponse 404 |

Conformément aux contraintes : pas de base de données, d'authentification, de Spring Security, de Docker ou de configuration avancée. Il s'agit d'une API, sans interface graphique.