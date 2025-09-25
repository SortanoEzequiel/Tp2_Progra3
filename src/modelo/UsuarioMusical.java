package modelo;

public class UsuarioMusical {
    private String nombre;
    private int tango, folclore, rock, urbano;

    public UsuarioMusical(String nombre, int tango, int folclore, int rock, int urbano) {
        this.nombre = nombre;
        this.tango = tango;
        this.folclore = folclore;
        this.rock = rock;
        this.urbano = urbano;
    }

    public String getNombre() { return nombre; }

    public int calcularSimilaridad(UsuarioMusical otroUsuario) {
        return Math.abs(this.tango - otroUsuario.tango)
             + Math.abs(this.folclore - otroUsuario.folclore)
             + Math.abs(this.rock - otroUsuario.rock)
             + Math.abs(this.urbano - otroUsuario.urbano);
    }

    @Override
    public String toString() {
        return nombre;
    }
}

