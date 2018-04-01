package br.com.projeto.aventura.modelo;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity(name = "Missao_Dificuldade")
public class MissaoDificuldade implements Serializable {

	private static final long serialVersionUID = 5601826314412811554L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long idMissaoDificuldade;

	@Column(name = "idMissao", unique = false, nullable = false)
	private long idMissao;

	@Embedded
	private Nivel nivel;

	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "idHabilidade")
	private Habilidade habilidade;

	public long getIdMissaoDificuldade() {
		return idMissaoDificuldade;
	}

	public void setIdMissaoDificuldade(long idMissaoDificuldade) {
		this.idMissaoDificuldade = idMissaoDificuldade;
	}

	public long getIdMissao() {
		return idMissao;
	}

	public void setIdMissao(long idMissao) {
		this.idMissao = idMissao;
	}

	public Nivel getNivel() {
		return nivel;
	}

	public void setNivel(Nivel nivel) {
		this.nivel = nivel;
	}

	public Habilidade getHabilidade() {
		return habilidade;
	}

	public void setHabilidade(Habilidade habilidade) {
		this.habilidade = habilidade;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
