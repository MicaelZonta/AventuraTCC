package br.com.projeto.aventura.repositorio;

import java.util.List;

import br.com.projeto.aventura.modelo.Missao;
import br.com.projeto.aventura.modelo.MissaoProgresso;
import br.com.projeto.aventura.modelo.PessoaFisica;
import br.com.projeto.aventura.modelo.Situacao;

/*
 * Parte da realização da missão
 */
public interface MissaoProgressoRepositorio {

	public MissaoProgresso cadastrarMissaoProgresso(MissaoProgresso missaoProgresso) throws Exception;

	public MissaoProgresso atualizarMissaoProgresso(MissaoProgresso missaoProgresso) throws Exception;

	public MissaoProgresso cancelarMissaoProgresso(MissaoProgresso missaoProgresso) throws Exception;

	public MissaoProgresso buscarMissaoProgresso(Missao missao, PessoaFisica usuario) throws Exception;

	public List<MissaoProgresso> listarMissaoProgresso(PessoaFisica pessoa) throws Exception;

	public List<MissaoProgresso> listarMissaoProgresso(Missao missao) throws Exception;

	public Situacao encontrarSituacao(int idSituacao) throws Exception;

}
