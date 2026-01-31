/**
 * Author:  meriem
 */
CREATE DATABASE cinema;
USE cinema;
CREATE USER 'cinema_user'@'localhost' IDENTIFIED BY 'cinema123';
GRANT ALL PRIVILEGES ON cinema.* TO 'cinema_user'@'localhost';
FLUSH PRIVILEGES;

select * from film;
select * from salle;
select * from seance;

CREATE TABLE film (
	id_film INT AUTO_INCREMENT PRIMARY KEY,
    titre VARCHAR(100) NOT NULL,
    genre VARCHAR(50) NOT NULL,
    duree INT NOT NULL CHECK (duree > 0),
    realisateur VARCHAR(100) NOT NULL
);
CREATE TABLE salle (
	id_salle INT AUTO_INCREMENT PRIMARY KEY,
    nom VARCHAR(50) NOT NULL,
    capacite INT NOT NULL CHECK (capacite > 0)
);

CREATE TABLE seance (
	id INT AUTO_INCREMENT PRIMARY KEY,
    date_projection DATE NOT NULL,
    prix DOUBLE NOT NULL CHECK (prix > 0),
    tickets_vendus INT NOT NULL DEFAULT 0 CHECK (tickets_vendus >= 0),
    id_film INT NOT NULL,
    id_salle INT NOT NULL,
    FOREIGN KEY fk_film (id_film) REFERENCES film(id_film),
    FOREIGN KEY fk_salle (id_salle) REFERENCES salle(id_salle),
    UNIQUE(id_film, id_salle, date_projection)
);


