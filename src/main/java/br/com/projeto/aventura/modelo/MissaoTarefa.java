package br.com.projeto.aventura.modelo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "Missao_Tarefa")
public class MissaoTarefa implements Serializable {

	private static final long serialVersionUID = -5344315529683911621L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idMissaoTarefa;

	@Column(name = "idMissao", unique = false, nullable = false)
	private Long idMissao;

	@Column(name = "nome", unique = false, nullable = false, length = 20)
	private String nome;

	@Column(name = "descricao", unique = false, nullable = false, length = 100)
	private String descricao;

	public Long getIdMissaoTarefa() {
		return idMissaoTarefa;
	}

	public void setIdMissaoTarefa(Long idMissaoTarefa) {
		this.idMissaoTarefa = idMissaoTarefa;
	}

	public Long getIdMissao() {
		return idMissao;
	}

	public void setIdMissao(Long idMissao) {
		this.idMissao = idMissao;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
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
