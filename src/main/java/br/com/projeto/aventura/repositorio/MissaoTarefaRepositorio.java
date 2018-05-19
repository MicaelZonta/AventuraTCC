package br.com.projeto.aventura.repositorio;

import br.com.projeto.aventura.modelo.MissaoTarefa;

/*
 * Parte das tarefas de uma missão
 */
public interface MissaoTarefaRepositorio {

	public MissaoTarefa cadastrarMissaoTarefa(MissaoTarefa missaoTarefa) throws Exception;

	public MissaoTarefa editarMissaoTarefa(MissaoTarefa missaoTarefa) throws Exception;

	public MissaoTarefa cancelarMissaoTarefa(MissaoTarefa missaoTarefa) throws Exception;

}
