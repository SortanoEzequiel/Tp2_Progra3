package modelo;

public class Arista implements Comparable<Arista> {
    private UsuarioMusical u1, u2;
    private int peso;

    public Arista(UsuarioMusical u1, UsuarioMusical u2) {
        this.u1 = u1;
        this.u2 = u2;
        this.peso = u1.calcularSimilaridad(u2);
    }

    public UsuarioMusical getU1() { return u1; }
    public UsuarioMusical getU2() { return u2; }
    public int getPeso() { return peso; }

    @Override
    public int compareTo(Arista otra) {
        return Integer.compare(this.peso, otra.peso);
    }

    @Override
    public String toString() {
        return u1 + " - " + u2 + " (" + peso + ")";
    }
}
