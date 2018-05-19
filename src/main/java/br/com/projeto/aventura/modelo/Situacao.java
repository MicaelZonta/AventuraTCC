package br.com.projeto.aventura.modelo;

public enum Situacao {
	INICIADO(1), PAUSA(2), COMPLETA(3), ESPERA(4), CANCELADO(5), DESISTENCIA(6);

	private Integer item;

	private Situacao(int item) {
		this.item = item;
	}

	public Integer getItem() {
		return item;
	}

	public void setItem(Integer item) {
		this.item = item;
	}

}
