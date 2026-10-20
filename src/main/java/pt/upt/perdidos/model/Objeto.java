package pt.upt.perdidos.model;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Objeto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    private String tipo;

    @Column(nullable = false)
    private String estado;

    @ManyToOne
    private Cliente dono;

    @ManyToOne(optional = false)
    private Espaco espaco;

    @OneToMany(mappedBy = "objeto", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<HistoricoEstado> historico = new ArrayList<>();

    public Objeto() {}

    public Objeto(String nome, String tipo, String estado, Cliente dono, Espaco espaco) {
        this.nome = nome;
        this.tipo = tipo;
        this.dono = dono;
        this.espaco = espaco;
        mudarEstado(estado);
    }

    public void mudarEstado(String novoEstado) {
        if (!"PERDIDO".equals(novoEstado) && !"ACHADO".equals(novoEstado) && !"ENTREGUE".equals(novoEstado)) {
            throw new IllegalArgumentException("Estado inválido: " + novoEstado + " (usar PERDIDO, ACHADO ou ENTREGUE)");
        }
        this.estado = novoEstado;
        historico.add(new HistoricoEstado(this, novoEstado));
    }

    public Long getId() { return id; }
    public String getNome() { return nome; }
    public String getTipo() { return tipo; }
    public String getEstado() { return estado; }
    public Cliente getDono() { return dono; }
    public Espaco getEspaco() { return espaco; }
    public List<HistoricoEstado> getHistorico() { return historico; }
}
