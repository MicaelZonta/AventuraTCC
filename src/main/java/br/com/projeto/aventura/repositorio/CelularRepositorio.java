package br.com.projeto.aventura.repositorio;

import br.com.projeto.aventura.modelo.Celular;
import br.com.projeto.aventura.modelo.Usuario;

public interface CelularRepositorio {

	public Usuario cadastrarCelular(Celular celular) throws Exception;

	public Usuario editarCelular(Celular celular) throws Exception;

}
