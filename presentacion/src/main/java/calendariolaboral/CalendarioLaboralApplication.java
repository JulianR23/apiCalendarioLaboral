package calendariolaboral;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {
    "calendariolaboral"
})
public class CalendarioLaboralApplication {

    public static void main(String[] args) {
        SpringApplication.run(CalendarioLaboralApplication.class, args);
    }
}
