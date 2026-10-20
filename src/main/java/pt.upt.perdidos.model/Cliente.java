package pt.upt.perdidos.model;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idC;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private int num;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String pass;

    @OneToMany(mappedBy = "dono")
    private List<Levantamento> levantamentos = new ArrayList<>();

    @OneToMany(mappedBy = "cliente")
    private List<Registo> registos = new ArrayList<>();

    @ManyToMany
    @JoinTable(name = "reclamacao", joinColumns = @JoinColumn(name = "cliente_id"), inverseJoinColumns = @JoinColumn(name = "objeto_id"))
    private Set<Objeto> reclamados = new HashSet<>();

    public Cliente() {}

    public Cliente(String nome, int num, String email, String pass) {
        this.nome = nome;
        this.num = num;
        this.email = email;
        this.pass = pass;
    }

    public Long getIdC() { return idC; }
    public String getNome() { return nome; }
    public int getNum() { return num; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public List<Levantamento> getLevantamentos() { return levantamentos; }
    public List<Registo> getRegistos() { return registos; }
    public Set<Objeto> getReclamados() { return reclamados; }
}
