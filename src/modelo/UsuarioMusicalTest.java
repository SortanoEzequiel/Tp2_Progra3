package modelo;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class UsuarioMusicalTest {

	 @Test
	    public void testSimilaridadMismoUsuarioDebeSer0() {
	        UsuarioMusical uM = new UsuarioMusical("A", 5, 3, 7, 2);
	        int similaridad = uM.similaridad(uM);
	        assertEquals(0, similaridad);
	    }
	 
	 @Test
	 public void testSimilaridadCalculaCorrectamente() {

	        UsuarioMusical u1 = new UsuarioMusical("U1", 5,5,5,5);
	        UsuarioMusical u2 = new UsuarioMusical("U2", 1,1,1,1);
	        
	        int esperado = 16;
	        int actual = u1.similaridad(u2);
	        assertEquals(esperado, actual);
	    }
}