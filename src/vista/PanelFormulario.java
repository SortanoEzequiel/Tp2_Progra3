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
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;

        JLabel lblNombre = new JLabel("Nombre:");
        gbc.gridx = 0; gbc.gridy = 0;
        add(lblNombre, gbc);

        txtNombre = new JTextField(20);
        gbc.gridx = 1;
        add(txtNombre, gbc);

        comboTango = crearCombo("Interés en Tango (1-5):", gbc, 1);
        comboFolclore = crearCombo("Interés en Folclore (1-5):", gbc, 2);
        comboRock = crearCombo("Interés en Rock Nacional (1-5):", gbc, 3);
        comboUrbano = crearCombo("Interés en Género Urbano (1-5):", gbc, 4);

        btnAgregarUsuario = new JButton("Agregar Usuario");
        gbc.gridx = 0; gbc.gridy = 5; gbc.gridwidth = 2;
        add(btnAgregarUsuario, gbc);
    }

    private JComboBox<Integer> crearCombo(String label, GridBagConstraints gbc, int row) {
        JLabel lbl = new JLabel(label);
        gbc.gridx = 0; gbc.gridy = row;
        add(lbl, gbc);

        JComboBox<Integer> combo = new JComboBox<>(new Integer[]{1,2,3,4,5});
        gbc.gridx = 1;
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
