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
        ConstructorDeGruposPorMST constructor = new ConstructorDeGruposPorMST();
        grupos = constructor.construir(usuarios, aristas);
        notificarCambio();
    }

    public List<UsuarioMusical> getUsuarios() {
        return usuarios;
    }

    public List<Set<UsuarioMusical>> getGrupos() {
        return grupos;
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

 
   

}
