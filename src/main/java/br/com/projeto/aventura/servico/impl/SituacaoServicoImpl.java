package br.com.projeto.aventura.servico.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import br.com.projeto.aventura.modelo.Situacao;
import br.com.projeto.aventura.repositorio.SituacaoRepositorio;
import br.com.projeto.aventura.servico.SituacaoServico;

@Service("situacaoServico")
public class SituacaoServicoImpl implements SituacaoServico {

	private SituacaoRepositorio situacaoRepositorio;

	@Autowired
	public SituacaoServicoImpl(SituacaoRepositorio situacaoRepositorio) {
		this.situacaoRepositorio = situacaoRepositorio;
	}

	@Override
	public List<Situacao> listar() throws Exception {
		try {
			return situacaoRepositorio.listar();
		} catch (Exception e) {
			
		}
		throw new HttpClientErrorException(HttpStatus.NOT_FOUND);
	}
}
