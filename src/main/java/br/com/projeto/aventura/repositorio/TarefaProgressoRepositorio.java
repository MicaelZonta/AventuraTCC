package br.com.projeto.aventura.repositorio;

import br.com.projeto.aventura.modelo.Celular;
import br.com.projeto.aventura.modelo.Missao;
import br.com.projeto.aventura.modelo.MissaoProgresso;
import br.com.projeto.aventura.modelo.MissaoTarefa;
import br.com.projeto.aventura.modelo.Usuario;

public interface TarefaProgressoRepositorio {

	/*
	 * Parte da Missão e Missão Pro
	 */
	public Missao cadastrarMissao(Missao missao) throws Exception;

	public Missao editarMissao(Missao missao) throws Exception;
	
	public Missao deletarMissao(Missao missao) throws Exception;
	
	public MissaoProgresso cadastrarMissaoProgresso(MissaoProgresso missaoProgresso) throws Exception;
	
	public MissaoProgresso atualizarMissaoProgresso(MissaoProgresso missaoProgresso) throws Exception;
	
	public MissaoProgresso cancelarMissaoProgresso(MissaoProgresso missaoProgresso) throws Exception;

	public MissaoTarefa cadastrarMissaoTarefa(MissaoTarefa missaoTarefa) throws Exception;

	public MissaoTarefa editarMissaoTarefa(MissaoTarefa missaoTarefa) throws Exception;

	public MissaoTarefa cancelarMissaoTarefa(MissaoTarefa missaoTarefa) throws Exception;

}
