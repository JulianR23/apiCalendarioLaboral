package calendariolaboral.core.repositorios;

import calendariolaboral.dominio.entidades.Calendario;
import java.util.List;

public interface ICalendarioRepositorio {
    List<Calendario> listar();
    List<Calendario> listarPorAnio(int anio);
    Calendario obtener(Long id);
    Calendario guardar(Calendario calendario);
}
