package br.com.projeto.aventura.repositorio.impl;

import java.util.List;

import javax.persistence.Query;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import br.com.projeto.aventura.modelo.Situacao;
import br.com.projeto.aventura.repositorio.SituacaoRepositorio;

@Repository("situacaoRepositorio")
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class SituacaoRepositorioImpl extends RepositorioImpl<Situacao> implements SituacaoRepositorio {

	public SituacaoRepositorioImpl() {
		super(Situacao.class);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Situacao> listar() throws Exception {
		openSession();
		Query query = getSession().createNativeQuery("SELECT * FROM Situacao", Situacao.class);
		List<Situacao> list = query.getResultList();
		closeSession();
		return list;
	}

}
