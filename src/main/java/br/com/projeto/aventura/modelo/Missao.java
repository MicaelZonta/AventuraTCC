package br.com.projeto.aventura.modelo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity(name = "Missao")
public class Missao implements Serializable {

	private static final long serialVersionUID = -4819933792322104078L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long idMissao;

	@Column(name = "nome", unique = false, nullable = false, length = 25)
	private String nome;

	@Column(name = "descricao", unique = false, nullable = false, length = 500)
	private String descricao;

	@Column(name = "dataCriacao", unique = false, nullable = false)
	private Date dataCriacao;

	@Column(name = "dataTermino", unique = false, nullable = false)
	private Date dataTermino;

	@Column(name = "maxAventureiro", unique = false, nullable = false)
	private int maxAventureiro;

	@Column(name = "idPessoa", unique = false, nullable = false)
	long idPessoa;

	@OneToMany(mappedBy = "idMissao", targetEntity = MissaoDificuldade.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	List<MissaoDificuldade> listaDificuldades;

	@OneToMany(mappedBy = "idMissao", targetEntity = MissaoProgresso.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	List<MissaoProgresso> listaProgressos;

	@OneToMany(mappedBy = "idMissao", targetEntity = MissaoTarefa.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	List<MissaoTarefa> listaTarefas;

	public long getIdMissao() {
		return idMissao;
	}

	public void setIdMissao(long idMissao) {
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

	public Date getDataCriacao() {
		return dataCriacao;
	}

	public void setDataCriacao(Date dataCriacao) {
		this.dataCriacao = dataCriacao;
	}

	public Date getDataTermino() {
		return dataTermino;
	}

	public void setDataTermino(Date dataTermino) {
		this.dataTermino = dataTermino;
	}

	public int getMaxAventureiro() {
		return maxAventureiro;
	}

	public void setMaxAventureiro(int maxAventureiro) {
		this.maxAventureiro = maxAventureiro;
	}

	public long getIdPessoa() {
		return idPessoa;
	}

	public void setIdPessoa(long idPessoa) {
		this.idPessoa = idPessoa;
	}

	public List<MissaoDificuldade> getListaDificuldades() {
		return listaDificuldades;
	}

	public void setListaDificuldades(List<MissaoDificuldade> listaDificuldades) {
		this.listaDificuldades = listaDificuldades;
	}

	public List<MissaoProgresso> getListaProgressos() {
		return listaProgressos;
	}

	public void setListaProgressos(List<MissaoProgresso> listaProgressos) {
		this.listaProgressos = listaProgressos;
	}

	public List<MissaoTarefa> getListaTarefas() {
		return listaTarefas;
	}

	public void setListaTarefas(List<MissaoTarefa> listaTarefas) {
		this.listaTarefas = listaTarefas;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
