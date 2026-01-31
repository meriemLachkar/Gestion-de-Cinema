/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.cinema.gui;

import com.cinema.dao.impl.SeanceDAOImpl;
import com.cinema.entities.Seance;
import java.awt.BorderLayout;
import java.time.Month;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import javax.swing.JPanel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;

/**
 *
 * @author meriem
 */
public class StatistiquesPanel extends JPanel {
    
    public StatistiquesPanel(){
        initUI();
        loadChart();
    }

    private void initUI() {
        setLayout(new BorderLayout());
    }

    private void loadChart() {
        SeanceDAOImpl seanceDAO = new SeanceDAOImpl();
        List<Seance> seances = seanceDAO.findAll();
        
        Map<Month, Double> recettesParMois = seances.stream()
                .collect(Collectors.groupingBy(s -> s.getDateP()
                .getMonth(),Collectors
                .summingDouble(s -> s.getPrix() * s.getTicketsVendus())));
        
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        recettesParMois.forEach((mois, recette) -> {
            dataset.addValue(recette, "Recettes", mois.toString());
        });
        
        JFreeChart chart = ChartFactory.createBarChart(
                "Recettes pas mois",
                "Mois",
                "Recettes (MAD)",
                dataset
        );
        
        ChartPanel chartPanel = new ChartPanel(chart);
        add(chartPanel, BorderLayout.CENTER);
        
    }
}
