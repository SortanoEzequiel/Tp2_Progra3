package vista;

import javax.swing.*;
import java.awt.*;

public class PanelEstadisticas extends JPanel {

    private JTextArea areaEstadisticas;
    private JButton btnEjecutarAlgoritmo;

    public PanelEstadisticas() {
        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        // 🔹 Título arriba
        JLabel titulo = new JLabel("📊 Estadísticas de Afinidades", SwingConstants.CENTER);
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 16));
        add(titulo, BorderLayout.NORTH);

        // 🔹 Área de estadísticas con scroll
        areaEstadisticas = new JTextArea();
        areaEstadisticas.setEditable(false);
        areaEstadisticas.setFont(new Font("Consolas", Font.PLAIN, 13));
        areaEstadisticas.setBackground(new Color(245, 245, 245));
        areaEstadisticas.setMargin(new Insets(10, 10, 10, 10));

        JScrollPane scrollEstadisticas = new JScrollPane(areaEstadisticas);
        scrollEstadisticas.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
        add(scrollEstadisticas, BorderLayout.CENTER);

        // 🔹 Panel inferior con botón centrado
        JPanel panelBoton = new JPanel(new FlowLayout(FlowLayout.CENTER));
        btnEjecutarAlgoritmo = new JButton("▶ Ejecutar Algoritmo");
        btnEjecutarAlgoritmo.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btnEjecutarAlgoritmo.setBackground(new Color(66, 135, 245));
        btnEjecutarAlgoritmo.setForeground(Color.WHITE);
        btnEjecutarAlgoritmo.setFocusPainted(false);
        btnEjecutarAlgoritmo.setBorder(BorderFactory.createEmptyBorder(5, 15, 5, 15));
        panelBoton.add(btnEjecutarAlgoritmo);

        add(panelBoton, BorderLayout.SOUTH);
    }

    // Métodos de la vista
    public void mostrarEstadisticas(String texto) {
        areaEstadisticas.setText(texto);
    }

    // Getters
    public JTextArea getAreaEstadisticas() { return areaEstadisticas; }
    public JButton getBtnEjecutarAlgoritmo() { return btnEjecutarAlgoritmo; }
}
