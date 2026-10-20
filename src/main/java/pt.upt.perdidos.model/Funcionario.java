package pt.upt.perdidos.model;

import jakarta.persistence.*;

@Entity
public class Funcionario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idF;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String pass;

    public Funcionario() {}

    public Funcionario(String nome, String email, String pass) {
        this.nome = nome;
        this.email = email;
        this.pass = pass;
        
    }

	public Long getIdF() {
		return idF;
	}

	public void setIdF(Long idF) {
		this.idF = idF;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	@Override
	public String toString() {
		return "Funcionario [idF=" + idF + ", nome=" + nome + ", email=" + email + ", pass=" + pass + "]";
	}
    
	
    
}
