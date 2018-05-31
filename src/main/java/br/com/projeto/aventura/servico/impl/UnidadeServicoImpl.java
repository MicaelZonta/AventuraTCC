package br.com.projeto.aventura.servico.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import br.com.projeto.aventura.modelo.Avaliacao;
import br.com.projeto.aventura.modelo.Aventureiro;
import br.com.projeto.aventura.modelo.Habilidade;
import br.com.projeto.aventura.modelo.Missao;
import br.com.projeto.aventura.modelo.MissaoDificuldade;
import br.com.projeto.aventura.modelo.MissaoProgresso;
import br.com.projeto.aventura.modelo.Posicao;
import br.com.projeto.aventura.modelo.UnidadeHabilidade;
import br.com.projeto.aventura.modelo.UnidadeHabilidadeChave;
import br.com.projeto.aventura.modelo.abstrato.Unidade;
import br.com.projeto.aventura.repositorio.AventureiroRepositorio;
import br.com.projeto.aventura.repositorio.UnidadeRepositorio;
import br.com.projeto.aventura.servico.AventureiroServico;
import br.com.projeto.aventura.servico.MissaoProgressoServico;
import br.com.projeto.aventura.servico.MissaoServico;
import br.com.projeto.aventura.servico.UnidadeHabilidadeServico;
import br.com.projeto.aventura.servico.UnidadeServico;

@Service("unidadeServico")
public class UnidadeServicoImpl implements UnidadeServico {

	private UnidadeRepositorio unidadeRepositorio;

	@Autowired
	public UnidadeServicoImpl(UnidadeRepositorio unidadeRepositorio) {
		super();
		this.unidadeRepositorio = unidadeRepositorio;
	}


	@Override
	public Unidade cadastrarUnidade() throws Exception {
		try {
			return unidadeRepositorio.cadastrarUnidade(new Unidade());
		} catch (Exception e) {
			e.printStackTrace();
		}
		throw new HttpClientErrorException(HttpStatus.NOT_ACCEPTABLE);
	}

}
