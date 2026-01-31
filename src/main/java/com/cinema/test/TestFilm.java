package com.cinema.test;

import com.cinema.dao.impl.FilmDAOImpl;
import com.cinema.entities.Film;

/**
 *
 * @author meriem
 */
public class TestFilm {

    public static void main(String[] args) {

        try {
            FilmDAOImpl filmDAO = new FilmDAOImpl();

            //Film film = new Film("Inception", "Sci-Fi", 148, "Nolan");
            //filmDAO.create(film);
            for (Film f : filmDAO.findAll()) {
                System.out.println(f.getTitre());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
