package calendariolaboral.presentacion.controladores;

import calendariolaboral.core.servicios.ITipoServicio;
import calendariolaboral.dominio.entidades.Tipo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tipos")
@CrossOrigin(origins = "*")
public class TipoControlador {

    @Autowired
    private ITipoServicio servicio;

    @RequestMapping(value = "/listar", method = RequestMethod.GET)
    public List<Tipo> listar() {
        return servicio.listar();
    }

    @RequestMapping(value = "/obtener/{id}", method = RequestMethod.GET)
    public Tipo obtener(@PathVariable Long id) {
        return servicio.obtener(id);
    }

    @RequestMapping(value = "/buscar/{tipo}", method = RequestMethod.GET)
    public List<Tipo> buscar(@PathVariable String tipo) {
        return servicio.buscar(tipo);
    }

    @RequestMapping(value = "/agregar", method = RequestMethod.POST)
    public Tipo crear(@RequestBody Tipo tipo) {
        return servicio.agregar(tipo);
    }

    @RequestMapping(value = "/modificar", method = RequestMethod.PUT)
    public Tipo actualizar(@RequestBody Tipo tipo) {
        return servicio.modificar(tipo);
    }

    @RequestMapping(value = "/eliminar/{id}", method = RequestMethod.DELETE)
    public boolean eliminar(@PathVariable Long id) {
        return servicio.eliminar(id);
    }
}
