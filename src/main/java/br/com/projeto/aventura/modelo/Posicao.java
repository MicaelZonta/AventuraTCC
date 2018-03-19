package br.com.projeto.aventura.modelo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "Posicao")
public class Posicao {

	@Id
	long idUnidade;

	@Column(name = "latitude")
	double latitude;

	@Column(name = "longitude")
	double longitude;

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

}
