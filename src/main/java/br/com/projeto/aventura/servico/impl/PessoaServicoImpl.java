package br.com.projeto.aventura.servico.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import br.com.projeto.aventura.modelo.abstrato.Pessoa;
import br.com.projeto.aventura.repositorio.PessoaRepositorio;
import br.com.projeto.aventura.servico.PessoaServico;

@Service("pessoaServico")
public class PessoaServicoImpl implements PessoaServico {

	private PessoaRepositorio pessoaRepositorio;

	@Autowired
	public PessoaServicoImpl(PessoaRepositorio pessoaRepositorio) {
		this.pessoaRepositorio = pessoaRepositorio;
	}

	@Override
	public Pessoa encontrarPessoa(long idUsuario) throws Exception {
		Pessoa pessoa = null;
		try {
			pessoa = pessoaRepositorio.encontrarPessoa(idUsuario);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (pessoa == null) {
			throw new HttpClientErrorException(HttpStatus.NOT_FOUND);
		}

		return pessoa;
	}
}
