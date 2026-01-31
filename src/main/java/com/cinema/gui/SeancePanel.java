package com.cinema.gui;

import com.cinema.dao.impl.FilmDAOImpl;
import com.cinema.dao.impl.SalleDAOImpl;
import com.cinema.dao.impl.SeanceDAOImpl;
import com.cinema.entities.Film;
import com.cinema.entities.Salle;
import com.cinema.entities.Seance;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
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
public class SeancePanel extends JPanel {

    private SeanceDAOImpl seanceDAO;
    private FilmDAOImpl filmDAO;
    private SalleDAOImpl salleDAO;

    private JTable table;
    private DefaultTableModel tableModel;
    private JComboBox<Film> filmCombo;
    private JComboBox<Salle> salleCombo;
    private JTextField dateField, prixField, ticketsField;
    private JButton addButton, editButton, deleteButton, clearButton, todayButton;
    private int selectedId = -1;

    private DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public SeancePanel() {
        seanceDAO = new SeanceDAOImpl();
        filmDAO = new FilmDAOImpl();
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
        JPanel panel = new JPanel(new GridLayout(5, 2, 10, 10));
        panel.setBorder(BorderFactory.createTitledBorder("Informations de la Séance"));

        List<Film> films = filmDAO.findAll();
        List<Salle> salles = salleDAO.findAll();

        panel.add(new JLabel("Film:"));
        filmCombo = new JComboBox<>();
        for (Film film : films) {
            filmCombo.addItem(film);
        }
        filmCombo.setRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value,
                    int index, boolean isSelected, boolean cellHasFocus) {
                if (value instanceof Film) {
                    value = ((Film) value).getTitre();
                }
                return super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
            }
        });
        panel.add(filmCombo);

        panel.add(new JLabel("Salle:"));
        salleCombo = new JComboBox<>();
        for (Salle salle : salles) {
            salleCombo.addItem(salle);
        }
        salleCombo.setRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value,
                    int index, boolean isSelected, boolean cellHasFocus) {
                if (value instanceof Salle) {
                    value = ((Salle) value).getNom() + " (" + ((Salle) value).getCapacite() + " places)";
                }
                return super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
            }
        });
        panel.add(salleCombo);

        panel.add(new JLabel("Date (AAAA-MM-JJ):"));
        JPanel datePanel = new JPanel(new BorderLayout());
        dateField = new JTextField();
        todayButton = new JButton("Aujourd'hui");
        todayButton.addActionListener(e -> {
            dateField.setText(LocalDate.now().format(dateFormatter));
        });
        datePanel.add(dateField, BorderLayout.CENTER);
        datePanel.add(todayButton, BorderLayout.EAST);
        panel.add(datePanel);

        panel.add(new JLabel("Prix (MAD):"));
        prixField = new JTextField();
        panel.add(prixField);

        panel.add(new JLabel("Tickets vendus:"));
        ticketsField = new JTextField("0");
        panel.add(ticketsField);

        return panel;
    }

    private JPanel createTablePanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createTitledBorder("Liste des Séances"));

        String[] columns = {"ID", "Film", "Salle", "Date", "Prix", "Tickets", "Recette"};
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

                // Trouver le film correspondant
                String filmTitre = tableModel.getValueAt(row, 1).toString();
                for (int i = 0; i < filmCombo.getItemCount(); i++) {
                    Film film = filmCombo.getItemAt(i);
                    if (film.getTitre().equals(filmTitre)) {
                        filmCombo.setSelectedIndex(i);
                        break;
                    }
                }

                // Trouver la salle correspondante
                String salleInfo = tableModel.getValueAt(row, 2).toString();
                String salleNom = salleInfo.split(" ")[0]; // Extraire le nom
                for (int i = 0; i < salleCombo.getItemCount(); i++) {
                    Salle salle = salleCombo.getItemAt(i);
                    if (salle.getNom().equals(salleNom)) {
                        salleCombo.setSelectedIndex(i);
                        break;
                    }
                }

                dateField.setText(tableModel.getValueAt(row, 3).toString());
                prixField.setText(tableModel.getValueAt(row, 4).toString());
                ticketsField.setText(tableModel.getValueAt(row, 5).toString());
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

        addButton.addActionListener(e -> addSeance());
        editButton.addActionListener(e -> updateSeance());
        deleteButton.addActionListener(e -> deleteSeance());
        clearButton.addActionListener(e -> clearForm());

        panel.add(addButton);
        panel.add(editButton);
        panel.add(deleteButton);
        panel.add(clearButton);

        return panel;
    }

    private void loadData() {
        tableModel.setRowCount(0);
        List<Seance> seances = seanceDAO.findAll();
        for (Seance seance : seances) {
            Film film = filmDAO.findById(seance.getFilm().getId());
            Salle salle = salleDAO.findById(seance.getSalle().getId());

            double recette = seance.getPrix() * seance.getTicketsVendus();

            tableModel.addRow(new Object[]{
                seance.getId(),
                film != null ? film.getTitre() : "Inconnu",
                salle != null ? salle.getNom() + " (" + salle.getCapacite() + " places)" : "Inconnue",
                seance.getDateP().format(dateFormatter),
                String.format("%.2f", seance.getPrix()),
                seance.getTicketsVendus(),
                String.format("%.2f", recette)
            });
        }
    }

    private void addSeance() {
        try {
            Film film = (Film) filmCombo.getSelectedItem();
            Salle salle = (Salle) salleCombo.getSelectedItem();
            LocalDate date = LocalDate.parse(dateField.getText().trim(), dateFormatter);
            double prix = Double.parseDouble(prixField.getText().trim());
            int tickets = Integer.parseInt(ticketsField.getText().trim());

            if (film == null || salle == null) {
                JOptionPane.showMessageDialog(this, "Film et salle sont requis!");
                return;
            }

            if (prix <= 0) {
                JOptionPane.showMessageDialog(this, "Le prix doit être positif!");
                return;
            }

            if (tickets < 0) {
                JOptionPane.showMessageDialog(this, "Le nombre de tickets ne peut pas être négatif!");
                return;
            }

            if (tickets > salle.getCapacite()) {
                JOptionPane.showMessageDialog(this,
                        "Tickets vendus (" + tickets + ") > capacité de la salle (" + salle.getCapacite() + ")!");
                return;
            }

            Seance seance = new Seance(date, prix, tickets, film, salle);
            if (seanceDAO.create(seance)) {
                JOptionPane.showMessageDialog(this, "Séance ajoutée avec succès!");
                loadData();
                clearForm();
            } else {
                JOptionPane.showMessageDialog(this, "Erreur: cette séance existe déjà (doublon)!");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Erreur: " + e.getMessage());
        }
    }

    private void updateSeance() {
        if (selectedId == -1) {
            JOptionPane.showMessageDialog(this, "Sélectionnez une séance à modifier!");
            return;
        }

        try {
            Film film = (Film) filmCombo.getSelectedItem();
            Salle salle = (Salle) salleCombo.getSelectedItem();
            LocalDate date = LocalDate.parse(dateField.getText().trim(), dateFormatter);
            double prix = Double.parseDouble(prixField.getText().trim());
            int tickets = Integer.parseInt(ticketsField.getText().trim());

            if (film == null || salle == null) {
                JOptionPane.showMessageDialog(this, "Film et salle sont requis!");
                return;
            }

            if (prix <= 0) {
                JOptionPane.showMessageDialog(this, "Le prix doit être positif!");
                return;
            }

            if (tickets < 0) {
                JOptionPane.showMessageDialog(this, "Le nombre de tickets ne peut pas être négatif!");
                return;
            }

            Seance seance = new Seance(selectedId, date, prix, tickets, film, salle);
            if (seanceDAO.update(seance)) {
                JOptionPane.showMessageDialog(this, "Séance modifiée avec succès!");
                loadData();
                clearForm();
            } else {
                JOptionPane.showMessageDialog(this, "Erreur lors de la modification!");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Erreur: " + e.getMessage());
        }
    }

    private void deleteSeance() {
        if (selectedId == -1) {
            JOptionPane.showMessageDialog(this, "Sélectionnez une séance à supprimer!");
            return;
        }

        int confirm = JOptionPane.showConfirmDialog(this,
                "Voulez-vous vraiment supprimer cette séance?",
                "Confirmation",
                JOptionPane.YES_NO_OPTION);

        if (confirm == JOptionPane.YES_OPTION) {
            Seance seance = seanceDAO.findById(selectedId);
            if (seance != null && seanceDAO.delete(seance)) {
                JOptionPane.showMessageDialog(this, "Séance supprimée avec succès!");
                loadData();
                clearForm();
            } else {
                JOptionPane.showMessageDialog(this, "Erreur lors de la suppression!");
            }
        }
    }

    private void clearForm() {
        if (filmCombo.getItemCount() > 0) {
            filmCombo.setSelectedIndex(0);
        }
        if (salleCombo.getItemCount() > 0) {
            salleCombo.setSelectedIndex(0);
        }
        dateField.setText("");
        prixField.setText("");
        ticketsField.setText("0");
        table.clearSelection();
        selectedId = -1;
    }
}
