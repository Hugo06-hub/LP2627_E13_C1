package pt.upt.perdidos.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Registo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // ENTRADA ou SAIDA
    @Column(nullable = false)
    private String tipo;

    // dia e hora em que o cartão foi lido
    @Column(nullable = false)
    private LocalDateTime dataHora = LocalDateTime.now();

    @ManyToOne(optional = false)
    private Cliente cliente;

    @ManyToOne(optional = false)
    private Espaco espaco;

    public Registo() {}

    public Registo(Cliente cliente, Espaco espaco, String tipo) {
        this.cliente = cliente;
        this.espaco = espaco;
        this.tipo = tipo;
    }

    public Long getId() { return id; }
    public String getTipo() { return tipo; }
    public LocalDateTime getDataHora() { return dataHora; }
    public Cliente getCliente() { return cliente; }
    public Espaco getEspaco() { return espaco; }
}
