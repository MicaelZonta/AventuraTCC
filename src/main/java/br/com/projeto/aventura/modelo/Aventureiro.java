package br.com.projeto.aventura.modelo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;

import br.com.projeto.aventura.modelo.abstrato.Unidade;

@Entity(name = "Aventureiro")
@PrimaryKeyJoinColumn(name = "idUnidade")
public class Aventureiro extends Unidade implements Serializable {

	private static final long serialVersionUID = -7757841948234782607L;

	@Column(name = "idPessoa", nullable = false, length = 9)
	private Long idPessoa;

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
