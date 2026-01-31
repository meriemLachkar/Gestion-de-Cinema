package com.cinema.test;

import com.cinema.dao.impl.SalleDAOImpl;
import com.cinema.entities.Salle;

/**
 *
 * @author meriem
 */
public class TestSalle {

    public static void main(String[] args) {
        try {
            SalleDAOImpl salleDao = new SalleDAOImpl();
            //Salle salle = new Salle("Salle 1", 150);
            //salleDao.create(salle);
            //System.out.println("Salle ajoutée avec succès !");
            
            for (Salle s : salleDao.findAll()) {
                System.out.println(s.getNom());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
