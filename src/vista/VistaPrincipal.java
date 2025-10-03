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

	
    private DefaultTableModel tablaModelo;
    private Controlador controlador;
    private PanelFormulario panelFormulario;
    private PanelUsuarios panelUsuarios;
    private PanelEstadisticas panelEstadisticas;

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
        
        panelFormulario = new PanelFormulario();
        panelUsuarios = new PanelUsuarios();
        panelEstadisticas = new PanelEstadisticas();

        tabbedPane.addTab("Formulario", panelFormulario);
        tabbedPane.addTab("Usuarios",  panelUsuarios);
        tabbedPane.addTab("Estadísticas",  panelEstadisticas);
    }
  
    public void mostrarUsuarios(List<UsuarioMusical> usuarios) {
        panelUsuarios.limpiarTabla();
        for (UsuarioMusical u : usuarios) {
            panelUsuarios.agregarUsuario(new Object[]{
                u.getNombre(), u.getTango(), u.getFolclore(), u.getRock(), u.getUrbano()
            });
        }
    }

    public void mostrarEstadisticas(String texto) {
        panelEstadisticas.mostrarEstadisticas(texto);
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

 // Getters para el controlador
    public PanelFormulario getPanelFormulario() { return panelFormulario; }
    public PanelUsuarios getPanelUsuarios() { return panelUsuarios; }
    public PanelEstadisticas getPanelEstadisticas() { return panelEstadisticas; }
}
