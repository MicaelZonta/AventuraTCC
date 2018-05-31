package br.com.projeto.aventura.repositorio.impl;

import java.util.List;

import javax.persistence.Query;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import br.com.projeto.aventura.modelo.UnidadeHabilidade;
import br.com.projeto.aventura.repositorio.UnidadeHabilidadeRepositorio;

@Repository("unidadeHabilidadeRepositorio")
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class UnidadeHabilidadeRepositorioImpl extends RepositorioImpl<UnidadeHabilidade>
		implements UnidadeHabilidadeRepositorio {

	public UnidadeHabilidadeRepositorioImpl() {
		super(UnidadeHabilidade.class);
	}

	@Override
	public UnidadeHabilidade cadastrarUnidadeHabilidade(UnidadeHabilidade unidadeHabilidade) throws Exception {
		return inserir(unidadeHabilidade);
	}

	@Override
	public UnidadeHabilidade atualizarUnidadeHabilidade(UnidadeHabilidade unidadeHabilidade) throws Exception {
		return atualizar(0L, unidadeHabilidade);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<UnidadeHabilidade> listarUnidadeHabilidade(long idUnidade) throws Exception {
		openSession();
		Query query = getSession().createNativeQuery("SELECT * FROM Unidade_Habilidade WHERE idUnidade = :idUnidade", UnidadeHabilidade.class);
		query.setParameter("idUnidade", idUnidade);
		List<UnidadeHabilidade> listUnidadeHabilidade = query.getResultList();
		closeSession();
		return listUnidadeHabilidade;
	}

}
