package calendariolaboral.infraestructura.persistencia.mapeadores;

import calendariolaboral.dominio.entidades.Calendario;
import calendariolaboral.infraestructura.persistencia.entidades.CalendarioEntidad;

public class CalendarioMapeador {

    public static Calendario toModelo(CalendarioEntidad entidad) {
        if (entidad == null) {
            return null;
        }
        return new Calendario(
                entidad.getId(),
                entidad.getFecha(),
                TipoMapeador.toModelo(entidad.getTipo()),
                entidad.getDescripcion()
        );
    }

    public static CalendarioEntidad toEntidad(Calendario dominio) {
        if (dominio == null) {
            return null;
        }
        return new CalendarioEntidad(
                dominio.getId(),
                dominio.getFecha(),
                TipoMapeador.toEntidad(dominio.getTipo()),
                dominio.getDescripcion()
        );
    }
}
