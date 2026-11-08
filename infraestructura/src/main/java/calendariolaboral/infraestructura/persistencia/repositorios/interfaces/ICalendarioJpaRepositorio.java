package calendariolaboral.infraestructura.persistencia.repositorios.interfaces;

import calendariolaboral.infraestructura.persistencia.entidades.CalendarioEntidad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ICalendarioJpaRepositorio extends JpaRepository<CalendarioEntidad, Long> {
    @Query("SELECT c FROM CalendarioEntidad c WHERE EXTRACT(YEAR FROM c.fecha) = ?1 ORDER BY c.fecha ASC")
    List<CalendarioEntidad> listarPorAnio(int anio);
}
