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

	    @Test
	    public void testConstruirGruposPorMST_ListaUsuariosVacia() {
	        // Set up - no agrega ningun usario
	        
	        // Exercise
	        List<Set<UsuarioMusical>> grupos = grafo.construirGruposPorMST();
	        
	        // Verify
	        assertNotNull("El método nunca debería retornar null", grupos);
	        assertTrue("La lista de grupos debería estar vacía", grupos.isEmpty());
	    }

}
