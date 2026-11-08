package calendariolaboral.infraestructura.persistencia.repositorios;

import calendariolaboral.core.repositorios.ICalendarioRepositorio;
import calendariolaboral.dominio.entidades.Calendario;
import calendariolaboral.infraestructura.persistencia.entidades.CalendarioEntidad;
import calendariolaboral.infraestructura.persistencia.mapeadores.CalendarioMapeador;
import calendariolaboral.infraestructura.persistencia.repositorios.interfaces.ICalendarioJpaRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CalendarioRepositorio implements ICalendarioRepositorio {

    @Autowired
    private ICalendarioJpaRepositorio jpaRepositorio;

    @Override
    public List<Calendario> listar() {
        return jpaRepositorio.findAll().stream()
                .map(CalendarioMapeador::toModelo)
                .collect(Collectors.toList());
    }

    @Override
    public List<Calendario> listarPorAnio(int anio) {
        return jpaRepositorio.listarPorAnio(anio).stream()
                .map(CalendarioMapeador::toModelo)
                .collect(Collectors.toList());
    }

    @Override
    public Calendario obtener(Long id) {
        return jpaRepositorio.findById(id)
                .map(CalendarioMapeador::toModelo)
                .orElse(null);
    }

    @Override
    public Calendario guardar(Calendario calendario) {
        CalendarioEntidad entidad = CalendarioMapeador.toEntidad(calendario);
        return CalendarioMapeador.toModelo(jpaRepositorio.save(entidad));
    }
}
