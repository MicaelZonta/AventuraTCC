package br.com.projeto.aventura.modelo.abstrato;

import br.com.projeto.aventura.modelo.Posicao;

public abstract class Unidade {

	Posicao posicao;

	public Posicao getPosicao() {
		return posicao;
	}

	public void setPosicao(Posicao posicao) {
		this.posicao = posicao;
	}

	
	
}
