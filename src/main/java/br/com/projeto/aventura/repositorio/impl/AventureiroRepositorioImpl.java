package br.com.projeto.aventura.repositorio.impl;

import javax.persistence.Query;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import br.com.projeto.aventura.modelo.Aventureiro;
import br.com.projeto.aventura.repositorio.AventureiroRepositorio;

@Repository("aventureiroRepositorio")
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class AventureiroRepositorioImpl extends RepositorioImpl<Aventureiro> implements AventureiroRepositorio {

	public AventureiroRepositorioImpl() {
		super(Aventureiro.class);
	}

	@Override
	public Aventureiro buscarAventureiroPorIdPessoa(long idPessoa) throws Exception {
		openSession();
		Query query = getSession().createNativeQuery("SELECT * FROM Aventureiro WHERE idPessoa = :idPessoa",
				Aventureiro.class);
		query.setParameter("idPessoa", idPessoa);
		Aventureiro av = (Aventureiro) query.getSingleResult();
		closeSession();
		return av;
	}

	@Override
	public Aventureiro cadastrarAventureiro(Aventureiro aventureiro) throws Exception {
		return inserir(aventureiro);
	}

	@Override
	public Aventureiro atualizarAventureiro(Aventureiro aventureiro) throws Exception {
		return atualizar(aventureiro.getIdUnidade(),aventureiro);
	}

}
