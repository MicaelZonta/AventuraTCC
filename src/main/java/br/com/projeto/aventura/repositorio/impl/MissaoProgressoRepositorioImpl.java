package br.com.projeto.aventura.repositorio.impl;

import java.util.List;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import br.com.projeto.aventura.modelo.Missao;
import br.com.projeto.aventura.modelo.MissaoProgresso;
import br.com.projeto.aventura.modelo.Usuario;
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
		return null;
	}

	@Override
	public MissaoProgresso atualizarMissaoProgresso(MissaoProgresso missaoProgresso) throws Exception {
		missaoProgresso = atualizar(missaoProgresso.getIdMissao(), missaoProgresso);
		return null;
	}

	@Override
	public MissaoProgresso cancelarMissaoProgresso(MissaoProgresso missaoProgresso) throws Exception {
		missaoProgresso = excluir(missaoProgresso);
		return null;
	}

	@Override
	public MissaoProgresso buscarMissaoProgresso(Missao missao, Usuario usuario) throws Exception {
		//
		return null;
	}

	@Override
	public List<MissaoProgresso> listarMissaoProgresso(Usuario usuario) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<MissaoProgresso> listarMissaoProgresso(Missao missao) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
