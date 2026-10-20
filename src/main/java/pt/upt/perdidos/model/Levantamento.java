package pt.upt.perdidos.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Levantamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDateTime data = LocalDateTime.now();

    @ManyToOne(optional = false)
    private Cliente dono;

    @ManyToOne(optional = false)
    private Objeto obj;

    public Levantamento() {}

    public Levantamento(Cliente dono, Objeto obj) {
        this.dono = dono;
        this.obj = obj;
    }

    public Long getId() { return id; }
    public LocalDateTime getData() { return data; }
    public Objeto getObj() { return obj; }
}
