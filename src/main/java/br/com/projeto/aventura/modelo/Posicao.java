package br.com.projeto.aventura.modelo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "Posicao")
public class Posicao implements Serializable {

	private static final long serialVersionUID = -3080946180624798351L;

	@Id
	private long idUnidade;

	@Column(name = "latitude")
	private double latitude;

	@Column(name = "longitude")
	private double longitude;

	public long getIdUnidade() {
		return idUnidade;
	}

	public void setIdUnidade(long idUnidade) {
		this.idUnidade = idUnidade;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
