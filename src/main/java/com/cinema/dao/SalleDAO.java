package com.cinema.dao;

import com.cinema.entities.Salle;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author meriem
 */
public class SalleDAO implements IDao<Salle> {

    private List<Salle> salles;

    public SalleDAO() {
        salles = new ArrayList<Salle>();
    }

    @Override
    public boolean create(Salle o) {
        return salles.add(o);
    }

    @Override
    public boolean update(Salle o) {
        Salle old = findById(o.getId());
        if (old == null) {
            return false;
        }
        old.setNom(o.getNom());
        old.setCapacite(o.getCapacite());
        return true;
    }

    @Override
    public boolean delete(Salle o) {
        return salles.remove(o);
    }

    @Override
    public Salle findById(int id) {
        for (Salle s : salles) {
            if (s.getId() == id) {
                return s;
            }
        }
        return null;
    }

    @Override
    public List<Salle> findAll() {
        return salles;
    }

}
