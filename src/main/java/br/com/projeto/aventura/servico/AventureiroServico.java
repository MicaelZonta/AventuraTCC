package br.com.projeto.aventura.servico;

import br.com.projeto.aventura.modelo.Aventureiro;
import br.com.projeto.aventura.modelo.Usuario;

public interface AventureiroServico {

	public Usuario cadastrarAventureiro(Aventureiro aventureiro) throws Exception;

	public Usuario editarAventureiro(Aventureiro aventureiro) throws Exception;

}
