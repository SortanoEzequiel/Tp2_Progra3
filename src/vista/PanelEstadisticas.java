package vista;

import javax.swing.*;
import java.awt.*;

public class PanelEstadisticas extends JPanel {

    private JTextArea areaEstadisticas;
    private JButton btnEjecutarAlgoritmo;

    public PanelEstadisticas() {
        setLayout(new BorderLayout());

        areaEstadisticas = new JTextArea();
        areaEstadisticas.setEditable(false);
        JScrollPane scrollEstadisticas = new JScrollPane(areaEstadisticas);
        add(scrollEstadisticas, BorderLayout.CENTER);

        btnEjecutarAlgoritmo = new JButton("Ejecutar Algoritmo");
        add(btnEjecutarAlgoritmo, BorderLayout.SOUTH);
    }

    // Métodos de la vista
    public void mostrarEstadisticas(String texto) {
        areaEstadisticas.setText(texto);
    }

    // Getters
    public JTextArea getAreaEstadisticas() { return areaEstadisticas; }
    public JButton getBtnEjecutarAlgoritmo() { return btnEjecutarAlgoritmo; }
}
