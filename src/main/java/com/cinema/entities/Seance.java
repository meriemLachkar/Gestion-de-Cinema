package com.cinema.entities;

import java.time.LocalDate;

/**
 *
 * @author meriem
 */
public class Seance {

    private int id;
    private LocalDate dateP;
    private double prix;
    private int ticketsVendus;
    private Film film;
    private Salle salle;
    private static int cmp = 0;

    public Seance(LocalDate dateP, double prix, int ticketsVendus, Film film, Salle salle) {
        this.id = ++cmp;
        this.dateP = dateP;
        this.prix = prix;
        this.ticketsVendus = ticketsVendus;
        this.film = film;
        this.salle = salle;
    }

    public Seance(int id, LocalDate dateP, double prix, int ticketsVendus, Film film, Salle salle) {
        this.id = id;
        this.dateP = dateP;
        this.prix = prix;
        this.ticketsVendus = ticketsVendus;
        this.film = film;
        this.salle = salle;
    }

    public int getId() {
        return id;
    }

    public LocalDate getDateP() {
        return dateP;
    }

    public void setDateP(LocalDate dateP) {
        this.dateP = dateP;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    public int getTicketsVendus() {
        return ticketsVendus;
    }

    public void setTicketsVendus(int ticketsVendus) {
        this.ticketsVendus = ticketsVendus;
    }

    public Film getFilm() {
        return film;
    }

    public void setFilm(Film film) {
        this.film = film;
    }

    public Salle getSalle() {
        return salle;
    }

    public void setSalle(Salle salle) {
        this.salle = salle;
    }
}
