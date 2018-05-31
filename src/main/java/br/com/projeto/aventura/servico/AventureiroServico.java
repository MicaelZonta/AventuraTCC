package br.com.projeto.aventura.servico;

import br.com.projeto.aventura.modelo.Avaliacao;
import br.com.projeto.aventura.modelo.Aventureiro;

public interface AventureiroServico {

	public Aventureiro evoluirHabilidades(Avaliacao avaliacao) throws Exception;

	public Aventureiro encontrarAventureiroPorIdPessoa(long idPessoa) throws Exception;

	public Aventureiro cadastrarAventureiro(long idPessoa, double latitude, double longitude) throws Exception;

	public Aventureiro atualizarAventureiro(Aventureiro aventureiro) throws Exception;
}
