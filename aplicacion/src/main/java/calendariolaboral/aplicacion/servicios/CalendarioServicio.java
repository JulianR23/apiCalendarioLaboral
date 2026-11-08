package calendariolaboral.aplicacion.servicios;

import calendariolaboral.core.integracion.IFestivoServicioExterno;
import calendariolaboral.core.repositorios.ICalendarioRepositorio;
import calendariolaboral.core.repositorios.ITipoRepositorio;
import calendariolaboral.core.servicios.ICalendarioServicio;
import calendariolaboral.dominio.dtos.FestivoDto;
import calendariolaboral.dominio.entidades.Calendario;
import calendariolaboral.dominio.entidades.Tipo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

@Service
public class CalendarioServicio implements ICalendarioServicio {

    // Ids en la tabla 'tipo'
    private static final long TIPO_LABORAL = 1L;
    private static final long TIPO_FIN_SEMANA = 2L;
    private static final long TIPO_FESTIVO = 3L;

    private static final Locale ESPANOL = new Locale("es", "CO");

    @Autowired
    private ICalendarioRepositorio calendarioRepositorio;

    @Autowired
    private ITipoRepositorio tipoRepositorio;

    @Autowired
    private IFestivoServicioExterno festivoServicioExterno;

    @Override
    public List<Calendario> listar(int anio) {
        return calendarioRepositorio.listarPorAnio(anio);
    }

    @Override
    public boolean generar(int anio) {
        // 1. Consultar todos los festivos del año una sola vez a la API de Festivos
        Map<LocalDate, String> festivos = new HashMap<>();
        for (FestivoDto festivo : festivoServicioExterno.obtenerFestivos(anio)) {
            String fechaStr = festivo.getFecha();
            if (fechaStr != null && fechaStr.length() >= 10) {
                fechaStr = fechaStr.substring(0, 10);
                festivos.put(LocalDate.parse(fechaStr), festivo.getFestivo());
            }
        }

        asegurarTiposIniciales();
        Tipo tipoLaboral = obtenerTipo(TIPO_LABORAL);
        Tipo tipoFinSemana = obtenerTipo(TIPO_FIN_SEMANA);
        Tipo tipoFestivo = obtenerTipo(TIPO_FESTIVO);

        // 2. Si el año ya fue generado, se reutilizan los ids para actualizar
        Map<LocalDate, Long> existentes = new HashMap<>();
        for (Calendario dia : calendarioRepositorio.listarPorAnio(anio)) {
            existentes.put(dia.getFecha(), dia.getId());
        }

        // 3. Recorrer y clasificar todos los días del año
        LocalDate fecha = LocalDate.of(anio, 1, 1);
        while (fecha.getYear() == anio) {
            Tipo tipo;

            if (festivos.containsKey(fecha)) {
                tipo = tipoFestivo;
            } else if (fecha.getDayOfWeek() == DayOfWeek.SATURDAY || fecha.getDayOfWeek() == DayOfWeek.SUNDAY) {
                tipo = tipoFinSemana;
            } else {
                tipo = tipoLaboral;
            }

            // Según el enunciado del Taller 1 (pág. 6), la descripción es el día de la semana (ej. "Domingo", "Lunes")
            String descripcion = nombreDia(fecha);

            calendarioRepositorio.guardar(new Calendario(existentes.get(fecha), fecha, tipo, descripcion));
            fecha = fecha.plusDays(1);
        }
        return true;
    }

    private void asegurarTiposIniciales() {
        if (tipoRepositorio.listar().isEmpty()) {
            tipoRepositorio.guardar(new Tipo(null, "Día laboral"));
            tipoRepositorio.guardar(new Tipo(null, "Fin de semana"));
            tipoRepositorio.guardar(new Tipo(null, "Día festivo"));
        }
    }

    private Tipo obtenerTipo(long id) {
        Tipo tipo = tipoRepositorio.obtener(id);
        if (tipo == null) {
            throw new IllegalStateException("No existe el tipo con id " + id
                    + " en la tabla 'tipo' (se requieren 1=Día laboral, 2=Fin de semana, 3=Día festivo)");
        }
        return tipo;
    }

    private String nombreDia(LocalDate fecha) {
        String nombre = fecha.getDayOfWeek().getDisplayName(TextStyle.FULL, ESPANOL);
        return nombre.substring(0, 1).toUpperCase(ESPANOL) + nombre.substring(1);
    }
}
