package modelo;

import java.util.Set;
import java.util.List;
import java.util.stream.Collectors;

public class EstadisticasUsuarios {

    public static String generarReporte(List<Set<UsuarioMusical>> grupos, List<UsuarioMusical> usuarios) {
        

        StringBuilder sb = new StringBuilder();
        sb.append("Cantidad de usuarios: ").append(usuarios.size()).append("\n\n");

        for (int i = 0; i < grupos.size(); i++) {
            Set<UsuarioMusical> grupo = grupos.get(i);
            sb.append("Grupo ").append(i + 1).append(" (").append(grupo.size()).append(" usuarios):\n");

            double promTango = grupo.stream().mapToInt(UsuarioMusical::getTango).average().orElse(0);
            double promFolclore = grupo.stream().mapToInt(UsuarioMusical::getFolclore).average().orElse(0);
            double promRock = grupo.stream().mapToInt(UsuarioMusical::getRock).average().orElse(0);
            double promUrbano = grupo.stream().mapToInt(UsuarioMusical::getUrbano).average().orElse(0);

            sb.append("  Prom Tango: ").append(String.format("%.2f", promTango)).append("\n");
            sb.append("  Prom Folclore: ").append(String.format("%.2f", promFolclore)).append("\n");
            sb.append("  Prom Rock: ").append(String.format("%.2f", promRock)).append("\n");
            sb.append("  Prom Urbano: ").append(String.format("%.2f", promUrbano)).append("\n");

            sb.append("  Usuarios: ").append(
                    grupo.stream().map(UsuarioMusical::getNombre).collect(Collectors.joining(", "))
            ).append("\n\n");
        }

        return sb.toString();
    }
}
