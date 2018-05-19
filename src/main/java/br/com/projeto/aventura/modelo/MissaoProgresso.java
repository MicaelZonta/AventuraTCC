package br.com.projeto.aventura.modelo;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity(name = "Missao_Progresso")
public class MissaoProgresso implements Serializable {

	private static final long serialVersionUID = 3346346965662625756L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idMissaoProgresso;

	@Column(name = "idMissao", unique = false, nullable = false)
	private Long idMissao;

	@Column(name = "idPessoa", unique = false, nullable = false)
	private Long idPessoa;

	@OneToMany(mappedBy = "idAvaliacao.idMissaoProgresso", targetEntity = Avaliacao.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Avaliacao> avaliacoes;

	@Enumerated(EnumType.ORDINAL)
	private Situacao idSituacao;

	@OneToMany(mappedBy = "idAvaliacao.idMissaoProgresso", targetEntity = Avaliacao.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<TarefaProgresso> tarefas;

	public Long getIdMissaoProgresso() {
		return idMissaoProgresso;
	}

	public void setIdMissaoProgresso(Long idMissaoProgresso) {
		this.idMissaoProgresso = idMissaoProgresso;
	}

	public Long getIdMissao() {
		return idMissao;
	}

	public void setIdMissao(Long idMissao) {
		this.idMissao = idMissao;
	}

	public Long getIdPessoa() {
		return idPessoa;
	}

	public void setIdPessoa(Long idPessoa) {
		this.idPessoa = idPessoa;
	}

	public List<Avaliacao> getAvaliacoes() {
		return avaliacoes;
	}

	public void setAvaliacoes(List<Avaliacao> avaliacoes) {
		this.avaliacoes = avaliacoes;
	}

	public Situacao getIdSituacao() {
		return idSituacao;
	}

	public void setIdSituacao(Situacao idSituacao) {
		this.idSituacao = idSituacao;
	}

	public List<TarefaProgresso> getTarefas() {
		return tarefas;
	}

	public void setTarefas(List<TarefaProgresso> tarefas) {
		this.tarefas = tarefas;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
