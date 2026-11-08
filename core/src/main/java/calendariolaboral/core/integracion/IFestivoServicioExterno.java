package calendariolaboral.core.integracion;

import calendariolaboral.dominio.dtos.FestivoDto;
import java.util.List;

/**
 * Contrato para consumir la API externa de Festivos.
 * La implementación (HTTP) vive en la capa de infraestructura.
 */
public interface IFestivoServicioExterno {
    List<FestivoDto> obtenerFestivos(int anio);
}
