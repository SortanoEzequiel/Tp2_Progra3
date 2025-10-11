package vista;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class PanelEstadisticas extends JPanel {

    private JTextArea areaEstadisticas;
    private JButton btnEjecutarAlgoritmo;

    public PanelEstadisticas() {
        
        setLayout(new BorderLayout(15, 15));
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        setBackground(new Color(240, 244, 250));

        
        JLabel titulo = new JLabel("📊 Estadísticas de Afinidades", SwingConstants.CENTER);
        titulo.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 20));
        titulo.setForeground(new Color(50, 60, 80));
        titulo.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));
        add(titulo, BorderLayout.NORTH);

       
        areaEstadisticas = new JTextArea();
        areaEstadisticas.setEditable(false);
        areaEstadisticas.setFont(new Font("Consolas", Font.PLAIN, 13));
        areaEstadisticas.setBackground(new Color(250, 250, 250));
        areaEstadisticas.setForeground(new Color(45, 45, 45));
        areaEstadisticas.setMargin(new Insets(12, 12, 12, 12));
        areaEstadisticas.setLineWrap(true);
        areaEstadisticas.setWrapStyleWord(true);

        
        JScrollPane scrollEstadisticas = new JScrollPane(areaEstadisticas);
        scrollEstadisticas.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(210, 210, 210), 1),
                BorderFactory.createEmptyBorder(5, 5, 5, 5)
        ));
        scrollEstadisticas.setBackground(Color.WHITE);
        scrollEstadisticas.setOpaque(true);
        add(scrollEstadisticas, BorderLayout.CENTER);

        JPanel panelBoton = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panelBoton.setOpaque(false);

        btnEjecutarAlgoritmo = new JButton("▶ Ejecutar Algoritmo");
        btnEjecutarAlgoritmo.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btnEjecutarAlgoritmo.setBackground(new Color(70, 130, 180));
        btnEjecutarAlgoritmo.setForeground(Color.WHITE);
        btnEjecutarAlgoritmo.setFocusPainted(false);
        btnEjecutarAlgoritmo.setBorder(BorderFactory.createEmptyBorder(8, 20, 8, 20));
        btnEjecutarAlgoritmo.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnEjecutarAlgoritmo.setAlignmentX(Component.CENTER_ALIGNMENT);

       
        btnEjecutarAlgoritmo.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                btnEjecutarAlgoritmo.setBackground(new Color(60, 115, 165));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                btnEjecutarAlgoritmo.setBackground(new Color(70, 130, 180));
            }
        });

        panelBoton.add(btnEjecutarAlgoritmo);
        add(panelBoton, BorderLayout.SOUTH);
    }

   
    public void mostrarEstadisticas(String texto) {
        areaEstadisticas.setText(texto);
    }

    public JTextArea getAreaEstadisticas() { return areaEstadisticas; }
    public JButton getBtnEjecutarAlgoritmo() { return btnEjecutarAlgoritmo; }

    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        GradientPaint gradiente = new GradientPaint(
                0, 0, new Color(250, 252, 255),
                0, getHeight(), new Color(230, 238, 250)
        );
        g2d.setPaint(gradiente);
        g2d.fillRect(0, 0, getWidth(), getHeight());
    }
}
