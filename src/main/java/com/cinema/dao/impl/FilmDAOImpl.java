package com.cinema.dao.impl;

import com.cinema.connexion.Connexion;
import com.cinema.dao.IDao;
import com.cinema.entities.Film;
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
public class FilmDAOImpl implements IDao<Film> {

    private Connection c;

    public FilmDAOImpl() {
        try {
            c = Connexion.getConnection();
            c.setAutoCommit(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /*
        private int id ;
        private String titre;
        private String genre;
        private int duree;
        private String realisateur;
     */
    @Override
    public boolean create(Film o) {
        try {
            PreparedStatement ps = c.prepareStatement("INSERT INTO film(titre, genre, duree, realisateur) VALUES (?,?,?,?)");
            ps.setString(1, o.getTitre());
            ps.setString(2, o.getGenre());
            ps.setInt(3, o.getDuree());
            ps.setString(4, o.getRealisateur());
            ps.executeUpdate();
            return true;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean update(Film o) {
        try {
            PreparedStatement ps = c.prepareStatement("UPDATE film SET titre=?, genre=?, duree=?, realisateur=? WHERE id_film=?");
            ps.setString(1, o.getTitre());
            ps.setString(2, o.getGenre());
            ps.setInt(3, o.getDuree());
            ps.setString(4, o.getRealisateur());
            ps.setInt(5, o.getId());
            ps.executeUpdate();
            return true;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean delete(Film o) {
        try {
            PreparedStatement ps = c.prepareStatement("DELETE FROM film WHERE id_film=?");
            ps.setInt(1, o.getId());
            ps.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Film findById(int id) {
        try {
            PreparedStatement ps = c.prepareStatement("SELECT * FROM film WHERE id_film=?");
            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Film(
                        rs.getInt("id_film"),
                        rs.getString("titre"),
                        rs.getString("genre"),
                        rs.getInt("duree"),
                        rs.getString("realisateur")
                );
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Film> findAll() {
        List<Film> list = new ArrayList<>();
        try {
            Statement s = c.createStatement();
            ResultSet rs = s.executeQuery("SELECT * FROM film");
            while (rs.next()) {
                list.add(new Film(
                        rs.getInt("id_film"),
                        rs.getString("titre"),
                        rs.getString("genre"),
                        rs.getInt("duree"),
                        rs.getString("realisateur")
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

}
