package calendariolaboral.core.servicios;

import calendariolaboral.dominio.entidades.Calendario;
import java.util.List;

public interface ICalendarioServicio {
    boolean generar(int anio);
    List<Calendario> listar(int anio);
}
