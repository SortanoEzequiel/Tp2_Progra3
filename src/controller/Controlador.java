package controller;

import modelo.GrafoUsuarios;
import modelo.UsuarioMusical;
import vista.VistaPrincipal;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Controlador implements ActionListener {

    private GrafoUsuarios modelo;
    private VistaPrincipal vista;

    public Controlador(GrafoUsuarios modelo, VistaPrincipal vista) {
        this.modelo = modelo;
        this.vista = vista;

        vista.getBtnAgregarUsuario().addActionListener(this);
        vista.getBtnEjecutarAlgoritmo().addActionListener(this);

        modelo.addObserver(vista);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == vista.getBtnAgregarUsuario()) {
            agregarUsuario();
        } else if (e.getSource() == vista.getBtnEjecutarAlgoritmo()) {
            modelo.ejecutarAlgoritmo();
        }
    }

    private void agregarUsuario() {
        String nombre = vista.getTxtNombre().getText();
        int tango = (int) vista.getComboTango().getSelectedItem();
        int folclore = (int) vista.getComboFolclore().getSelectedItem();
        int rock = (int) vista.getComboRock().getSelectedItem();
        int urbano = (int) vista.getComboUrbano().getSelectedItem();

        UsuarioMusical usuario = new UsuarioMusical(nombre, tango, folclore, rock, urbano);
        modelo.agregarUsuario(usuario);

        vista.getTxtNombre().setText("");
        vista.getComboTango().setSelectedIndex(0);
        vista.getComboFolclore().setSelectedIndex(0);
        vista.getComboRock().setSelectedIndex(0);
        vista.getComboUrbano().setSelectedIndex(0);
    }
}
