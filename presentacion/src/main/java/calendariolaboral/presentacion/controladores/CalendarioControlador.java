package calendariolaboral.presentacion.controladores;

import calendariolaboral.core.servicios.ICalendarioServicio;
import calendariolaboral.dominio.entidades.Calendario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/calendario")
@CrossOrigin(origins = "*")
public class CalendarioControlador {

    @Autowired
    private ICalendarioServicio servicio;

    @RequestMapping(value = "/generar/{anio}", method = RequestMethod.GET)
    public ResponseEntity<Boolean> generar(@PathVariable int anio) {
        try {
            return ResponseEntity.ok(servicio.generar(anio));
        } catch (IllegalStateException e) {
            System.err.println("Error generando calendario " + anio + ": " + e.getMessage());
            return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(false);
        }
    }

    @RequestMapping(value = "/listar/{anio}", method = RequestMethod.GET)
    public List<Calendario> listar(@PathVariable int anio) {
        return servicio.listar(anio);
    }
}
