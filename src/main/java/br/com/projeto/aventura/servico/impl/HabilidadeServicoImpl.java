package br.com.projeto.aventura.servico.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import br.com.projeto.aventura.modelo.Habilidade;
import br.com.projeto.aventura.repositorio.HabilidadeRepositorio;
import br.com.projeto.aventura.servico.HabilidadeServico;

@Service("habilidadeServico")
public class HabilidadeServicoImpl implements HabilidadeServico {

	private HabilidadeRepositorio habilidadeRepositorio;

	@Autowired
	public HabilidadeServicoImpl(HabilidadeRepositorio habilidadeRepositorio) {
		this.habilidadeRepositorio = habilidadeRepositorio;
	}

	@Override
	public List<Habilidade> listar() throws Exception {
		try {
			return habilidadeRepositorio.listar();
		} catch (Exception e) {
			
		}
		throw new HttpClientErrorException(HttpStatus.NOT_FOUND);
	}
}
