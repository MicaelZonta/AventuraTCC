package br.com.projeto.aventura.repositorio;

import br.com.projeto.aventura.modelo.TarefaProgresso;

/*
 * Parte das tarefas de uma missão
 */
public interface MissaoTarefaProgressoRepositorio {

	public TarefaProgresso cadastrarTarefaProgresso(TarefaProgresso tarefaProgresso) throws Exception;

	public TarefaProgresso editarTarefaProgresso(TarefaProgresso tarefaProgresso) throws Exception;

	public TarefaProgresso cancelarTarefaProgresso(TarefaProgresso tarefaProgresso) throws Exception;

}
