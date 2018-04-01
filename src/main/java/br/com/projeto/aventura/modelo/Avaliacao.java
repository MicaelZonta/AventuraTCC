package br.com.projeto.aventura.modelo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

@Entity(name = "Avaliacao")
public class Avaliacao implements Serializable {

	private static final long serialVersionUID = 5278827789832873873L;

	@EmbeddedId
	AvaliacaoChave idAvaliacao;

	@Column(name = "estrelas", unique = false, nullable = false)
	private int estrelas;

	@Column(name = "descricao", unique = false, nullable = false, length = 50)
	private String descricao;

	public AvaliacaoChave getIdAvaliacao() {
		return idAvaliacao;
	}

	public void setIdAvaliacao(AvaliacaoChave idAvaliacao) {
		this.idAvaliacao = idAvaliacao;
	}

	public int getEstrelas() {
		return estrelas;
	}

	public void setEstrelas(int estrelas) {
		this.estrelas = estrelas;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
