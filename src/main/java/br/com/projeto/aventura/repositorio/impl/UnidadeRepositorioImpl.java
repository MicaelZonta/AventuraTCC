package br.com.projeto.aventura.repositorio.impl;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import br.com.projeto.aventura.modelo.abstrato.Unidade;
import br.com.projeto.aventura.repositorio.UnidadeRepositorio;

@Repository("unidadeRepositorio")
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class UnidadeRepositorioImpl extends RepositorioImpl<Unidade> implements UnidadeRepositorio {

	public UnidadeRepositorioImpl() {
		super(Unidade.class);
	}

	@Override
	public Unidade cadastrarUnidade(Unidade unidade) throws Exception {
		return inserir(unidade);
	}

}
