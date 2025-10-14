package modelo;

import java.util.*;
import java.util.stream.Collectors;

public class GrafoUsuarios {

    private List<UsuarioMusical> usuarios;
    private List<Arista> aristas;
    private List<Set<UsuarioMusical>> grupos; 
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
        // Kruskal
        List<Arista> todasLasAristas = new ArrayList<>(aristas);
        Collections.sort(todasLasAristas);

        EncontrarUnion unionInicial = new EncontrarUnion(usuarios.size());
        Map<UsuarioMusical, Integer> indice = new HashMap<>();
        for (int i = 0; i < usuarios.size(); i++) {
            indice.put(usuarios.get(i), i);
        }

        List<Arista> arbolMST = new ArrayList<>();
        for (Arista arista : todasLasAristas) {
            int u1 = indice.get(arista.getU1());
            int u2 = indice.get(arista.getU2());
            if (unionInicial.encontrar(u1) != unionInicial.encontrar(u2)) {
            	unionInicial.union(u1, u2);
                arbolMST.add(arista);
            }
        }

      
        if (!arbolMST.isEmpty()) {
            Arista aristaMaxima = Collections.max(arbolMST);
            arbolMST.remove(aristaMaxima);
        }

   
        Map<Integer, Set<UsuarioMusical>> componentes = new HashMap<>();
        EncontrarUnion unionFinal = new EncontrarUnion(usuarios.size());
        for (Arista arista : arbolMST) {
            int u1 = indice.get(arista.getU1());
            int u2 = indice.get(arista.getU2());
            unionFinal.union(u1, u2);
        }

        for (UsuarioMusical um : usuarios) {
            int componente = unionFinal.encontrar(indice.get(um));
            componentes.computeIfAbsent(componente, k -> new HashSet<>()).add(um);
        }

        return new ArrayList<>(componentes.values());
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
