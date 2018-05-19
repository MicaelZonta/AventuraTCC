package br.com.projeto.aventura.modelo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class AvaliacaoChave implements Serializable {

	private static final long serialVersionUID = -927695184418103481L;

	@Column(name = "idMissaoProgresso", unique = false, nullable = false)
	private Long idMissaoProgresso;

	@Column(name = "idPessoa", unique = false, nullable = false)
	private Long idPessoa;

	public Long getIdMissaoProgresso() {
		return idMissaoProgresso;
	}

	public void setIdMissaoProgresso(Long idMissaoProgresso) {
		this.idMissaoProgresso = idMissaoProgresso;
	}

	public Long getIdPessoa() {
		return idPessoa;
	}

	public void setIdPessoa(Long idPessoa) {
		this.idPessoa = idPessoa;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
