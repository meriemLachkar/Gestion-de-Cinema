package com.cinema.test;

import com.cinema.dao.impl.FilmDAOImpl;
import com.cinema.dao.impl.SalleDAOImpl;
import com.cinema.dao.impl.SeanceDAOImpl;
import com.cinema.entities.Film;
import com.cinema.entities.Salle;
import com.cinema.entities.Seance;
import java.time.LocalDate;

/**
 *
 * @author meriem
 */
public class TestSeance {

    public static void main(String[] args) {

        try {
            SeanceDAOImpl seanceDao = new SeanceDAOImpl();

            SalleDAOImpl salleDao = new SalleDAOImpl();

            FilmDAOImpl filmDao = new FilmDAOImpl();

            Film film = new Film("la la land", "Romance", 128, "Damien");
            filmDao.create(film);

            Salle salle = new Salle("Salle 2", 200);
            salleDao.create(salle);

            Seance seance = new Seance(LocalDate.now(), 140, 50, film, salle);

            seanceDao.create(seance);
            System.out.println("Séance ajoutée avec succès !");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
