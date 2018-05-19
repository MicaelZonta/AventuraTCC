package br.com.projeto.aventura.modelo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "Tarefa_Progresso")
public class TarefaProgresso implements Serializable {

	private static final long serialVersionUID = -1767930338175401513L;

	@EmbeddedId
	AvaliacaoChave idAvaliacao;

	@Column(name = "idTarefa", unique = false, nullable = false)
	private Long idTarefa;

	@Enumerated(EnumType.ORDINAL)
	private Situacao idSituacao;

	public AvaliacaoChave getIdAvaliacao() {
		return idAvaliacao;
	}

	public void setIdAvaliacao(AvaliacaoChave idAvaliacao) {
		this.idAvaliacao = idAvaliacao;
	}

	public Long getIdTarefa() {
		return idTarefa;
	}

	public void setIdTarefa(Long idTarefa) {
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
