package modelo;

import static org.junit.jupiter.api.Assertions.*;
import java.util.*;
import org.junit.jupiter.api.Test;

public class ConstructorDeGruposPorMSTTest {

    @Test
    public void testConstruirGruposBasico() {
    
        UsuarioMusical u1 = new UsuarioMusical("Ana", 5, 3, 2, 4);
        UsuarioMusical u2 = new UsuarioMusical("Juan", 4, 3, 2, 5);
        UsuarioMusical u3 = new UsuarioMusical("Luis", 1, 8, 6, 2);

        List<UsuarioMusical> usuarios = Arrays.asList(u1, u2, u3);

    
        Arista a1 = new Arista(u1, u2); 
        Arista a2 = new Arista(u2, u2); 
        Arista a3 = new Arista(u1, u3); 

        List<Arista> aristas = Arrays.asList(a1, a2, a3);

        
        ConstructorDeGruposPorMST constructor = new ConstructorDeGruposPorMST();
        List<Set<UsuarioMusical>> grupos = constructor.construir(usuarios, aristas);

      
        assertEquals(2, grupos.size(), "Debe haber exactamente dos grupos formados.");

     
        long totalUsuarios = grupos.stream().mapToLong(Set::size).sum();
        assertEquals(usuarios.size(), totalUsuarios, "Todos los usuarios deben estar agrupados.");
    }

    @Test
    public void testConstruirSinAristas() {
        UsuarioMusical u1 = new UsuarioMusical("Ana", 5, 3, 2, 4);
        UsuarioMusical u2 = new UsuarioMusical("Juan", 4, 3, 2, 5);

        List<UsuarioMusical> usuarios = Arrays.asList(u1, u2);

        ConstructorDeGruposPorMST constructor = new ConstructorDeGruposPorMST();
        List<Set<UsuarioMusical>> grupos = constructor.construir(usuarios, new ArrayList<>());

     
        assertEquals(2, grupos.size(), "Cada usuario debe quedar en su propio grupo.");
    }

    @Test
    public void testConstruirConUsuariosVacios() {
        ConstructorDeGruposPorMST constructor = new ConstructorDeGruposPorMST();
        List<Set<UsuarioMusical>> grupos = constructor.construir(Collections.emptyList(), new ArrayList<>());

        assertTrue(grupos.isEmpty(), "Si no hay usuarios, la lista de grupos debe estar vacía.");
    }
}
