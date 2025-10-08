package modelo;

import static org.junit.Assert.*;
import java.util.*;
import org.junit.*;

public class GrafoUsuariosTest {

	 private GrafoUsuarios grafo;

	    @Before
	    public void setUp() {
	        grafo = new GrafoUsuarios();
	    }
	    
	    private void cuatroUsuarios() {
	    	UsuarioMusical User1 = new UsuarioMusical("pepe", 5, 4, 1, 1);
	    	UsuarioMusical User2 = new UsuarioMusical("micho", 1, 1, 5, 1);
	    	UsuarioMusical User3 = new UsuarioMusical("pablo", 2, 1, 5, 2);
	    	UsuarioMusical User4 = new UsuarioMusical("laura", 3, 1, 3, 5);
	    	
	    	grafo.agregarUsuario(User1);
	    	grafo.agregarUsuario(User2);
	    	grafo.agregarUsuario(User3);
	    	grafo.agregarUsuario(User4);
	    }

	    @Test
	    public void testConstruirGruposPorMST_ListaUsuariosVacia() {
	        // Set up - no agrega ningun usario
	        
	        // Exercise
	        List<Set<UsuarioMusical>> grupos = grafo.construirGruposPorMST();
	        
	        // Verify
	        assertNotNull("El método nunca debería retornar null", grupos);
	        assertTrue("La lista de grupos debería estar vacía", grupos.isEmpty());
	    }
	    
	    @Test
	    public void testAgregarUsuario() {
	    	//SetUp
	    	UsuarioMusical nuevoUser = new UsuarioMusical("jose", 2, 1, 5, 1);
	    	grafo.agregarUsuario(nuevoUser);
	    	
	    	//Exercise
	    	int totalUsuarios = grafo.getUsuarios().size();
	    	
	    	//Verify
	    	assertEquals(1, totalUsuarios);
	    	assertTrue(grafo.getUsuarios().contains(nuevoUser));
	    }
	    
	    @Test
	    public void testUsuariosEnGrupos() {
	    	//SetUp
	    	cuatroUsuarios();
	    	
	    	//Excersice
	    	grafo.ejecutarAlgoritmo();
	    	
	    	//Verify
	    	
	    }
	    
	    @Test
	    public void testCantidadDeGrupos() {
	    	//SetUp
	    	cuatroUsuarios();
	    	
	    	//Exercise
	    	grafo.ejecutarAlgoritmo();
	    	int totalGrupos = grafo.getGrupos().size();
	    	
	    	//Verify
	    	assertEquals(2, totalGrupos);
	    }
	    
	    @Test
	    public void testConstruirGruposPorMST_ListaConUsuarios() {
	        // Set up
	    	cuatroUsuarios();
	    	
	        // Exercise
	        List<Set<UsuarioMusical>> grupos = grafo.construirGruposPorMST();
	        
	        // Verify
	        assertNotNull("El método nunca debería retornar null", grupos);
	        assertFalse("La lista de grupos debería no estar vacía", grupos.isEmpty());
	    }
	    
}
