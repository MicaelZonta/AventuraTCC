package br.com.projeto.aventura.repositorio;

import br.com.projeto.aventura.modelo.Aventureiro;

public interface AventureiroRepositorio {

	public Aventureiro buscarAventureiroPorIdPessoa(long idPessoa) throws Exception;	
	
	public Aventureiro cadastrarAventureiro(Aventureiro aventureiro) throws Exception;
	
	public Aventureiro atualizarAventureiro(Aventureiro aventureiro) throws Exception;
}
