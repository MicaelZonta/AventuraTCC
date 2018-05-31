package br.com.projeto.aventura.servico;

import br.com.projeto.aventura.modelo.Avaliacao;
import br.com.projeto.aventura.modelo.PessoaFisica;

public interface PessoaFisicaServico {

	public PessoaFisica encontrarPessoaFisicaPorIdUsuario(long idUsuario) throws Exception;

	public PessoaFisica cadastrarPessoaFisica(PessoaFisica pessoaFisica) throws Exception;

	public PessoaFisica editarPessoaFisica(PessoaFisica pessoaFisica) throws Exception;
}
