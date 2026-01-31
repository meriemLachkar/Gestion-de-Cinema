# 🎬 Projet : Système de Gestion de Cinéma

Ce projet consiste à concevoir et développer un système de gestion de cinéma permettant d’administrer les films, les salles et les séances de projection.
L’objectif principal est de faciliter l’organisation des projections, le suivi des tickets vendus et la gestion des ressources du cinéma à travers une base de données relationnelle fiable.

Le système permet :

    - d’enregistrer les films projetés,

    - de gérer les salles et leurs capacités,

    - de planifier les séances avec des contraintes de cohérence,

    - d’assurer l’intégrité des données grâce aux clés primaires, étrangères et contraintes SQL.

## Fonctionnalités principales

🎞 Gestion des films

    - Ajouter un film (titre, genre, durée, réalisateur)

    - Modifier les informations d’un film

    - Supprimer un film

    - Consulter la liste des films

🏛 Gestion des salles

    - Ajouter une salle

    - Définir la capacité d’une salle

    - Modifier les informations d’une salle

    - Supprimer une salle

📅 Gestion des séances

    - Programmer une séance pour un film donné

    - Associer une séance à une salle

    - Définir la date de projection et le prix

    - Gérer le nombre de tickets vendus

    - Empêcher la duplication d’une séance (film + salle + date)

## Base de données

📌 Nom de la base de données
    - cinema
📌 Utilisateur de la base
    - cinema_user

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

## Diagramme de cas d’utilisation

🎭 Acteur principal

Administrateur

📌 Cas d’utilisation

    - Gérer les films

    - Gérer les salles

    - Programmer les séances

    - Consulter les séances

📌 Description textuelle du diagramme :

L’administrateur interagit avec le système pour gérer les films, les salles et les séances de projection.

## Diagramme de classe

📦 Classes principales
Classe Film

    id_film

    titre

    genre

    duree

    realisateur

Classe Salle

    id_salle

    nom

    capacite

Classe Seance

    id

    date_projection

    prix

    tickets_vendus

    film

    salle

📌 Relations

    - Un Film peut avoir plusieurs Séances

    - Une Salle peut accueillir plusieurs Séances

    - Une Séance est associée à un seul film et une seule salle

## Technologies utilisées

    Base de données : MySQL

    Langage SQL : DDL / DML

    Outils :

    MySQL Workbench / phpMyAdmin

    IDE SQL

    Système : Architecture client–serveur

## Architecture du projet / Structure
📂 Structure logique


## Auteur
Meriem Lachkar
