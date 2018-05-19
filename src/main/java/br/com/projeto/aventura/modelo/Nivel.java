package br.com.projeto.aventura.modelo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class Nivel implements Serializable {

	private static final long serialVersionUID = -7366405306829190127L;

	@Column(name = "level", unique = false, nullable = false)
	private Long level;

	@Column(name = "experiencia", unique = false, nullable = false)
	private Long experiencia;

	public Long getLevel() {
		return level;
	}

	public void setLevel(Long level) {
		this.level = level;
	}

	public Long getExperiencia() {
		return experiencia;
	}

	public void setExperiencia(Long experiencia) {
		this.experiencia = experiencia;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
