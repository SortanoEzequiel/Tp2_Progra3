package modelo;

import java.util.*;
import java.util.stream.Collectors;

public class GrafoUsuarios {

    private List<UsuarioMusical> usuarios;
    private List<Arista> aristas;
    private List<Set<UsuarioMusical>> grupos; // dos grupos resultantes
    private List<Observador> observadores;

    public GrafoUsuarios() {
        usuarios = new ArrayList<>();
        aristas = new ArrayList<>();
        grupos = new ArrayList<>();
        observadores = new ArrayList<>();
    }
    
    public void addObserver(Observador obs) {
        observadores.add(obs);
    }
    
    public void agregarUsuario(UsuarioMusical usuario) {
        usuarios.add(usuario);
        recalcularAristas();
        notificarCambio();
    }

    private void recalcularAristas() {
        aristas.clear();
        for (int i = 0; i < usuarios.size(); i++) {
            for (int j = i + 1; j < usuarios.size(); j++) {
                aristas.add(new Arista(usuarios.get(i), usuarios.get(j)));
            }
        }
    }

    public void ejecutarAlgoritmo() {
        if (usuarios.size() < 2) {
            grupos.clear();
            notificarCambio();
            return;
        }

        recalcularAristas();
        grupos = construirGruposPorMST();
        notificarCambio();
    }

    public List<UsuarioMusical> getUsuarios() {
        return usuarios;
    }

    public List<Set<UsuarioMusical>> getGrupos() {
        return grupos;
    }

    List<Set<UsuarioMusical>> construirGruposPorMST() {
        // Kruskal para MST
        List<Arista> edges = new ArrayList<>(aristas);
        Collections.sort(edges);

        UnionFind uf = new UnionFind(usuarios.size());
        Map<UsuarioMusical, Integer> indice = new HashMap<>();
        for (int i = 0; i < usuarios.size(); i++) {
            indice.put(usuarios.get(i), i);
        }

        List<Arista> mst = new ArrayList<>();
        for (Arista e : edges) {
            int u = indice.get(e.getU1());
            int v = indice.get(e.getU2());
            if (uf.find(u) != uf.find(v)) {
                uf.union(u, v);
                mst.add(e);
            }
        }

        // Eliminar la arista de mayor peso
        if (!mst.isEmpty()) {
            Arista max = Collections.max(mst);
            mst.remove(max);
        }

        // Crear componentes (dos grupos)
        Map<Integer, Set<UsuarioMusical>> comps = new HashMap<>();
        UnionFind ufFinal = new UnionFind(usuarios.size());
        for (Arista e : mst) {
            int u = indice.get(e.getU1());
            int v = indice.get(e.getU2());
            ufFinal.union(u, v);
        }

        for (UsuarioMusical u : usuarios) {
            int comp = ufFinal.find(indice.get(u));
            comps.computeIfAbsent(comp, k -> new HashSet<>()).add(u);
        }

        return new ArrayList<>(comps.values());
    }

    public String calcularEstadisticas() {
        if (usuarios.isEmpty()) return "No hay usuarios cargados.";

        return EstadisticasUsuarios.generarReporte(grupos, usuarios);
    }
 
    private void notificarCambio() {
        for (Observador obs : observadores) {
            obs.actualizar();
        }
    }

    // clase auxiliar Union-Find 
    private static class UnionFind {
        private int[] parent, rank;

        public UnionFind(int n) {
            parent = new int[n];
            rank = new int[n];
            for (int i = 0; i < n; i++) parent[i] = i;
        }

        public int find(int x) {
            if (parent[x] != x) parent[x] = find(parent[x]);
            return parent[x];
        }

        public void union(int x, int y) {
            int rx = find(x), ry = find(y);
            if (rx == ry) return;
            if (rank[rx] < rank[ry]) {
                parent[rx] = ry;
            } else if (rank[rx] > rank[ry]) {
                parent[ry] = rx;
            } else {
                parent[ry] = rx;
                rank[rx]++;
            }
        }
    }
}
