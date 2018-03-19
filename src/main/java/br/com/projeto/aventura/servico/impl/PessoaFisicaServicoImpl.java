package br.com.projeto.aventura.servico.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import br.com.projeto.aventura.Util.UtilString;
import br.com.projeto.aventura.modelo.PessoaFisica;
import br.com.projeto.aventura.modelo.Usuario;
import br.com.projeto.aventura.modelo.abstrato.Pessoa;
import br.com.projeto.aventura.repositorio.PessoaFisicaRepositorio;
import br.com.projeto.aventura.repositorio.UsuarioRepositorio;
import br.com.projeto.aventura.servico.PessoaFisicaServico;
import br.com.projeto.aventura.servico.UsuarioServico;

@Service("pessoaFisicaServico")
public class PessoaFisicaServicoImpl implements PessoaFisicaServico {

	@Autowired
	private PessoaFisicaRepositorio pessoaFisicaRepositorio;

	public PessoaFisicaServicoImpl(PessoaFisicaRepositorio pessoaFisicaRepositorio) {
		this.pessoaFisicaRepositorio = pessoaFisicaRepositorio;
	}

	@Override
	public PessoaFisica encontrarPessoaFisicaPorIdUsuario(long idUsuario) throws Exception {
		PessoaFisica pessoa = null;
		try {
			pessoa = pessoaFisicaRepositorio.encontrarPessoaFisicaPorIdUsuario(idUsuario);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (pessoa == null) {
			throw new HttpClientErrorException(HttpStatus.NOT_FOUND);
		}

		return pessoa;
	}

	@Override
	public PessoaFisica cadastrarPessoaFisica(PessoaFisica pessoaFisica) throws Exception {
		if (validar(pessoaFisica))
			pessoaFisica = pessoaFisicaRepositorio.cadastrarPessoaFisica(pessoaFisica);
		else
			throw new HttpClientErrorException(HttpStatus.BAD_REQUEST);
		return pessoaFisica;
	}

	@Override
	public PessoaFisica editarPessoaFisica(PessoaFisica pessoaFisica) throws Exception {
		if (validar(pessoaFisica))
			pessoaFisica = pessoaFisicaRepositorio.editarPessoaFisica(pessoaFisica);
		else
			throw new HttpClientErrorException(HttpStatus.BAD_REQUEST);
		return pessoaFisica;
	}

	private boolean validar(PessoaFisica p) {
		if(UtilString.isEmpty(p.getNome())) {
			return false;
		}
		if(UtilString.isEmpty(p.getSobrenome())) {
			return false;
		}
		if(UtilString.isEmpty(p.getEmail())) {
			return false;
		}
		if(UtilString.isEmpty(p.getCPF())) {
			return false;
		}
		return true;	}
}
