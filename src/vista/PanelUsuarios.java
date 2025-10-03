package vista;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class PanelUsuarios extends JPanel {

    private JTable tablaUsuarios;
    private DefaultTableModel tablaModelo;

    public PanelUsuarios() {
        setLayout(new BorderLayout());

        tablaModelo = new DefaultTableModel(new Object[]{"Nombre", "Tango", "Folclore", "Rock", "Urbano"}, 0);
        tablaUsuarios = new JTable(tablaModelo);

        JScrollPane scrollUsuarios = new JScrollPane(tablaUsuarios);
        add(scrollUsuarios, BorderLayout.CENTER);
    }

    // Métodos para manejar la tabla
    public void limpiarTabla() {
        tablaModelo.setRowCount(0);
    }

    public void agregarUsuario(Object[] fila) {
        tablaModelo.addRow(fila);
    }

    public JTable getTablaUsuarios() { return tablaUsuarios; }
    public DefaultTableModel getTablaModelo() { return tablaModelo; }
}
