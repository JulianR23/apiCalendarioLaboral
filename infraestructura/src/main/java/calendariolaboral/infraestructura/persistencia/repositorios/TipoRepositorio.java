package calendariolaboral.infraestructura.persistencia.repositorios;

import calendariolaboral.core.repositorios.ITipoRepositorio;
import calendariolaboral.dominio.entidades.Tipo;
import calendariolaboral.infraestructura.persistencia.entidades.TipoEntidad;
import calendariolaboral.infraestructura.persistencia.mapeadores.TipoMapeador;
import calendariolaboral.infraestructura.persistencia.repositorios.interfaces.ITipoJpaRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class TipoRepositorio implements ITipoRepositorio {

    @Autowired
    private ITipoJpaRepositorio jpaRepositorio;

    @Override
    public List<Tipo> listar() {
        return jpaRepositorio.findAll().stream()
                .map(TipoMapeador::toModelo)
                .collect(Collectors.toList());
    }

    @Override
    public Tipo obtener(Long id) {
        return jpaRepositorio.findById(id)
                .map(TipoMapeador::toModelo)
                .orElse(null);
    }

    @Override
    public List<Tipo> buscar(String tipo) {
        return jpaRepositorio.findByTipoContainingIgnoreCase(tipo)
                .stream()
                .map(TipoMapeador::toModelo)
                .collect(Collectors.toList());
    }

    @Override
    public Tipo guardar(Tipo tipo) {
        TipoEntidad entidad = TipoMapeador.toEntidad(tipo);
        return TipoMapeador.toModelo(jpaRepositorio.save(entidad));
    }

    @Override
    public void eliminar(Long id) {
        jpaRepositorio.deleteById(id);
    }

    @Override
    public boolean existe(Long id) {
        return jpaRepositorio.existsById(id);
    }
}
