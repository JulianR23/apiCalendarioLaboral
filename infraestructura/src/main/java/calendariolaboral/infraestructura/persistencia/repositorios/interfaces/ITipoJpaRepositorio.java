package calendariolaboral.infraestructura.persistencia.repositorios.interfaces;

import calendariolaboral.infraestructura.persistencia.entidades.TipoEntidad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ITipoJpaRepositorio extends JpaRepository<TipoEntidad, Long> {
    List<TipoEntidad> findByTipoContainingIgnoreCase(String tipo);
}
