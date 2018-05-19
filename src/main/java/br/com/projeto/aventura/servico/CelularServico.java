package br.com.projeto.aventura.servico;

import br.com.projeto.aventura.modelo.Celular;
import br.com.projeto.aventura.modelo.Usuario;

public interface CelularServico {

	public Usuario cadastrarCelular(Celular celular) throws Exception;

	public Usuario editarCelular(Celular celular) throws Exception;

}
