package br.com.projeto.aventura.modelo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "DDD")
public class DDD implements Serializable {

	private static final long serialVersionUID = 8966058726143788854L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long idDDD;

	@Column(name = "numero", unique = true, nullable = false, length = 3)
	String numero;

	public Long getIdDDD() {
		return idDDD;
	}

	public void setIdDDD(Long idDDD) {
		this.idDDD = idDDD;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
