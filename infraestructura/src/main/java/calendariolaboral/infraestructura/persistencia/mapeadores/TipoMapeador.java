package calendariolaboral.infraestructura.persistencia.mapeadores;

import calendariolaboral.dominio.entidades.Tipo;
import calendariolaboral.infraestructura.persistencia.entidades.TipoEntidad;

public class TipoMapeador {

    public static Tipo toModelo(TipoEntidad entidad) {
        if (entidad == null) {
            return null;
        }
        return new Tipo(entidad.getId(), entidad.getTipo());
    }

    public static TipoEntidad toEntidad(Tipo dominio) {
        if (dominio == null) {
            return null;
        }
        return new TipoEntidad(dominio.getId(), dominio.getTipo());
    }
}
