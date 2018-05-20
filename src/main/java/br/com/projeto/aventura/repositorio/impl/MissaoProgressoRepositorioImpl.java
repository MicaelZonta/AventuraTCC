package br.com.projeto.aventura.repositorio.impl;

import java.util.List;

import javax.persistence.Query;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import br.com.projeto.aventura.modelo.Missao;
import br.com.projeto.aventura.modelo.MissaoProgresso;
import br.com.projeto.aventura.modelo.PessoaFisica;
import br.com.projeto.aventura.modelo.Situacao;
import br.com.projeto.aventura.repositorio.MissaoProgressoRepositorio;

@Repository("missaoProgressoRepositorio")
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class MissaoProgressoRepositorioImpl extends RepositorioImpl<MissaoProgresso>
		implements MissaoProgressoRepositorio {

	public MissaoProgressoRepositorioImpl() {
		super(MissaoProgresso.class);
	}

	@Override
	public MissaoProgresso cadastrarMissaoProgresso(MissaoProgresso missaoProgresso) throws Exception {
		missaoProgresso = inserir(missaoProgresso);
		return missaoProgresso;
	}

	@Override
	public MissaoProgresso atualizarMissaoProgresso(MissaoProgresso missaoProgresso) throws Exception {
		missaoProgresso = atualizar(missaoProgresso.getIdMissaoProgresso(), missaoProgresso);
		return missaoProgresso;
	}

	@Override
	public MissaoProgresso cancelarMissaoProgresso(MissaoProgresso missaoProgresso) throws Exception {
		missaoProgresso = excluir(missaoProgresso);
		return missaoProgresso;
	}

	@Override
	public MissaoProgresso buscarMissaoProgresso(Missao missao, PessoaFisica pessoaFisica) throws Exception {
		openSession();
		StringBuilder sb = new StringBuilder("SELECT mp.* FROM missao_progresso mp ");
		sb.append("INNER JOIN usuario_pessoa up ON mp.idPessoa = up.idPessoa ");
		sb.append("WHERE mp.idMissao = :idMissao AND up.idPessoa = :idPessoa ");

		
		Query query = getSession().createNativeQuery(sb.toString(),MissaoProgresso.class);
		query.setMaxResults(1);
		query.setParameter("idPessoa", pessoaFisica.getIdPessoa());
		query.setParameter("idMissao", missao.getIdMissao());

		MissaoProgresso mp = (MissaoProgresso) query.getSingleResult();
		
		System.out.println("PESSSSSSSSOAAA" + mp.getIdPessoa());
		closeSession();
		return mp;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<MissaoProgresso> listarMissaoProgresso(PessoaFisica pessoaFisica) throws Exception {
		openSession();
		StringBuilder sb = new StringBuilder("SELECT mp.* FROM missao_progresso mp ");
		sb.append("INNER JOIN usuario_pessoa up ON mp.idPessoa = up.idPessoa ");
		sb.append("WHERE up.idPessoa = :idPessoa ");

		Query query = getSession().createNativeQuery(sb.toString(), MissaoProgresso.class);
		query.setParameter("idPessoa", pessoaFisica.getIdPessoa());

		List<MissaoProgresso> mp = query.getResultList();
		closeSession();
		return mp;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<MissaoProgresso> listarMissaoProgresso(Missao missao) throws Exception {
		openSession();
		StringBuilder sb = new StringBuilder("SELECT mp FROM Missao_Progresso mp WHERE idMissao = :idMissao ");
		Query query = getSession().createQuery(sb.toString(), MissaoProgresso.class);
		query.setParameter("idMissao", missao.getIdMissao());

		List<MissaoProgresso> mp = query.getResultList();
		closeSession();
		return mp;
	}

	@Override
	public Situacao encontrarSituacao(int idSituacao) throws Exception {
		openSession();
		StringBuilder sb = new StringBuilder("SELECT s FROM Situacao s WHERE idSituacao = :idSituacao ");
		Query query = getSession().createQuery(sb.toString(), Situacao.class);
		query.setMaxResults(1);
		query.setParameter("idSituacao", idSituacao);

		Situacao situacao = (Situacao) query.getSingleResult();
		closeSession();
		return situacao;
	}

}
