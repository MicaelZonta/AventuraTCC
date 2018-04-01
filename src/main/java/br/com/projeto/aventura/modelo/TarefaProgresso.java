package br.com.projeto.aventura.modelo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "Tarefa_Progresso")
public class TarefaProgresso implements Serializable {

	private static final long serialVersionUID = -1767930338175401513L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long idTarefaProgresso;

	@Column(name = "idMissaoProgresso", unique = false, nullable = false)
	private long idMissaoProgresso;

	@Column(name = "idTarefa", unique = false, nullable = false)
	private long idTarefa;

	@Enumerated(EnumType.ORDINAL)
	private Situacao idSituacao;

	public long getIdTarefaProgresso() {
		return idTarefaProgresso;
	}

	public void setIdTarefaProgresso(long idTarefaProgresso) {
		this.idTarefaProgresso = idTarefaProgresso;
	}

	public long getIdMissaoProgresso() {
		return idMissaoProgresso;
	}

	public void setIdMissaoProgresso(long idMissaoProgresso) {
		this.idMissaoProgresso = idMissaoProgresso;
	}

	public long getIdTarefa() {
		return idTarefa;
	}

	public void setIdTarefa(long idTarefa) {
		this.idTarefa = idTarefa;
	}

	public Situacao getIdSituacao() {
		return idSituacao;
	}

	public void setIdSituacao(Situacao idSituacao) {
		this.idSituacao = idSituacao;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
