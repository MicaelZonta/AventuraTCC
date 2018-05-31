package br.com.projeto.aventura.repositorio;

import br.com.projeto.aventura.modelo.Posicao;

public interface PosicaoRepositorio {

	public Posicao cadastrarPosicao(Posicao posicao) throws Exception;

	public Posicao atualizarPosicao(Posicao posicao) throws Exception;
}
