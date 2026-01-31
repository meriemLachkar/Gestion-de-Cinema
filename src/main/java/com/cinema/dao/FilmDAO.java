package com.cinema.dao;

import com.cinema.entities.Film;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author meriem
 */
public class FilmDAO implements IDao<Film> {

    private List<Film> films;

    public FilmDAO() {
        films = new ArrayList<Film>();
    }

    @Override
    public boolean create(Film o) {
        return films.add(o);
    }

    @Override
    public boolean update(Film o) {
        Film old = findById(o.getId());
        if (old == null) {
            return false;
        }
        old.setTitre(o.getTitre());
        old.setGenre(o.getGenre());
        old.setDuree(o.getDuree());
        old.setRealisateur(o.getRealisateur());
        return true;

    }

    @Override
    public boolean delete(Film o) {
        return films.remove(o);
    }

    @Override
    public Film findById(int id) {
        for (Film f : films) {
            if (f.getId() == id) {
                return f;
            }
        }
        return null;
    }

    @Override
    public List<Film> findAll() {
        return films;
    }

}
