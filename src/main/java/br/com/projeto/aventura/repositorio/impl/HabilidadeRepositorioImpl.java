package br.com.projeto.aventura.repositorio.impl;

import java.util.List;

import javax.persistence.Query;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import br.com.projeto.aventura.modelo.Habilidade;
import br.com.projeto.aventura.repositorio.HabilidadeRepositorio;

@Repository("habilidadeRepositorio")
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class HabilidadeRepositorioImpl extends RepositorioImpl<Habilidade> implements HabilidadeRepositorio {

	public HabilidadeRepositorioImpl() {
		super(Habilidade.class);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Habilidade> listar() throws Exception {
		openSession();
		Query query = getSession().createNativeQuery("SELECT * FROM Habilidade", Habilidade.class);
		List<Habilidade> habilidades = query.getResultList();
		closeSession();
		return habilidades;
	}

}
