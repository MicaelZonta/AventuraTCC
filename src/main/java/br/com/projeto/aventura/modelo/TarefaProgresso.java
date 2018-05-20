package br.com.projeto.aventura.modelo;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity(name = "Tarefa_Progresso")
public class TarefaProgresso implements Serializable {

	private static final long serialVersionUID = -1767930338175401513L;

	@EmbeddedId
	TarefaProgressoChave tarefaProgressoChave;

	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.DETACH)
	@JoinColumn(name = "idSituacao")
	private Situacao situacao;

	public TarefaProgressoChave getTarefaProgressoChave() {
		return tarefaProgressoChave;
	}

	public void setTarefaProgressoChave(TarefaProgressoChave tarefaProgressoChave) {
		this.tarefaProgressoChave = tarefaProgressoChave;
	}

	public Situacao getSituacao() {
		return situacao;
	}

	public void setSituacao(Situacao situacao) {
		this.situacao = situacao;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
