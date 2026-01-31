package com.cinema.dao;

import com.cinema.entities.Seance;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author meriem
 */
public class SeanceDAO implements IDao<Seance> {

    private List<Seance> seances;

    public SeanceDAO() {
        seances = new ArrayList<Seance>();
    }

    @Override
    public boolean create(Seance o) {
        return seances.add(o);
    }

    @Override
    public boolean update(Seance o) {
        Seance old = findById(o.getId());
        if (old == null) {
            return false;
        }
        old.setDateP(o.getDateP());
        old.setPrix(o.getPrix());
        old.setTicketsVendus(o.getTicketsVendus());
        old.setFilm(o.getFilm());
        old.setSalle(o.getSalle());
        return true;
    }

    @Override
    public boolean delete(Seance o) {
        return seances.remove(o);
    }

    @Override
    public Seance findById(int id) {
        for (Seance sc : seances) {
            if (sc.getId() == id) {
                return sc;
            }
        }
        return null;
    }

    @Override
    public List<Seance> findAll() {
        return seances;
    }

}
