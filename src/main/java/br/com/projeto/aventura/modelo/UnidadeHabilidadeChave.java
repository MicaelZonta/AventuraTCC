package br.com.projeto.aventura.modelo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class UnidadeHabilidadeChave implements Serializable {

	private static final long serialVersionUID = 6035718893772729424L;

	@Column(name = "idHabilidade", unique = false, nullable = false)
	private Long idHabilidade;

	@Column(name = "idUnidade", unique = false, nullable = false)
	private Long idUnidade;

	public Long getIdHabilidade() {
		return idHabilidade;
	}

	public void setIdHabilidade(Long idHabilidade) {
		this.idHabilidade = idHabilidade;
	}

	public Long getIdUnidade() {
		return idUnidade;
	}

	public void setIdUnidade(Long idUnidade) {
		this.idUnidade = idUnidade;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
