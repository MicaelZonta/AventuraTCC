package br.com.projeto.aventura.repositorio.impl;

import javax.persistence.Query;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import br.com.projeto.aventura.modelo.PessoaFisica;
import br.com.projeto.aventura.repositorio.PessoaFisicaRepositorio;

@Repository("pessoaFisicaRepositorio")
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class PessoaFisicaRepositorioImpl extends RepositorioImpl<PessoaFisica> implements PessoaFisicaRepositorio {

	public PessoaFisicaRepositorioImpl() {
		super(PessoaFisica.class);
	}

	@Override
	public PessoaFisica encontrarPessoaFisicaPorIdUsuario(long idUsuario) throws Exception {
		openSession();
		Query query = getSession().createQuery("SELECT p FROM Pessoa_Fisica p WHERE idUsuario = :idUsuario",
				PessoaFisica.class);
		query.setParameter("idUsuario", idUsuario);
		PessoaFisica pessoa = (PessoaFisica) query.getSingleResult();
		closeSession();
		return pessoa;
	}

	@Override
	public PessoaFisica cadastrarPessoaFisica(PessoaFisica pessoaFisica) throws Exception {
		pessoaFisica = inserir(pessoaFisica);
		return pessoaFisica;
	}

	@Override
	public PessoaFisica editarPessoaFisica(PessoaFisica pessoaFisica) throws Exception {
		pessoaFisica = atualizar(pessoaFisica.getIdPessoa(), pessoaFisica);
		return pessoaFisica;
	}

}
