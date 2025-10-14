package modelo;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import java.util.*;

public class EstadisticaUsuariosTest {
    private UsuarioMusical uA, uB, uC, uD;
    private List<UsuarioMusical> usuarios;
    private List<Set<UsuarioMusical>> grupos;

    @Before
    public void setup() {
        uA = new UsuarioMusical("A", 4, 2, 3, 5);
        uB = new UsuarioMusical("B", 3, 2, 1, 1);
        uC = new UsuarioMusical("C", 1, 4, 3, 4);
        uD = new UsuarioMusical("D", 2, 4, 3, 2);  // usar valores válidos

        usuarios = Arrays.asList(uA, uB, uC, uD);

        Set<UsuarioMusical> grupo1 = new HashSet<>(Arrays.asList(uA, uB));
        Set<UsuarioMusical> grupo2 = new HashSet<>(Arrays.asList(uC, uD));
        grupos = Arrays.asList(grupo1, grupo2);
    }

    @Test
    public void testCantidadUsuariosEnReporte() {
        String reporte = EstadisticasUsuarios.generarReporte(grupos, usuarios);
        assertNotNull("El reporte no debe ser null", reporte);
        assertTrue("Debe contener la cantidad total de usuarios",
                   reporte.contains("Cantidad de usuarios: 4"));
    }
}
