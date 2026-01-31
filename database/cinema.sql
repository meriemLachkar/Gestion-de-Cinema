CREATE DATABASE cinema;
USE cinema;
CREATE USER 'cinema_user'@'localhost' IDENTIFIED BY 'cinema123';
GRANT ALL PRIVILEGES ON cinema.* TO 'cinema_user'@'localhost';
FLUSH PRIVILEGES;

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

INSERT INTO film (titre, genre, duree, realisateur) VALUES
('The Dark Knight', 'Action', 152, 'Christopher Nolan'),
('Pulp Fiction', 'Crime', 154, 'Quentin Tarantino'),
('La La Land', 'Musical', 128, 'Damien Chazelle'),
('Parasite', 'Thriller', 132, 'Bong Joon-ho'),
('Forrest Gump', 'Drama', 142, 'Robert Zemeckis'),
('The Matrix', 'Sci-Fi', 136, 'Wachowski Sisters'),
('Amélie', 'Romance', 122, 'Jean-Pierre Jeunet');

INSERT INTO salle (nom, capacite) VALUES
('Salle 3', 100),
('Salle IMAX', 300),
('Salle VIP', 50);

INSERT INTO seance (date_projection, prix, tickets_vendus, id_film, id_salle) VALUES
('2024-01-15', 12.50, 180, 1, 1),
('2024-01-15', 10.00, 120, 2, 2),
('2024-01-16', 9.50, 90, 3, 3),
('2024-01-17', 15.00, 280, 1, 4),
('2024-01-18', 8.50, 40, 4, 5),
('2024-02-10', 12.50, 170, 5, 1),
('2024-02-11', 10.50, 130, 6, 2),
('2024-02-12', 9.00, 85, 7, 3),
('2024-02-13', 14.50, 290, 8, 4),
('2024-03-05', 11.50, 195, 1, 1),
('2024-03-05', 9.50, 110, 2, 2);