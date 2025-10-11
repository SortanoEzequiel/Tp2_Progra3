package vista;

import javax.swing.*;
import java.awt.*;

public class PanelFormulario extends JPanel {

    private JTextField txtNombre;
    private JComboBox<Integer> comboTango;
    private JComboBox<Integer> comboFolclore;
    private JComboBox<Integer> comboRock;
    private JComboBox<Integer> comboUrbano;
    private JButton btnAgregarUsuario;

    public PanelFormulario() {
        setBackground(new Color(245, 247, 250));
        setLayout(new GridBagLayout());
        setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(12, 12, 12, 12);
        gbc.fill = GridBagConstraints.HORIZONTAL;

      
        JLabel lblTitulo = new JLabel("Registro de Intereses Musicales", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 20));
        lblTitulo.setForeground(new Color(40, 40, 40));
        gbc.gridx = 0; gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.weightx = 1;
        add(lblTitulo, gbc);

        gbc.gridwidth = 1; 

        
        JLabel lblNombre = crearEtiqueta("Nombre:");
        gbc.gridx = 0; gbc.gridy = 1;
        gbc.weightx = 0.3; 
        gbc.anchor = GridBagConstraints.EAST;
        add(lblNombre, gbc);

        txtNombre = crearCampoTexto();
        gbc.gridx = 1;
        gbc.weightx = 0.7; 
        gbc.anchor = GridBagConstraints.WEST;
        add(txtNombre, gbc);

        comboTango = crearCombo("Interés en Tango (1-5):", gbc, 2);
        comboFolclore = crearCombo("Interés en Folclore (1-5):", gbc, 3);
        comboRock = crearCombo("Interés en Rock Nacional (1-5):", gbc, 4);
        comboUrbano = crearCombo("Interés en Género Urbano (1-5):", gbc, 5);

        
        btnAgregarUsuario = new JButton("Agregar Usuario");
        btnAgregarUsuario.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btnAgregarUsuario.setBackground(new Color(70, 130, 180));
        btnAgregarUsuario.setForeground(Color.WHITE);
        btnAgregarUsuario.setFocusPainted(false);
        btnAgregarUsuario.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnAgregarUsuario.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        gbc.gridx = 0; gbc.gridy = 6;
        gbc.gridwidth = 2;
        gbc.weightx = 1;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.NONE;
        add(btnAgregarUsuario, gbc);
    }

    private JLabel crearEtiqueta(String texto) {
        JLabel label = new JLabel(texto);
        label.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        label.setForeground(new Color(60, 60, 60));
        return label;
    }

    private JTextField crearCampoTexto() {
        JTextField campo = new JTextField();
        campo.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        campo.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(200, 200, 200)),
                BorderFactory.createEmptyBorder(5, 8, 5, 8)
        ));
        return campo;
    }

    private JComboBox<Integer> crearCombo(String texto, GridBagConstraints gbc, int fila) {
        JLabel lbl = crearEtiqueta(texto);
        gbc.gridx = 0; gbc.gridy = fila;
        gbc.weightx = 0.3;
        gbc.anchor = GridBagConstraints.EAST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        add(lbl, gbc);

        JComboBox<Integer> combo = new JComboBox<>(new Integer[]{1, 2, 3, 4, 5});
        combo.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        combo.setBackground(Color.WHITE);
        combo.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200)));
        gbc.gridx = 1;
        gbc.weightx = 0.7;
        gbc.anchor = GridBagConstraints.WEST;
        add(combo, gbc);

        return combo;
    }

    // Getters
    public JTextField getTxtNombre() { return txtNombre; }
    public JComboBox<Integer> getComboTango() { return comboTango; }
    public JComboBox<Integer> getComboFolclore() { return comboFolclore; }
    public JComboBox<Integer> getComboRock() { return comboRock; }
    public JComboBox<Integer> getComboUrbano() { return comboUrbano; }
    public JButton getBtnAgregarUsuario() { return btnAgregarUsuario; }
}
