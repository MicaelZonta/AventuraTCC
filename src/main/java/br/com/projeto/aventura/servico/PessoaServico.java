package br.com.projeto.aventura.servico;

import br.com.projeto.aventura.modelo.abstrato.Pessoa;

public interface PessoaServico {

	public Pessoa encontrarPessoa(long idUsuario) throws Exception;


}
