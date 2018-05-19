package br.com.projeto.aventura.repositorio.impl;

import java.util.List;

import javax.persistence.Query;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import br.com.projeto.aventura.modelo.Missao;
import br.com.projeto.aventura.modelo.PessoaFisica;
import br.com.projeto.aventura.repositorio.MissaoRepositorio;

@Repository("missaoRepositorio")
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class MissaoRepositorioImpl extends RepositorioImpl<Missao> implements MissaoRepositorio {

	public MissaoRepositorioImpl() {
		super(Missao.class);
	}

	@Override
	public Missao cadastrarMissao(Missao missao) throws Exception {
		missao = inserir(missao);
		return missao;
	}

	@Override
	public Missao editarMissao(Missao missao) throws Exception {
		missao = atualizar(missao.getIdMissao(), missao);
		return missao;
	}

	@Override
	public Missao deletarMissao(Missao missao) throws Exception {
		missao = excluir(missao);
		return missao;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Missao> listarMissao(PessoaFisica pessoa, Missao missao) throws Exception {
		openSession();
		StringBuilder sb = new StringBuilder("SELECT m.* FROM missao m ");
		sb.append("INNER JOIN pessoa p ON m.idPessoa = p.idPessoa ");
		sb.append("INNER JOIN usuario u on u.idUsuario = p.idUsuario ");
		sb.append(
				"WHERE m.maxAventureiros > (SELECT count(mp.idMissaoProgresso) FROM missao_progresso mp WHERE mp.idMissao = m.idMissao) ");
		sb.append("AND m.dataTermino IS NULL ");
		sb.append("AND u.ativo = true ");
		sb.append("AND 1000 >= aventura_db.getDistancia(m.latitude,m.longitude,:latitude,:longitude) ");
		
		if (missao.getNome() != null && !missao.getNome().isEmpty()) {
			sb.append("AND m.nome LIKE :nome ");
		}
		
		if(missao.getDescricao() != null &&  !missao.getDescricao().isEmpty()) {
			sb.append("AND m.descricao LIKE :descricao ");
		}
		
		if(missao.getDataCriacao() != null) {
			sb.append("AND m.dataCriacao >= :data ");
		}
		

		Query query = getSession().createNativeQuery(sb.toString() );
		query.setParameter("latitude", pessoa.getAventureiro().getPosicao().getLatitude() );
		query.setParameter("longitude", pessoa.getAventureiro().getPosicao().getLongitude() );
		query.setParameter("nome", missao.getNome() );
		query.setParameter("descricao", missao.getDescricao() );
		query.setParameter("data", missao.getDataCriacao() );

		List<Missao> missaoList = query.getResultList();
		closeSession();
		return missaoList;
	}

	@Override
	public Missao encontrarMissao(Long idMissao) throws Exception {
		Missao missao = buscaPorId(idMissao);
		return missao;
	}

}
