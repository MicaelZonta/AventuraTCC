package br.com.projeto.aventura.modelo;

public enum Situacao {
	INICIADO(0), PAUSA(1), COMPLETA(2), ESPERA(3), CANCELADO(4), DESISTENCIA(5);

	private int item;

	private Situacao(int item) {
		this.item = item;
	}

	public int getItem() {
		return this.item;
	}
}
