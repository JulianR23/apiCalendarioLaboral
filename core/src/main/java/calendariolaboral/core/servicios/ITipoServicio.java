package calendariolaboral.core.servicios;

import calendariolaboral.dominio.entidades.Tipo;
import java.util.List;

public interface ITipoServicio {
    List<Tipo> listar();
    Tipo obtener(Long id);
    List<Tipo> buscar(String tipo);
    Tipo agregar(Tipo tipo);
    Tipo modificar(Tipo tipo);
    boolean eliminar(Long id);
}
