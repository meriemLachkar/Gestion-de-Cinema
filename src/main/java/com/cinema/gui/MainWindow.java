package com.cinema.gui;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import javax.swing.SwingUtilities;

/**
 *
 * @author meriem
 */
public class MainWindow extends JFrame {

    public MainWindow() {
        setTitle("Gestion de Cinéma");
        setSize(1000, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.addTab("Films", new FilmPanel());
        tabbedPane.addTab("Salles", new SallePanel());
        tabbedPane.addTab("Séances", new SeancePanel());
        tabbedPane.addTab("Statistiques", new StatistiquesPanel());

        add(tabbedPane);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MainWindow());
    }
}
