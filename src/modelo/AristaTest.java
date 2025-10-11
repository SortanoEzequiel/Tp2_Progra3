package modelo;

import static org.junit.Assert.*;


import java.util.*;
import org.junit.*;




public class AristaTest {
	private UsuarioMusical uA;
    private UsuarioMusical uB;
    private UsuarioMusical uC;
    private UsuarioMusical uD;
    
    @Before
    public void setup() {
        
        uA = new UsuarioMusical("A",4,2,3,5);
        uB = new UsuarioMusical("B",3,2,1,1);
        uC = new UsuarioMusical("C", 1,4,3,4);
        uD = new UsuarioMusical("D", -1,4,3,-4);
    }
    
    @Test
    public void testPesoMayorQueCinco() {
        Arista aristaAB = new Arista(uA, uB);
        int peso = aristaAB.getPeso();
        // Verificamos que peso esté en rango válido (0 a 5)
        assertTrue(peso > 5);
//        assertTrue(peso > 0);
        
    }

    @Test
    public void testPesoGustosNegativos() {
        Arista aristaAB = new Arista(uA, uD);
        int peso = aristaAB.getPeso();
        // Verificamos que peso esté en rango válido (0 a 5)
        assertTrue(peso > 0);
//        assertTrue(peso > 0);
        
    }

}