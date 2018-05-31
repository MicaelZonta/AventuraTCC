package br.com.projeto.aventura.modelo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

@Entity(name = "Unidade_Habilidade")
public class UnidadeHabilidade implements Serializable {

	private static final long serialVersionUID = -2780288257651463089L;

	@EmbeddedId
	UnidadeHabilidadeChave unidadeHabilidadeChave;

	@Column(name = "nivel", nullable = false)
	Long nivel;

	@Column(name = "exp", nullable = false)
	Integer exp;

	public UnidadeHabilidadeChave getUnidadeHabilidadeChave() {
		return unidadeHabilidadeChave;
	}

	public void setUnidadeHabilidadeChave(UnidadeHabilidadeChave unidadeHabilidadeChave) {
		this.unidadeHabilidadeChave = unidadeHabilidadeChave;
	}

	public Long getNivel() {
		return nivel;
	}

	public void setNivel(Long nivel) {
		this.nivel = nivel;
	}

	public Integer getExp() {
		return exp;
	}

	public void setExp(Integer exp) {
		this.exp = exp;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
