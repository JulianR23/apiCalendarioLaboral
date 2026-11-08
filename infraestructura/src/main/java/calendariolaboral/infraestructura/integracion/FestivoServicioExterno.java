package calendariolaboral.infraestructura.integracion;

import calendariolaboral.core.integracion.IFestivoServicioExterno;
import calendariolaboral.dominio.dtos.FestivoDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

/**
 * Cliente HTTP de la API de Festivos (Express + MongoDB).
 * Consume: GET {servicio-externo.festivos.url}/obtener/{anio}
 */
@Service
public class FestivoServicioExterno implements IFestivoServicioExterno {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${servicio-externo.festivos.url}")
    private String urlBase;

    @Override
    public List<FestivoDto> obtenerFestivos(int anio) {
        String url = urlBase + "/obtener/" + anio;
        try {
            FestivoDto[] festivos = restTemplate.getForObject(url, FestivoDto[].class);
            return festivos != null ? Arrays.asList(festivos) : List.of();
        } catch (RestClientException e) {
            throw new IllegalStateException("No fue posible consultar la API de Festivos en " + url, e);
        }
    }
}
