package main;

import controlador.Controlador;
import modelo.GrafoUsuarios;
import vista.VistaPrincipal;

public class AppMain {
	 public static void main(String[] args) {
	        
	        GrafoUsuarios modelo = new GrafoUsuarios();

	        VistaPrincipal vista = new VistaPrincipal();
	        vista.setVisible(true);

	        new Controlador(modelo, vista);
	    }
}
