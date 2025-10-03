package controller;

import modelo.GrafoUsuarios;
import modelo.UsuarioMusical;
import vista.VistaPrincipal;
import vista.PanelFormulario;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JOptionPane;

public class Controlador implements ActionListener {

    private static GrafoUsuarios modelo;
    private VistaPrincipal vista;
    

    public Controlador(GrafoUsuarios modelo, VistaPrincipal vista) {
        this.modelo = modelo;
        this.vista = vista;

        vista.getPanelFormulario().getBtnAgregarUsuario().addActionListener(this);
        vista.getPanelEstadisticas().getBtnEjecutarAlgoritmo().addActionListener(this);
        
        vista.setControlador(this);
        modelo.addObserver(vista);
    }
    public static GrafoUsuarios getModelo() {
        return modelo;
    }
    
    public List<UsuarioMusical> getUsuarios() {
        return modelo.getUsuarios();
    }

    private void agregarUsuario() {
        PanelFormulario form = vista.getPanelFormulario();

        String nombre = form.getTxtNombre().getText();
        if (!nombre.matches("[A-Za-zÁÉÍÓÚáéíóúÑñ ]{2,}")) {
            JOptionPane.showMessageDialog(null,
                    "El nombre debe tener al menos 2 letras y solo contener caracteres válidos.");
            return;
        }

        int tango = (int) form.getComboTango().getSelectedItem();
        int folclore = (int) form.getComboFolclore().getSelectedItem();
        int rock = (int) form.getComboRock().getSelectedItem();
        int urbano = (int) form.getComboUrbano().getSelectedItem();

        UsuarioMusical usuario = new UsuarioMusical(nombre, tango, folclore, rock, urbano);
        modelo.agregarUsuario(usuario);

        // Resetear campos
        form.getTxtNombre().setText("");
        form.getComboTango().setSelectedIndex(0);
        form.getComboFolclore().setSelectedIndex(0);
        form.getComboRock().setSelectedIndex(0);
        form.getComboUrbano().setSelectedIndex(0);
    }
    
    
    @Override
    public void actionPerformed(ActionEvent e) {
    	 Object source = e.getSource();

         if (source == vista.getPanelFormulario().getBtnAgregarUsuario()) {
             agregarUsuario();
         } else if (source == vista.getPanelEstadisticas().getBtnEjecutarAlgoritmo()) {
             modelo.ejecutarAlgoritmo();
         }
    }
    
	public String getEstadisticas() {		
		return modelo.calcularEstadisticas();
	}
}
