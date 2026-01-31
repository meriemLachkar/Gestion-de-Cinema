# 🎬 Projet : Système de Gestion de Cinéma

Ce projet consiste à concevoir et développer un système de gestion de cinéma permettant d’administrer les films, les salles et les séances de projection.
L’objectif principal est de faciliter l’organisation des projections, le suivi des tickets vendus et la gestion des ressources du cinéma à travers une base de données relationnelle fiable.

    Le système permet :

        • d’enregistrer les films projetés,

        • de gérer les salles et leurs capacités,

        • de planifier les séances avec des contraintes de cohérence,

        • d’assurer l’intégrité des données grâce aux clés primaires, étrangères et contraintes SQL.

## Fonctionnalités principales

🎞 Gestion des films

    • Ajouter un film (titre, genre, durée, réalisateur)

    • Modifier les informations d’un film

    • Supprimer un film

    • Consulter la liste des films

🏛 Gestion des salles

    • Ajouter une salle

    • Définir la capacité d’une salle

    • Modifier les informations d’une salle

    • Supprimer une salle

📅 Gestion des séances

    • Programmer une séance pour un film donné

    • Associer une séance à une salle

    • Définir la date de projection et le prix

    • Gérer le nombre de tickets vendus

    • Empêcher la duplication d’une séance (film + salle + date)

## Base de données

📌 Nom de la base de données

    • cinema

📌 Utilisateur de la base

    • cinema_user

## Description des tables

🎬 Table film

    | Champ       | Type         | Description                |
    | ----------- | ------------ | -------------------------- |
    | id_film     | INT (PK)     | Identifiant du film        |
    | titre       | VARCHAR(100) | Titre du film              |
    | genre       | VARCHAR(50)  | Genre du film              |
    | duree       | INT          | Durée du film (en minutes) |
    | realisateur | VARCHAR(100) | Réalisateur                |

🏛 Table salle

    | Champ    | Type        | Description             |
    | -------- | ----------- | ----------------------- |
    | id_salle | INT (PK)    | Identifiant de la salle |
    | nom      | VARCHAR(50) | Nom de la salle         |
    | capacite | INT         | Capacité de la salle    |

📅 Table seance

    | Champ           | Type     | Description              |
    | --------------- | -------- | ------------------------ |
    | id              | INT (PK) | Identifiant de la séance |
    | date_projection | DATE     | Date de projection       |
    | prix            | DOUBLE   | Prix du ticket           |
    | tickets_vendus  | INT      | Nombre de tickets vendus |
    | id_film         | INT (FK) | Film projeté             |
    | id_salle        | INT (FK) | Salle utilisée           |


<img width="1325" height="733" alt="database" src="https://github.com/user-attachments/assets/b4a47cd1-3418-4fe3-b341-7302d37d892b" />


## Diagramme de cas d’utilisation

🎭 Acteur principal

    • Administrateur

📌 Cas d’utilisation

<img width="886" height="777" alt="use_case_diagram" src="https://github.com/user-attachments/assets/153d85fa-62e8-4262-8628-fa4f0a2a530c" />


📌 Description textuelle du diagramme :

    L’administrateur interagit avec le système pour gérer les films, les salles et les séances de projection.

## Diagramme de classe

<img width="836" height="650" alt="class_diagram" src="https://github.com/user-attachments/assets/5ca18b24-3953-4e1b-a00c-0379442018dd" />


📌 Relations

    • Un Film peut avoir plusieurs Séances

    • Une Salle peut accueillir plusieurs Séances

    • Une Séance est associée à un seul film et une seule salle

## Technologies utilisées

Backend & Base de données

    • Java 17
    • MySQL 8.0+
    • JDBC
    • Maven

Interface Graphique

    • Java Swing
    • JFreeChart 1.5.3 (pour les graphiques)

Architecture

    • DAO Pattern
    • MVC Léger
    • Stream API

## Architecture du projet / Structure
📂 Structure logique

    cinema-project/
    ├── src/main/java/com/cinema/
    │   ├── connexion/           
    │   │   └── Connexion.java
    │   ├── dao/                 
    │   │   ├── IDao.java        
    │   │   ├── impl/            
    │   │   │   ├── FilmDAOImpl.java
    │   │   │   ├── SalleDAOImpl.java
    │   │   │   └── SeanceDAOImpl.java
    │   │   ├── FilmDAO.java    
    │   │   ├── SalleDAO.java
    │   │   └── SeanceDAO.java
    │   ├── entities/            
    │   │   ├── Film.java
    │   │   ├── Salle.java
    │   │   └── Seance.java
    │   ├── gui/                 
    │   │   ├── MainWindow.java
    │   │   ├── FilmPanel.java
    │   │   ├── SallePanel.java
    │   │   ├── SeancePanel.java
    │   │   └── StatistiquesPanel.java
    │   └── test/                
    │       ├── TestFilm.java
    │       ├── TestSalle.java
    │       └── TestSeance.java
    ├── database/                
    │   └── cinema.sql
    ├── lib/                     
    ├── pom.xml                  
    └── README.md                

## Vidéo de test (contenu à montrer)


https://github.com/user-attachments/assets/f1051b7e-5b51-4c85-ab64-9c6d077135f1


## Conclusion

Ce projet permet de mettre en pratique les concepts fondamentaux des bases de données relationnelles :

    • Modélisation UML

    • Contraintes d’intégrité

    • Relations entre tables

    • Normalisation des données

Il constitue une base solide pour une future extension vers une application web ou desktop.

## Auteur
Meriem Lachkar
