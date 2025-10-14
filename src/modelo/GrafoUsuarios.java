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
    private void notificarCambio() {
        for (Observador obs : observadores) {
            obs.actualizar();
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

        EncontrarUnion eu = new EncontrarUnion(usuarios.size());
        Map<UsuarioMusical, Integer> indice = new HashMap<>();
        for (int i = 0; i < usuarios.size(); i++) {
            indice.put(usuarios.get(i), i);
        }

        List<Arista> mst = new ArrayList<>();
        for (Arista e : edges) {
            int u = indice.get(e.getU1());
            int v = indice.get(e.getU2());
            if (eu.encontrar(u) != eu.encontrar(v)) {
                eu.union(u, v);
                mst.add(e);
            }
        }

      
        if (!mst.isEmpty()) {
            Arista max = Collections.max(mst);
            mst.remove(max);
        }

   
        Map<Integer, Set<UsuarioMusical>> comps = new HashMap<>();
        EncontrarUnion ufFinal = new EncontrarUnion(usuarios.size());
        for (Arista e : mst) {
            int u = indice.get(e.getU1());
            int v = indice.get(e.getU2());
            ufFinal.union(u, v);
        }

        for (UsuarioMusical u : usuarios) {
            int comp = ufFinal.encontrar(indice.get(u));
            comps.computeIfAbsent(comp, k -> new HashSet<>()).add(u);
        }

        return new ArrayList<>(comps.values());
    }

    public String calcularEstadisticas() {
        if (usuarios.isEmpty()) return "No hay usuarios cargados.";

        return EstadisticasUsuarios.generarReporte(grupos, usuarios);
    }
 
    
 
    private static class EncontrarUnion {
        private int[] padre;       
        private int[] altura;      

        public EncontrarUnion(int cantidadElementos) {
            padre = new int[cantidadElementos];
            altura = new int[cantidadElementos];
            for (int i = 0; i < cantidadElementos; i++) {
                padre[i] = i; 
            }
        }

     
        public int encontrar(int elemento) {
            if (padre[elemento] != elemento) {
                padre[elemento] = encontrar(padre[elemento]); 
            }
            return padre[elemento];
        }

  
        public void union(int elementoA, int elementoB) {
            int raizA = encontrar(elementoA);
            int raizB = encontrar(elementoB);

            if (raizA == raizB) return; 

            // Unión por altura (rank)
            if (altura[raizA] < altura[raizB]) {
                padre[raizA] = raizB;
            } else if (altura[raizA] > altura[raizB]) {
                padre[raizB] = raizA;
            } else {
                padre[raizB] = raizA;
                altura[raizA]++;
            }
        }
    }

}
