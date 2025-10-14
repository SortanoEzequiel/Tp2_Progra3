package modelo;

public class UsuarioMusical {
    private String nombre;
    private int tango;
    private int folclore;
    private int rock;
    private int urbano;

    public UsuarioMusical(String nombre, int tango, int folclore, int rock, int urbano) {
        this.nombre = nombre;
        this.tango = tango;
        this.folclore = folclore;
        this.rock = rock;
        this.urbano = urbano;
    }

    public String getNombre() {
        return nombre;
    }

    public int getTango() {
        return tango;
    }

    public int getFolclore() {
        return folclore;
    }

    public int getRock() {
        return rock;
    }

    public int getUrbano() {
        return urbano;
    }

    
    public int similaridad(UsuarioMusical otroUsuario) {
        return Math.abs(this.tango - otroUsuario.tango)
             + Math.abs(this.folclore - otroUsuario.folclore)
             + Math.abs(this.rock - otroUsuario.rock)
             + Math.abs(this.urbano - otroUsuario.urbano);
    }
}
