package br.com.projeto.aventura.repositorio;

import br.com.projeto.aventura.modelo.Aventureiro;
import br.com.projeto.aventura.modelo.Usuario;

public interface AventureiroRepositorio {

	public Usuario cadastrarAventureiro(Aventureiro aventureiro) throws Exception;

	public Usuario editarAventureiro(Aventureiro aventureiro) throws Exception;

}
