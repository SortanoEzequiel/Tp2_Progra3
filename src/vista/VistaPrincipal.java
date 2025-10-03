package vista;

import modelo.GrafoUsuarios;
import modelo.Observador;
import modelo.UsuarioMusical;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;
import java.util.ArrayList;
import controller.Controlador;

import java.awt.*;

public class VistaPrincipal extends JFrame implements Observador {

    private JTextField txtNombre;
    private JComboBox<Integer> comboTango;
    private JComboBox<Integer> comboFolclore;
    private JComboBox<Integer> comboRock;
    private JComboBox<Integer> comboUrbano;
    private JTable tablaUsuarios;
    private JTextArea areaEstadisticas;
    private JButton btnAgregarUsuario;
    private JButton btnEjecutarAlgoritmo;
    private DefaultTableModel tablaModelo;
    private Controlador controlador;

    public VistaPrincipal() {
    	iniciarComponentes();

    }
    
    private void iniciarComponentes() {
        setTitle("Descubriendo Afinidades Musicales");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(700, 500);
        setLocationRelativeTo(null);

        JTabbedPane tabbedPane = new JTabbedPane();
        getContentPane().add(tabbedPane, BorderLayout.CENTER);

        tabbedPane.addTab("Formulario", crearPanelFormulario());
        tabbedPane.addTab("Usuarios", crearPanelUsuarios());
        tabbedPane.addTab("Estadísticas", crearPanelEstadisticas());
    }
  

    private JComboBox<Integer> crearComboInteres() {
        return new JComboBox<>(new Integer[]{1,2,3,4,5});
    }

    private JPanel crearPanelFormulario() {
    JPanel panelFormulario = new JPanel(new GridBagLayout());
    GridBagConstraints gbc = new GridBagConstraints();
    gbc.insets = new Insets(5, 5, 5, 5);
    gbc.anchor = GridBagConstraints.WEST;

    JLabel lblNombre = new JLabel("Nombre:");
    gbc.gridx = 0; gbc.gridy = 0;
    panelFormulario.add(lblNombre, gbc);

    txtNombre = new JTextField(20);
    gbc.gridx = 1; gbc.gridy = 0;
    panelFormulario.add(txtNombre, gbc);

    JLabel lblTango = new JLabel("Interés en Tango (1-5):");
    gbc.gridx = 0; gbc.gridy = 1;
    panelFormulario.add(lblTango, gbc);

    comboTango = crearComboInteres();
    gbc.gridx = 1; gbc.gridy = 1;
    panelFormulario.add(comboTango, gbc);

    JLabel lblFolclore = new JLabel("Interés en Folclore (1-5):");
    gbc.gridx = 0; gbc.gridy = 2;
    panelFormulario.add(lblFolclore, gbc);

    comboFolclore = crearComboInteres();
    gbc.gridx = 1; gbc.gridy = 2;
    panelFormulario.add(comboFolclore, gbc);

    JLabel lblRock = new JLabel("Interés en Rock Nacional (1-5):");
    gbc.gridx = 0; gbc.gridy = 3;
    panelFormulario.add(lblRock, gbc);

    comboRock = crearComboInteres();
    gbc.gridx = 1; gbc.gridy = 3;
    panelFormulario.add(comboRock, gbc);

    JLabel lblUrbano = new JLabel("Interés en Género Urbano (1-5):");
    gbc.gridx = 0; gbc.gridy = 4;
    panelFormulario.add(lblUrbano, gbc);

    comboUrbano = crearComboInteres();
    gbc.gridx = 1; gbc.gridy = 4;
    panelFormulario.add(comboUrbano, gbc);

    btnAgregarUsuario = new JButton("Agregar Usuario");
    gbc.gridx = 0; gbc.gridy = 5; gbc.gridwidth = 2;
    panelFormulario.add(btnAgregarUsuario, gbc);
    
    return panelFormulario;
   }
    
    private JPanel crearPanelUsuarios() {
    JPanel panelUsuarios = new JPanel(new BorderLayout());
    tablaModelo = new DefaultTableModel(new Object[]{"Nombre", "Tango", "Folclore", "Rock", "Urbano"}, 0);
    tablaUsuarios = new JTable(tablaModelo);
    JScrollPane scrollUsuarios = new JScrollPane(tablaUsuarios);
    panelUsuarios.add(scrollUsuarios, BorderLayout.CENTER);
    return panelUsuarios;
 }
    
    private JPanel crearPanelEstadisticas() {
        JPanel panelEstadisticas = new JPanel(new BorderLayout());
        areaEstadisticas = new JTextArea();
        areaEstadisticas.setEditable(false);
        JScrollPane scrollEstadisticas = new JScrollPane(areaEstadisticas);
        panelEstadisticas.add(scrollEstadisticas, BorderLayout.CENTER);

        btnEjecutarAlgoritmo = new JButton("Ejecutar Algoritmo");
        panelEstadisticas.add(btnEjecutarAlgoritmo, BorderLayout.SOUTH);

        return panelEstadisticas;
        }

    public void mostrarUsuarios(List<UsuarioMusical> usuarios) {
        tablaModelo.setRowCount(0);
        for (UsuarioMusical u : usuarios) {
            tablaModelo.addRow(new Object[]{
                u.getNombre(), u.getTango(), u.getFolclore(), u.getRock(), u.getUrbano()
            });
        }
    }

    public void mostrarEstadisticas(String texto) {
        areaEstadisticas.setText(texto);
    }
   
    public void setControlador(Controlador controlador) {
        this.controlador = controlador;
    }
    
    @Override
    public void actualizar() {
        if (controlador == null) return;
        mostrarUsuarios(controlador.getUsuarios());
        mostrarEstadisticas(controlador.getEstadisticas());
    }

    // Getters
    public JTextField getTxtNombre() { return txtNombre; }
    public JComboBox<Integer> getComboTango() { return comboTango; }
    public JComboBox<Integer> getComboFolclore() { return comboFolclore; }
    public JComboBox<Integer> getComboRock() { return comboRock; }
    public JComboBox<Integer> getComboUrbano() { return comboUrbano; }
    public JTable getTablaUsuarios() { return tablaUsuarios; }
    public JTextArea getAreaEstadisticas() { return areaEstadisticas; }
    public JButton getBtnAgregarUsuario() { return btnAgregarUsuario; }
    public JButton getBtnEjecutarAlgoritmo() { return btnEjecutarAlgoritmo; }
}
