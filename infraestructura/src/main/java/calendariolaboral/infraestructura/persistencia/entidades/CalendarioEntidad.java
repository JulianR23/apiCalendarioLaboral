package calendariolaboral.infraestructura.persistencia.entidades;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "calendario")
public class CalendarioEntidad {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "fecha", unique = true)
    private LocalDate fecha;

    @ManyToOne
    @JoinColumn(name = "idtipo", referencedColumnName = "id")
    private TipoEntidad tipo;

    @Column(name = "descripcion", length = 100)
    private String descripcion;

    public CalendarioEntidad() {}

    public CalendarioEntidad(Long id, LocalDate fecha, TipoEntidad tipo, String descripcion) {
        this.id = id;
        this.fecha = fecha;
        this.tipo = tipo;
        this.descripcion = descripcion;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public LocalDate getFecha() { return fecha; }
    public void setFecha(LocalDate fecha) { this.fecha = fecha; }
    public TipoEntidad getTipo() { return tipo; }
    public void setTipo(TipoEntidad tipo) { this.tipo = tipo; }
    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
}
