package br.com.projeto.aventura.modelo;

public enum SituacaoEnum {
	INICIADO(1), PAUSA(2), COMPLETA(3), ESPERA(4), CANCELADO(5), DESISTENCIA(6);

	private Integer item;

	private SituacaoEnum(int item) {
		this.item = item;
	}

	public Integer getItem() {
		return item;
	}

	public void setItem(Integer item) {
		this.item = item;
	}

	// INICIADO(1), PAUSA(2), COMPLETA(3), ESPERA(4), CANCELADO(5), DESISTENCIA(6);
	public static SituacaoEnum getIntValue(int i) {
		switch (i) {
		case 1:
			return INICIADO;
		case 2:
			return PAUSA;
		case 3:
			return COMPLETA;
		case 4:
			return ESPERA;
		case 5:
			return CANCELADO;
		case 6:
			return DESISTENCIA;
		default:
			return null;
		}
	}

}
