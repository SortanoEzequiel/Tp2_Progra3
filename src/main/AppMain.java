package main;

import controller.Controlador;
import modelo.GrafoUsuarios;
import vista.VistaPrincipal;

public class AppMain {
    public static void main(String[] args) {
        GrafoUsuarios modelo = new GrafoUsuarios();
        VistaPrincipal vista = new VistaPrincipal(modelo);
        new Controlador(modelo, vista);
        vista.setVisible(true);
    }
}
