package com.cinema.dao.impl;

import com.cinema.connexion.Connexion;
import com.cinema.dao.IDao;
import com.cinema.entities.Salle;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author meriem
 */
public class SalleDAOImpl implements IDao<Salle> {

    private Connection c;

    public SalleDAOImpl() {
        try {
            c = Connexion.getConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /*
        private int id ;
        private String nom;
        private int capacite;
     */
    @Override
    public boolean create(Salle o) {
        try {
            PreparedStatement ps = c.prepareStatement("INSERT INTO salle (nom, capacite) values(?,?)");
            ps.setString(1, o.getNom());
            ps.setInt(2, o.getCapacite());
            ps.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean update(Salle o) {
        try {
            PreparedStatement ps = c.prepareStatement("UPDATE salle SET nom=? , capacite=? WHERE id_salle=?");
            ps.setString(1, o.getNom());
            ps.setInt(2, o.getCapacite());
            ps.setInt(3, o.getId());
            ps.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean delete(Salle o) {
        try {
            PreparedStatement ps = c.prepareStatement("DELETE FROM salle WHERE id_salle=?");
            ps.setInt(1, o.getId());
            ps.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Salle findById(int id) {
        try {
            PreparedStatement ps = c.prepareStatement("SELECT * FROM salle WHERE id_salle=?");
            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Salle(
                        rs.getInt("id_salle"),
                        rs.getString("nom"),
                        rs.getInt("capacite")
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Salle> findAll() {
        List<Salle> list = new ArrayList<>();
        try {
            Statement s = c.createStatement();
            ResultSet rs = s.executeQuery("SELECT * FROM salle");
            while (rs.next()) {
                list.add(new Salle(
                        rs.getInt("id_salle"),
                        rs.getString("nom"),
                        rs.getInt("capacite")
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

}
