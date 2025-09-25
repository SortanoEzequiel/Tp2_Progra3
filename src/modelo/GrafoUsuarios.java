package modelo;

import java.util.*;

public class GrafoUsuarios {
    private List<UsuarioMusical> usuarios = new ArrayList<>();
    private List<Arista> aristas = new ArrayList<>();

    public void agregarUsuario(UsuarioMusical u) {
        usuarios.add(u);
    }

    public void construirGrafoCompleto() {
        aristas.clear();
        for (int i = 0; i < usuarios.size(); i++) {
            for (int j = i + 1; j < usuarios.size(); j++) {
                aristas.add(new Arista(usuarios.get(i), usuarios.get(j)));
            }
        }
    }

    public List<Arista> obtenerAGMKruskal() {
        construirGrafoCompleto();
        Collections.sort(aristas);
        Map<UsuarioMusical, UsuarioMusical> padre = new HashMap<>();
        for (UsuarioMusical u : usuarios) padre.put(u, u);

        List<Arista> agm = new ArrayList<>();
        for (Arista a : aristas) {
            UsuarioMusical raiz1 = encontrar(padre, a.getU1());
            UsuarioMusical raiz2 = encontrar(padre, a.getU2());
            if (!raiz1.equals(raiz2)) {
                agm.add(a);
                padre.put(raiz1, raiz2);
            }
        }
        return agm;
    }

    private UsuarioMusical encontrar(Map<UsuarioMusical, UsuarioMusical> padre, UsuarioMusical u) {
        if (!padre.get(u).equals(u)) {
            padre.put(u, encontrar(padre, padre.get(u)));
        }
        return padre.get(u);
    }

    public List<List<UsuarioMusical>> dividirEnDosGrupos() {
        List<Arista> agm = obtenerAGMKruskal();
        agm.sort(Collections.reverseOrder());
        agm.remove(0); // Eliminar la arista de mayor peso

        Map<UsuarioMusical, List<UsuarioMusical>> grupos = new HashMap<>();
        for (UsuarioMusical u : usuarios) grupos.put(u, new ArrayList<>(List.of(u)));

        for (Arista a : agm) {
            List<UsuarioMusical> g1 = grupos.get(a.getU1());
            List<UsuarioMusical> g2 = grupos.get(a.getU2());
            if (g1 != g2) {
                g1.addAll(g2);
                for (UsuarioMusical u : g2) grupos.put(u, g1);
            }
        }

        Set<List<UsuarioMusical>> resultado = new HashSet<>(grupos.values());
        return new ArrayList<>(resultado);
    }
}
