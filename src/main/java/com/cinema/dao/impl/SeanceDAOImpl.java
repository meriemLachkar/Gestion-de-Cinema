package com.cinema.dao.impl;

import com.cinema.connexion.Connexion;
import com.cinema.dao.IDao;
import com.cinema.entities.Film;
import com.cinema.entities.Salle;
import com.cinema.entities.Seance;
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
public class SeanceDAOImpl implements IDao<Seance> {

    private Connection c;

    public SeanceDAOImpl() {
        try {
            c = Connexion.getConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /*
        private int id;
        private LocalDate dateP;
        private double prix;
        private int ticketsVendus;
        private Film film;
        private Salle salle;
     */
    @Override
    public boolean create(Seance o) {
        try {
            PreparedStatement ps = c.prepareStatement("INSERT INTO seance(date_projection, prix, tickets_vendus, id_film, id_salle) VALUES(?,?,?,?,?)");
            ps.setDate(1, java.sql.Date.valueOf(o.getDateP()));
            ps.setDouble(2, o.getPrix());
            ps.setInt(3, o.getTicketsVendus());
            ps.setInt(4, o.getFilm().getId());
            ps.setInt(5, o.getSalle().getId());
            ps.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean update(Seance o) {
        try {
            PreparedStatement ps = c.prepareStatement("UPDATE seance SET date_projection=?, prix=?, tickets_vendus=?, id_film=?, id_salle=? WHERE id=?");
            ps.setDate(1, java.sql.Date.valueOf(o.getDateP()));
            ps.setDouble(2, o.getPrix());
            ps.setInt(3, o.getTicketsVendus());
            ps.setInt(4, o.getFilm().getId());
            ps.setInt(5, o.getSalle().getId());
            ps.setInt(6, o.getId());
            ps.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean delete(Seance o) {
        try {
            PreparedStatement ps = c.prepareStatement("DELETE FROM seance WHERE id=?");
            ps.setInt(1, o.getId());
            ps.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Seance findById(int id) {
        try {
            PreparedStatement ps = c.prepareStatement("SELECT * FROM seance where id=?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Film f = new Film();
                f.setId(rs.getInt("id_film"));
                Salle sa = new Salle();
                sa.setId(rs.getInt("id_salle"));

                return new Seance(
                        rs.getInt("id"),
                        rs.getDate("date_projection").toLocalDate(),
                        rs.getDouble("prix"),
                        rs.getInt("tickets_vendus"),
                        f,
                        sa
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Seance> findAll() {
        List<Seance> list = new ArrayList<>();
        try {
            Statement s = c.createStatement();
            ResultSet rs = s.executeQuery("SELECT * FROM seance");
            while (rs.next()) {
                Film f = new Film();
                f.setId(rs.getInt("id_film"));

                Salle sa = new Salle();
                sa.setId(rs.getInt("id_salle"));

                list.add(new Seance(
                        rs.getInt("id"),
                        rs.getDate("date_projection").toLocalDate(),
                        rs.getDouble("prix"),
                        rs.getInt("tickets_vendus"),
                        f,
                        sa
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

}
