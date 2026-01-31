package com.cinema.gui;

import com.cinema.dao.impl.FilmDAOImpl;
import com.cinema.entities.Film;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author meriem
 */
public class FilmPanel extends JPanel {
    private FilmDAOImpl filmDAO;
    private JTable table;
    private DefaultTableModel tableModel;
    private JTextField titreField, genreField, dureeField, realisateurField;
    private JButton addButton, editButton, deleteButton, clearButton;
    private int selectedId = -1;

    public FilmPanel() {
        filmDAO = new FilmDAOImpl();
        initUI();
        loadData();
    }

    private void initUI() {
        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Panel formulaire
        JPanel formPanel = createFormPanel();
        
        JPanel tablePanel = createTablePanel();
        
        JPanel buttonPanel = createButtonPanel();

        JPanel northPanel = new JPanel(new BorderLayout());
        northPanel.add(formPanel, BorderLayout.NORTH);
        northPanel.add(buttonPanel, BorderLayout.SOUTH);
        
        add(northPanel, BorderLayout.NORTH);
        add(tablePanel, BorderLayout.CENTER);
    }

    private JPanel createFormPanel() {
        JPanel panel = new JPanel(new GridLayout(4, 2, 10, 10));
        panel.setBorder(BorderFactory.createTitledBorder("Informations du Film"));
        
        panel.add(new JLabel("Titre:"));
        titreField = new JTextField();
        panel.add(titreField);
        
        panel.add(new JLabel("Genre:"));
        genreField = new JTextField();
        panel.add(genreField);
        
        panel.add(new JLabel("Durée (minutes):"));
        dureeField = new JTextField();
        panel.add(dureeField);
        
        panel.add(new JLabel("Réalisateur:"));
        realisateurField = new JTextField();
        panel.add(realisateurField);
        
        return panel;
    }

    private JPanel createTablePanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createTitledBorder("Liste des Films"));
        
        String[] columns = {"ID", "Titre", "Genre", "Durée", "Réalisateur"};
        tableModel = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        
        table = new JTable(tableModel);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        table.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting() && table.getSelectedRow() != -1) {
                int row = table.getSelectedRow();
                selectedId = (int) tableModel.getValueAt(row, 0);
                titreField.setText(tableModel.getValueAt(row, 1).toString());
                genreField.setText(tableModel.getValueAt(row, 2).toString());
                dureeField.setText(tableModel.getValueAt(row, 3).toString());
                realisateurField.setText(tableModel.getValueAt(row, 4).toString());
            }
        });
        
        JScrollPane scrollPane = new JScrollPane(table);
        panel.add(scrollPane, BorderLayout.CENTER);
        
        return panel;
    }

    private JPanel createButtonPanel() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        
        addButton = new JButton("Ajouter");
        editButton = new JButton("Modifier");
        deleteButton = new JButton("Supprimer");
        clearButton = new JButton("Vider");
        
        addButton.addActionListener(e -> addFilm());
        editButton.addActionListener(e -> updateFilm());
        deleteButton.addActionListener(e -> deleteFilm());
        clearButton.addActionListener(e -> clearForm());
        
        panel.add(addButton);
        panel.add(editButton);
        panel.add(deleteButton);
        panel.add(clearButton);
        
        return panel;
    }

    private void loadData() {
        tableModel.setRowCount(0);
        List<Film> films = filmDAO.findAll();
        for (Film film : films) {
            tableModel.addRow(new Object[]{
                film.getId(),
                film.getTitre(),
                film.getGenre(),
                film.getDuree(),
                film.getRealisateur()
            });
        }
    }

    private void addFilm() {
        try {
            String titre = titreField.getText().trim();
            String genre = genreField.getText().trim();
            int duree = Integer.parseInt(dureeField.getText().trim());
            String realisateur = realisateurField.getText().trim();
            
            if (titre.isEmpty() || genre.isEmpty() || realisateur.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Tous les champs sont requis!");
                return;
            }
            
            Film film = new Film(titre, genre, duree, realisateur);
            if (filmDAO.create(film)) {
                JOptionPane.showMessageDialog(this, "Film ajouté avec succès!");
                loadData();
                clearForm();
            } else {
                JOptionPane.showMessageDialog(this, "Erreur lors de l'ajout!");
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "La durée doit être un nombre!");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Erreur: " + e.getMessage());
        }
    }

    private void updateFilm() {
        if (selectedId == -1) {
            JOptionPane.showMessageDialog(this, "Sélectionnez un film à modifier!");
            return;
        }
        
        try {
            String titre = titreField.getText().trim();
            String genre = genreField.getText().trim();
            int duree = Integer.parseInt(dureeField.getText().trim());
            String realisateur = realisateurField.getText().trim();
            
            if (titre.isEmpty() || genre.isEmpty() || realisateur.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Tous les champs sont requis!");
                return;
            }
            
            Film film = new Film(selectedId, titre, genre, duree, realisateur);
            if (filmDAO.update(film)) {
                JOptionPane.showMessageDialog(this, "Film modifié avec succès!");
                loadData();
                clearForm();
            } else {
                JOptionPane.showMessageDialog(this, "Erreur lors de la modification!");
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "La durée doit être un nombre!");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Erreur: " + e.getMessage());
        }
    }

    private void deleteFilm() {
        if (selectedId == -1) {
            JOptionPane.showMessageDialog(this, "Sélectionnez un film à supprimer!");
            return;
        }
        
        int confirm = JOptionPane.showConfirmDialog(this,
            "Voulez-vous vraiment supprimer ce film?",
            "Confirmation",
            JOptionPane.YES_NO_OPTION);
        
        if (confirm == JOptionPane.YES_OPTION) {
            Film film = filmDAO.findById(selectedId);
            if (film != null && filmDAO.delete(film)) {
                JOptionPane.showMessageDialog(this, "Film supprimé avec succès!");
                loadData();
                clearForm();
            } else {
                JOptionPane.showMessageDialog(this, "Erreur lors de la suppression!");
            }
        }
    }

    private void clearForm() {
        titreField.setText("");
        genreField.setText("");
        dureeField.setText("");
        realisateurField.setText("");
        table.clearSelection();
        selectedId = -1;
    }
}
