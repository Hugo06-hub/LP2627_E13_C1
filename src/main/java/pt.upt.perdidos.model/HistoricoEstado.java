package pt.upt.perdidos.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class HistoricoEstado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String estado;

    @Column(nullable = false)
    private LocalDateTime data = LocalDateTime.now();

    @ManyToOne(optional = false)
    private Objeto objeto;

    public HistoricoEstado() {}

    public HistoricoEstado(Objeto objeto, String estado) {
        this.objeto = objeto;
        this.estado = estado;
    }

    public String getEstado() { return estado; }
    public LocalDateTime getData() { return data; }
}
