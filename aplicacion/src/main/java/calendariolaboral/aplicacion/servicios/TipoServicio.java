package calendariolaboral.aplicacion.servicios;

import calendariolaboral.core.repositorios.ITipoRepositorio;
import calendariolaboral.core.servicios.ITipoServicio;
import calendariolaboral.dominio.entidades.Tipo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TipoServicio implements ITipoServicio {

    @Autowired
    private ITipoRepositorio repositorio;

    @Override
    public List<Tipo> listar() {
        return repositorio.listar();
    }

    @Override
    public Tipo obtener(Long id) {
        return repositorio.obtener(id);
    }

    @Override
    public List<Tipo> buscar(String tipo) {
        return repositorio.buscar(tipo);
    }

    @Override
    public Tipo agregar(Tipo tipo) {
        tipo.setId(null);
        return repositorio.guardar(tipo);
    }

    @Override
    public Tipo modificar(Tipo tipo) {
        if (repositorio.existe(tipo.getId())) {
            return repositorio.guardar(tipo);
        }
        return null;
    }

    @Override
    public boolean eliminar(Long id) {
        if (repositorio.existe(id)) {
            repositorio.eliminar(id);
            return true;
        }
        return false;
    }
}
