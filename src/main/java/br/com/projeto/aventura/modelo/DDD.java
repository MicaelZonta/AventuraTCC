package br.com.projeto.aventura.modelo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "DDD")
public class DDD {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	long idDDD;

	@Column(name = "numero", unique = true, nullable = false, length = 3)
	String numero;

	public long getIdDDD() {
		return idDDD;
	}

	public void setIdDDD(long idDDD) {
		this.idDDD = idDDD;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

}
