package br.com.projeto.aventura.repositorio.impl;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import br.com.projeto.aventura.modelo.Avaliacao;
import br.com.projeto.aventura.repositorio.AvaliacaoRepositorio;

@Repository("avaliacaoRepositorio")
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class AvaliacaoRepositorioImpl extends RepositorioImpl<Avaliacao> implements AvaliacaoRepositorio {

	public AvaliacaoRepositorioImpl() {
		super(Avaliacao.class);
	}


	@Override
	public Avaliacao cadastrarAvaliacao(Avaliacao avaliacao) throws Exception {
		avaliacao = inserir(avaliacao);
		return avaliacao;
	}

}
