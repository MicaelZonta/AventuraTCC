package br.com.projeto.aventura.servico.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import br.com.projeto.aventura.modelo.Missao;
import br.com.projeto.aventura.modelo.MissaoDificuldade;
import br.com.projeto.aventura.modelo.MissaoProgresso;
import br.com.projeto.aventura.modelo.MissaoTarefa;
import br.com.projeto.aventura.modelo.PessoaFisica;
import br.com.projeto.aventura.modelo.Usuario;
import br.com.projeto.aventura.repositorio.MissaoRepositorio;
import br.com.projeto.aventura.servico.MissaoProgressoServico;
import br.com.projeto.aventura.servico.MissaoServico;
import br.com.projeto.aventura.servico.UsuarioServico;

@Service("missaoServico")
public class MissaoServicoImpl implements MissaoServico {

	@Autowired
	private MissaoRepositorio missaoRepositorio;
	@Autowired
	private MissaoProgressoServico progressoServico;
	@Autowired
	private UsuarioServico usuarioServico;

	public MissaoServicoImpl(MissaoRepositorio missaoRepositorio, MissaoProgressoServico progressoServico,
			UsuarioServico usuarioServico) {
		this.missaoRepositorio = missaoRepositorio;
		this.progressoServico = progressoServico;
		this.usuarioServico = usuarioServico;
	}

	@Override
	public Missao cadastrarMissao(Missao missao, Usuario usuario) throws Exception {

		if (usuario.getFavor() > 0) {
			usuario.setFavor((usuario.getFavor() - 1));
		} else {
			throw new HttpClientErrorException(HttpStatus.FORBIDDEN);
		}

		List<MissaoTarefa> tarefaList = missao.getListaTarefas();
		List<MissaoDificuldade> dificuldadeList = missao.getListaDificuldades();

		missao.setListaTarefas(null);
		missao.setListaDificuldades(null);

		try {
			missao = missaoRepositorio.cadastrarMissao(missao);
			usuario = usuarioServico.editarUsuario(usuario);
			if (missao == null) {
				throw new HttpClientErrorException(HttpStatus.NOT_FOUND);
			}

			for (MissaoTarefa tarefa : tarefaList) {
				tarefa.setIdMissao(missao.getIdMissao());
			}

			for (MissaoDificuldade dificuldade : dificuldadeList) {
				dificuldade.setIdMissao(missao.getIdMissao());
			}

			missao.setListaDificuldades(dificuldadeList);
			missao.setListaTarefas(tarefaList);

			missao = editarMissao(missao);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return missao;
	}

	@Override
	public Missao editarMissao(Missao missao) throws Exception {

		try {
			missao = missaoRepositorio.editarMissao(missao);
			if (missao == null) {
				throw new HttpClientErrorException(HttpStatus.NOT_FOUND);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return missao;
	}

	@Override
	public Missao deletarMissao(Missao missao) throws Exception {
		try {
			List<MissaoProgresso> progressos = progressoServico.listarMissaoProgresso(missao);
			if (progressos !=null && progressos.size() == 0) {
				missao = missaoRepositorio.deletarMissao(missao);
				if (missao == null) {
					throw new HttpClientErrorException(HttpStatus.NOT_FOUND);
				}
			} else {
				throw new HttpClientErrorException(HttpStatus.NOT_ACCEPTABLE);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return missao;
	}

	@Override
	public List<Missao> listarMissao(PessoaFisica usuario, Missao missao) throws Exception {
		List<Missao> missaoLista = null;

		try {
			missaoLista = missaoRepositorio.listarMissao(usuario, missao);
			if (missaoLista == null) {
				throw new HttpClientErrorException(HttpStatus.NOT_FOUND);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return missaoLista;
	}

	@Override
	public Missao encontrarMissao(long idMissao) throws Exception {
		Missao missao = null;
		try {
			missao = missaoRepositorio.encontrarMissao(idMissao);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return missao;
	}
}
