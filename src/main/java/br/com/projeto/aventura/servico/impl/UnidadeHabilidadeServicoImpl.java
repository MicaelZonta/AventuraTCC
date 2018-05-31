package br.com.projeto.aventura.servico.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import br.com.projeto.aventura.modelo.UnidadeHabilidade;
import br.com.projeto.aventura.repositorio.UnidadeHabilidadeRepositorio;
import br.com.projeto.aventura.servico.UnidadeHabilidadeServico;

@Service("unidadeHabilidadeServico")
public class UnidadeHabilidadeServicoImpl implements UnidadeHabilidadeServico {

	private UnidadeHabilidadeRepositorio uniHabiliRepo;

	@Autowired
	public UnidadeHabilidadeServicoImpl(UnidadeHabilidadeRepositorio uniHabiliRepo) {
		this.uniHabiliRepo = uniHabiliRepo;
	}

	@Override
	public UnidadeHabilidade cadastrarUnidadeHabilidade(UnidadeHabilidade unidadeHabilidade) throws Exception {
		try {
			unidadeHabilidade = uniHabiliRepo.cadastrarUnidadeHabilidade(unidadeHabilidade);
			return unidadeHabilidade;
		} catch (Exception e) {
		}

		throw new HttpClientErrorException(HttpStatus.NOT_ACCEPTABLE);
	}

	@Override
	public UnidadeHabilidade atualizarUnidadeHabilidade(UnidadeHabilidade unidadeHabilidade) throws Exception {
		try {
			return unidadeHabilidade = uniHabiliRepo.atualizarUnidadeHabilidade(unidadeHabilidade);
		} catch (Exception e) {

		}

		throw new HttpClientErrorException(HttpStatus.NOT_ACCEPTABLE);
	}

	@Override
	public List<UnidadeHabilidade> listarUnidadeHabilidade(long idUnidade) throws Exception {
		try {
			return uniHabiliRepo.listarUnidadeHabilidade(idUnidade);
		} catch (Exception e) {

		}

		throw new HttpClientErrorException(HttpStatus.NOT_ACCEPTABLE);
	}

}
