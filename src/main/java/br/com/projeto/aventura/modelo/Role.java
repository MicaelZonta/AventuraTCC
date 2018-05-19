package br.com.projeto.aventura.modelo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "Role")
public class Role implements Serializable {

	private static final long serialVersionUID = -1953554222781689759L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long idRole;

	@Column(name = "nome", unique = true, nullable = false, length = 25)
	private String nome;

	public Long getIdRole() {
		return idRole;
	}

	public void setIdRole(Long idRole) {
		this.idRole = idRole;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
