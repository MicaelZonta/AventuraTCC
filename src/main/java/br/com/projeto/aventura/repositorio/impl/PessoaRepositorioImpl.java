package br.com.projeto.aventura.repositorio.impl;

import javax.persistence.Query;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import br.com.projeto.aventura.modelo.abstrato.Pessoa;
import br.com.projeto.aventura.repositorio.PessoaRepositorio;

@Repository("pessoaRepositorio")
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class PessoaRepositorioImpl extends RepositorioImpl<Pessoa> implements PessoaRepositorio {

	public PessoaRepositorioImpl() {
		super(Pessoa.class);
	}

	@Override
	public Pessoa encontrarPessoa(long idUsuario) throws Exception {
		openSession();
		Query query = getSession().createQuery("SELECT p FROM Pessoa p WHERE idUsuario = :idUsuario",
				Pessoa.class);
		query.setParameter("idUsuario", idUsuario);
		Pessoa pessoa = (Pessoa) query.getSingleResult();
		closeSession();
		return pessoa;
	}

}
