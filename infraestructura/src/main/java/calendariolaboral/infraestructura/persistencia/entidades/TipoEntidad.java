package calendariolaboral.infraestructura.persistencia.entidades;

import jakarta.persistence.*;

@Entity
@Table(name = "tipo")
public class TipoEntidad {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "tipo", length = 50, unique = true)
    private String tipo;

    public TipoEntidad() {}

    public TipoEntidad(Long id, String tipo) {
        this.id = id;
        this.tipo = tipo;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }
}
