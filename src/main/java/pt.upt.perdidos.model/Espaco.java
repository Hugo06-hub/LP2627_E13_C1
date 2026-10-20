package pt.upt.perdidos.model;

import jakarta.persistence.*;

@Entity
public class Espaco {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String sala;
    
    public Espaco() {}

    public Espaco(String sala) {
        this.sala = sala;
    }

	public String getSala() {
		return sala;
	}

	public void setSala(String sala) {
		this.sala = sala;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "Espaco [id=" + id + ", sala=" + sala + "]";
	}


}
