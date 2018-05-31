package br.com.projeto.aventura.servico.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import br.com.projeto.aventura.modelo.abstrato.Unidade;
import br.com.projeto.aventura.repositorio.UnidadeRepositorio;
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
