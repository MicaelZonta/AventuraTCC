package br.com.projeto.aventura.modelo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class Nivel implements Serializable {

	private static final long serialVersionUID = -7366405306829190127L;

	@Column(name = "level", unique = false, nullable = false)
	private long level;

	@Column(name = "experiencia", unique = false, nullable = false)
	private long experiencia;

	public long getLevel() {
		return level;
	}

	public void setLevel(long level) {
		this.level = level;
	}

	public long getExperiencia() {
		return experiencia;
	}

	public void setExperiencia(long experiencia) {
		this.experiencia = experiencia;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
