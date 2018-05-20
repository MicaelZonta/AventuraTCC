package br.com.projeto.aventura.repositorio.impl;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import br.com.projeto.aventura.modelo.TarefaProgresso;
import br.com.projeto.aventura.repositorio.MissaoTarefaProgressoRepositorio;

@Repository("missaoRepositorio")
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class TarefaProgressoRepositorioImpl extends RepositorioImpl<TarefaProgresso>
		implements MissaoTarefaProgressoRepositorio {

	public TarefaProgressoRepositorioImpl() {
		super(TarefaProgresso.class);
	}

	@Override
	public TarefaProgresso cadastrarTarefaProgresso(TarefaProgresso tarefaProgresso) throws Exception {
		tarefaProgresso = inserir(tarefaProgresso);
		return tarefaProgresso;
	}

	@Override
	public TarefaProgresso editarTarefaProgresso(TarefaProgresso tarefaProgresso) throws Exception {
		tarefaProgresso = atualizar(tarefaProgresso.getTarefaProgressoChave().getIdMissaoProgresso(), tarefaProgresso);
		return tarefaProgresso;
	}

	@Override
	public TarefaProgresso cancelarTarefaProgresso(TarefaProgresso tarefaProgresso) throws Exception {
		tarefaProgresso = excluir(tarefaProgresso);
		return tarefaProgresso;
	}

}
