package br.com.projeto.aventura.modelo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "Situacao")
public class Situacao implements Serializable {

	private static final long serialVersionUID = 4567960800237213451L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idSituacao;

	@Column(name = "nome", nullable = false, length = 20)
	String nome;

	public Situacao() {
		
	}
	
	public Integer getIdSituacao() {
		return idSituacao;
	}

	public void setIdSituacao(Integer idSituacao) {
		this.idSituacao = idSituacao;
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
