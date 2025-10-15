package modelo;

import java.util.*;

public class ConstructorDeGruposPorMST {

    public List<Set<UsuarioMusical>> construir(List<UsuarioMusical> usuarios, List<Arista> aristas) {
        if (usuarios == null || usuarios.isEmpty()) return Collections.emptyList();
        if (aristas == null) aristas = new ArrayList<>();

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

        // Elimina la arista de mayor peso
        if (!mst.isEmpty()) {
            Arista max = Collections.max(mst);
            mst.remove(max);
        }

        // Construcción de grupos (componentes)
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

    // Clase interna auxiliar (Union-Find)
    private static class UnionFind {
        private int[] parent;
        private int[] rank;

        public UnionFind(int n) {
            parent = new int[n];
            rank = new int[n];
            for (int i = 0; i < n; i++) parent[i] = i;
        }

        public int find(int x) {
            if (parent[x] != x)
                parent[x] = find(parent[x]);
            return parent[x];
        }

        public void union(int a, int b) {
            int ra = find(a);
            int rb = find(b);
            if (ra == rb) return;

            if (rank[ra] < rank[rb]) parent[ra] = rb;
            else if (rank[ra] > rank[rb]) parent[rb] = ra;
            else {
                parent[rb] = ra;
                rank[ra]++;
            }
        }
    }
}
