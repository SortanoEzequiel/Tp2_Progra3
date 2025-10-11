package vista;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;

public class PanelUsuarios extends JPanel {

    private JTable tablaUsuarios;
    private DefaultTableModel tablaModelo;

    public PanelUsuarios() {
        setLayout(new BorderLayout());
        setBackground(new Color(245, 247, 250));
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // === Título ===
        JLabel lblTitulo = new JLabel("Listado de Usuarios", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 20));
        lblTitulo.setForeground(new Color(40, 40, 40));
        lblTitulo.setBorder(BorderFactory.createEmptyBorder(0, 0, 15, 0));
        add(lblTitulo, BorderLayout.NORTH);

    
        tablaModelo = new DefaultTableModel(
                new Object[]{"Nombre", "Tango", "Folclore", "Rock", "Urbano"}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // evita edición directa
            }
        };

        tablaUsuarios = new JTable(tablaModelo);
        tablaUsuarios.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        tablaUsuarios.setRowHeight(28);
        tablaUsuarios.setGridColor(new Color(220, 220, 220));
        tablaUsuarios.setShowHorizontalLines(true);
        tablaUsuarios.setShowVerticalLines(false);
        tablaUsuarios.setFillsViewportHeight(true);
        tablaUsuarios.setBackground(Color.WHITE);
        tablaUsuarios.setSelectionBackground(new Color(220, 235, 245));
        tablaUsuarios.setSelectionForeground(Color.BLACK);

      
        JTableHeader cabecera = tablaUsuarios.getTableHeader();
        cabecera.setFont(new Font("Segoe UI", Font.BOLD, 14));
        cabecera.setBackground(new Color(70, 130, 180));
        cabecera.setForeground(Color.WHITE);
        cabecera.setPreferredSize(new Dimension(cabecera.getWidth(), 30));

      
        DefaultTableCellRenderer centro = new DefaultTableCellRenderer();
        centro.setHorizontalAlignment(SwingConstants.CENTER);
        for (int i = 1; i < tablaUsuarios.getColumnCount(); i++) {
            tablaUsuarios.getColumnModel().getColumn(i).setCellRenderer(centro);
        }

       
        JScrollPane scrollUsuarios = new JScrollPane(tablaUsuarios);
        scrollUsuarios.getViewport().setBackground(Color.WHITE);
        scrollUsuarios.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200)));

        add(scrollUsuarios, BorderLayout.CENTER);
    }


    public void limpiarTabla() {
        tablaModelo.setRowCount(0);
    }

    public void agregarUsuario(Object[] fila) {
        tablaModelo.addRow(fila);
    }

    public JTable getTablaUsuarios() {
        return tablaUsuarios;
    }

    public DefaultTableModel getTablaModelo() {
        return tablaModelo;
    }
}
