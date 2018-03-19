package br.com.projeto.aventura.repositorio;

import br.com.projeto.aventura.modelo.PessoaFisica;
import br.com.projeto.aventura.modelo.Usuario;

public interface PessoaFisicaRepositorio {

	public PessoaFisica encontrarPessoaFisicaPorIdUsuario(long idUsuario) throws Exception;

	public PessoaFisica cadastrarPessoaFisica(PessoaFisica pessoaFisica) throws Exception;

	public PessoaFisica editarPessoaFisica(PessoaFisica pessoaFisica) throws Exception;

}
