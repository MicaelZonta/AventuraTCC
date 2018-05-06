package br.com.projeto.aventura.modelo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class TarefaProgressoChave implements Serializable {

	private static final long serialVersionUID = -3449878135607852393L;

	@Column(name = "idMissaoProgresso", unique = false, nullable = false)
	private Long idMissaoProgresso;

	@Column(name = "idMissaoTarefa", unique = false, nullable = false)
	private Long idMissaoTarefa;

	public Long getIdMissaoProgresso() {
		return idMissaoProgresso;
	}

	public void setIdMissaoProgresso(Long idMissaoProgresso) {
		this.idMissaoProgresso = idMissaoProgresso;
	}

	public Long getIdMissaoTarefa() {
		return idMissaoTarefa;
	}

	public void setIdMissaoTarefa(Long idMissaoTarefa) {
		this.idMissaoTarefa = idMissaoTarefa;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
