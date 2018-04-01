package br.com.projeto.aventura.modelo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "Habilidade")
public class Habilidade implements Serializable {

	private static final long serialVersionUID = 3939043522266357718L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long idHabilidade;

	@Column(name = "nome", unique = true, nullable = false, length = 25)
	private String nome;

	public long getIdHabilidade() {
		return idHabilidade;
	}

	public void setIdHabilidade(long idHabilidade) {
		this.idHabilidade = idHabilidade;
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
