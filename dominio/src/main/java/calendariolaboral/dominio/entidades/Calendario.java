package calendariolaboral.dominio.entidades;

import java.time.LocalDate;

public class Calendario {
    private Long id;
    private LocalDate fecha;
    private Tipo tipo;
    private String descripcion;

    public Calendario() {
    }

    public Calendario(Long id, LocalDate fecha, Tipo tipo, String descripcion) {
        this.id = id;
        this.fecha = fecha;
        this.tipo = tipo;
        this.descripcion = descripcion;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public Tipo getTipo() {
        return tipo;
    }

    public void setTipo(Tipo tipo) {
        this.tipo = tipo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
