package br.com.projeto.aventura.repositorio;

import br.com.projeto.aventura.modelo.abstrato.Pessoa;

public interface PessoaRepositorio {

	public Pessoa encontrarPessoa(long idUsuario) throws Exception;
	
	
}
