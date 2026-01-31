package com.cinema.connexion;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author meriem
 */
public class Connexion {

    public static Connection getConnection() throws Exception {
        Connection c = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/cinema",
                "cinema_user",
                "cinema123"
        );
        //System.out.println("Connexion à la base de données réussie !!");
        return c;
    }

    public static void main(String[] args) {
        try {
            getConnection();
        } catch (Exception e) {
            System.out.println("Erreur de connexion " + e.getMessage());
        }
    }
}
