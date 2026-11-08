package calendariolaboral.presentacion.handlers;

import calendariolaboral.presentacion.dtos.ErrorRespuesta;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.ResourceAccessException;

@ControllerAdvice
public class ExcepcionesGlobalesHandler {

    @ExceptionHandler(HttpClientErrorException.BadRequest.class)
    public ResponseEntity<?> manejarBadRequest(HttpClientErrorException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                new ErrorRespuesta("Error de validación desde API de Festivos", ex.getResponseBodyAsString()));
    }

    @ExceptionHandler(HttpClientErrorException.class)
    public ResponseEntity<?> manejarHttpClient(HttpClientErrorException ex) {
        return ResponseEntity.status(ex.getStatusCode()).body(
                new ErrorRespuesta("Error en API de Festivos", ex.getResponseBodyAsString()));
    }

    @ExceptionHandler({ HttpServerErrorException.class, ResourceAccessException.class })
    public ResponseEntity<?> manejarServerError(Exception ex) {
        return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body(
                new ErrorRespuesta("Error al comunicarse con el servicio externo de festivos", ex.getMessage()));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> manejarGeneral(Exception ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                new ErrorRespuesta("Error interno", ex.getMessage()));
    }
}
