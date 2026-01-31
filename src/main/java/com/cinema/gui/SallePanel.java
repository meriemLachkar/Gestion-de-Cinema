package com.cinema.gui;

import com.cinema.dao.impl.SalleDAOImpl;
import com.cinema.entities.Salle;
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
public class SallePanel extends JPanel {

    private SalleDAOImpl salleDAO;
    private JTable table;
    private DefaultTableModel tableModel;
    private JTextField nomField, capaciteField;
    private JButton addButton, editButton, deleteButton, clearButton;

    private int selectedId = -1;

    public SallePanel() {
        salleDAO = new SalleDAOImpl();
        initUI();
        loadData();
    }

    private void initUI() {
        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

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
        JPanel panel = new JPanel(new GridLayout(2, 2, 10, 10));
        panel.setBorder(BorderFactory.createTitledBorder("Informations de la Salle"));

        panel.add(new JLabel("Nom:"));
        nomField = new JTextField();
        panel.add(nomField);

        panel.add(new JLabel("Capacité:"));
        capaciteField = new JTextField();
        panel.add(capaciteField);

        return panel;
    }

    private JPanel createTablePanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createTitledBorder("Liste des Salles"));

        String[] columns = {"ID", "Nom", "Capacité"};
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
                nomField.setText(tableModel.getValueAt(row, 1).toString());
                capaciteField.setText(tableModel.getValueAt(row, 2).toString());
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

        addButton.addActionListener(e -> addSalle());
        editButton.addActionListener(e -> updateSalle());
        deleteButton.addActionListener(e -> deleteSalle());
        clearButton.addActionListener(e -> clearForm());

        panel.add(addButton);
        panel.add(editButton);
        panel.add(deleteButton);
        panel.add(clearButton);

        return panel;
    }

    private void loadData() {
        tableModel.setRowCount(0);
        List<Salle> salles = salleDAO.findAll();
        for (Salle salle : salles) {
            tableModel.addRow(new Object[]{
                salle.getId(),
                salle.getNom(),
                salle.getCapacite()
            });
        }
    }

    private void addSalle() {
        try {
            String nom = nomField.getText().trim();
            int capacite = Integer.parseInt(capaciteField.getText().trim());

            if (nom.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Le nom est requis!");
                return;
            }

            if (capacite <= 0) {
                JOptionPane.showMessageDialog(this, "La capacité doit être positive!");
                return;
            }

            Salle salle = new Salle(nom, capacite);
            if (salleDAO.create(salle)) {
                JOptionPane.showMessageDialog(this, "Salle ajoutée avec succès!");
                loadData();
                clearForm();
            } else {
                JOptionPane.showMessageDialog(this, "Erreur lors de l'ajout!");
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "La capacité doit être un nombre!");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Erreur: " + e.getMessage());
        }
    }

    private void updateSalle() {
        if (selectedId == -1) {
            JOptionPane.showMessageDialog(this, "Sélectionnez une salle à modifier!");
            return;
        }

        try {
            String nom = nomField.getText().trim();
            int capacite = Integer.parseInt(capaciteField.getText().trim());

            if (nom.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Le nom est requis!");
                return;
            }

            if (capacite <= 0) {
                JOptionPane.showMessageDialog(this, "La capacité doit être positive!");
                return;
            }

            Salle salle = new Salle(selectedId, nom, capacite);
            if (salleDAO.update(salle)) {
                JOptionPane.showMessageDialog(this, "Salle modifiée avec succès!");
                loadData();
                clearForm();
            } else {
                JOptionPane.showMessageDialog(this, "Erreur lors de la modification!");
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "La capacité doit être un nombre!");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Erreur: " + e.getMessage());
        }
    }

    private void deleteSalle() {
        if (selectedId == -1) {
            JOptionPane.showMessageDialog(this, "Sélectionnez une salle à supprimer!");
            return;
        }

        int confirm = JOptionPane.showConfirmDialog(this,
                "Voulez-vous vraiment supprimer cette salle?",
                "Confirmation",
                JOptionPane.YES_NO_OPTION);

        if (confirm == JOptionPane.YES_OPTION) {
            Salle salle = salleDAO.findById(selectedId);
            if (salle != null && salleDAO.delete(salle)) {
                JOptionPane.showMessageDialog(this, "Salle supprimée avec succès!");
                loadData();
                clearForm();
            } else {
                JOptionPane.showMessageDialog(this, "Erreur lors de la suppression!");
            }
        }
    }

    private void clearForm() {
        nomField.setText("");
        capaciteField.setText("");
        table.clearSelection();
        selectedId = -1;
    }
}
