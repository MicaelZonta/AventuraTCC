package br.com.projeto.aventura.modelo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class AvaliacaoChave implements Serializable {

	private static final long serialVersionUID = -927695184418103481L;

	@Column(name = "idMissaoProgresso", unique = false, nullable = false)
	private long idMissaoProgresso;

	@Column(name = "idPessoa", unique = false, nullable = false)
	private long idPessoa;

	public long getIdMissaoProgresso() {
		return idMissaoProgresso;
	}

	public void setIdMissaoProgresso(long idMissaoProgresso) {
		this.idMissaoProgresso = idMissaoProgresso;
	}

	public long getIdPessoa() {
		return idPessoa;
	}

	public void setIdPessoa(long idPessoa) {
		this.idPessoa = idPessoa;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
