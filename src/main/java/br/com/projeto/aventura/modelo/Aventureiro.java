package br.com.projeto.aventura.modelo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;

import br.com.projeto.aventura.modelo.abstrato.Unidade;

@Entity(name = "Aventureiro")
@PrimaryKeyJoinColumn(name = "idUnidade")
public class Aventureiro extends Unidade {

	@Column(name = "idPessoa", nullable = false, length = 9)
	long idPessoa;

	public long getIdPessoa() {
		return idPessoa;
	}

	public void setIdPessoa(long idPessoa) {
		this.idPessoa = idPessoa;
	}

}
