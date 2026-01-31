package com.cinema.entities;

/**
 *
 * @author meriem
 */
public class Film {

    private int id;
    private String titre;
    private String genre;
    private int duree;
    private String realisateur;
    private static int cmp = 0;

    public Film(int id, String titre, String genre, int duree, String realisateur) {
        this.id = id;
        this.titre = titre;
        this.genre = genre;
        this.duree = duree;
        this.realisateur = realisateur;
    }

    public Film(String titre, String genre, int duree, String realisateur) {
        this.id = ++cmp;
        this.titre = titre;
        this.genre = genre;
        this.duree = duree;
        this.realisateur = realisateur;
    }

    public Film() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public int getDuree() {
        return duree;
    }

    public void setDuree(int duree) {
        this.duree = duree;
    }

    public String getRealisateur() {
        return realisateur;
    }

    public void setRealisateur(String realisateur) {
        this.realisateur = realisateur;
    }

    @Override
    public String toString() {
        return "Film{" + "id=" + id + ", titre=" + titre + ", genre=" + genre + ", duree=" + duree + ", realisateur=" + realisateur + '}';
    }

}
