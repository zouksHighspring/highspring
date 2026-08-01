# Library Management System

## Description
Ce projet est une application de gestion de bibliothèque simple développée en Java 21 avec Spring Boot 4. Elle permet de gérer des livres, des auteurs et des lecteurs, ainsi que le processus d'emprunt.

## Décisions de Conception (Design Decisions)
- **Modèle de données** : Utilisation de l'héritage (`Person` -> `Author`/`Reader`) avec Lombok `@SuperBuilder` pour faciliter la création d'objets complexes tout en respectant la hiérarchie.
- **Performance du Service** : Le `BookService` utilise une `HashMap<String, Book>` pour stocker les livres. Cela permet une recherche par ISBN en **O(1)** (temps constant), ce qui est bien plus performant qu'une liste pour de grands volumes de données.
- **Gestion des erreurs** : Utilisation d'exceptions personnalisées (`BookAlreadyBorrowedException`) pour isoler la logique métier des erreurs techniques.
- **Programmation Défensive** : Utilisation intensive de `Optional` pour éviter les `NullPointerException` lors des recherches de livres.
- **Clean Code** : Séparation claire entre les entités (domain), le service (logique métier) et l'application (point d'entrée).

## Fonctionnalités Java utilisées
- **Java 17/21 Pattern Matching** : Utilisé avec `instanceof` dans `borrowBook` pour vérifier et caster le type de personne en une seule étape.
- **Stream API** : Utilisée pour le filtrage (`filter`), le comptage (`count`) et la transformation des données en listes (`toList()`).
- **Optional API** : Pour une gestion propre des valeurs potentiellement nulles lors des recherches par ISBN.

## Challenge Optionnel (Java 17/21)
- **Records (Java 17/21+)** : Utilisés pour la classe `Adresse`. Cela rend le code plus concis, immuable et elimine (getters/setters).