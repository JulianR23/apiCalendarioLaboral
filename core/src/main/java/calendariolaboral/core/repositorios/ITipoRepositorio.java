package calendariolaboral.core.repositorios;

import calendariolaboral.dominio.entidades.Tipo;
import java.util.List;

public interface ITipoRepositorio {
    List<Tipo> listar();
    Tipo obtener(Long id);
    List<Tipo> buscar(String tipo);
    Tipo guardar(Tipo tipo);
    void eliminar(Long id);
    boolean existe(Long id);
}
