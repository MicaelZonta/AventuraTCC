package br.com.projeto.aventura.servico.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import br.com.projeto.aventura.modelo.Missao;
import br.com.projeto.aventura.modelo.MissaoProgresso;
import br.com.projeto.aventura.modelo.MissaoTarefa;
import br.com.projeto.aventura.modelo.PessoaFisica;
import br.com.projeto.aventura.modelo.Situacao;
import br.com.projeto.aventura.modelo.TarefaProgresso;
import br.com.projeto.aventura.modelo.Usuario;
import br.com.projeto.aventura.repositorio.MissaoProgressoRepositorio;
import br.com.projeto.aventura.repositorio.MissaoTarefaProgressoRepositorio;
import br.com.projeto.aventura.servico.MissaoProgressoServico;

@Service("missaoProgressoServico")
public class MissaoProgressoServicoImpl implements MissaoProgressoServico {

	private MissaoTarefaProgressoRepositorio tarefaRepositorio;
	private MissaoProgressoRepositorio progressoRepositorio;

	@Autowired
	public MissaoProgressoServicoImpl(MissaoTarefaProgressoRepositorio tarefaRepositorio,
			MissaoProgressoRepositorio progressoRepositorio) {
		this.tarefaRepositorio = tarefaRepositorio;
		this.progressoRepositorio = progressoRepositorio;
	}

	@Override
	public MissaoProgresso aceitarMissao(Missao missao, PessoaFisica pessoa) throws Exception {
		MissaoProgresso progresso = null;
		try {
			progresso = new MissaoProgresso();
			progresso.setIdMissao(missao.getIdMissao());
			progresso.setIdPessoa(pessoa.getIdPessoa());
			progresso.setIdSituacao(Situacao.INICIADO);

			progressoRepositorio.cadastrarMissaoProgresso(progresso);

			List<TarefaProgresso> tarefas = new ArrayList<TarefaProgresso>();

			for (MissaoTarefa tarefa : missao.getListaTarefas()) {
				TarefaProgresso tarefaProg = new TarefaProgresso();
				tarefaProg.setIdMissaoProgresso(progresso.getIdMissaoProgresso());
				tarefaProg.setIdSituacao(Situacao.INICIADO);
				tarefaProg.setIdTarefa(tarefa.getIdMissaoTarefa());
				tarefas.add(tarefaProg);
			}

			progresso.setTarefas(tarefas);

			progressoRepositorio.atualizarMissaoProgresso(progresso);
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (progresso == null) {
			throw new HttpClientErrorException(HttpStatus.NOT_ACCEPTABLE);
		}

		return progresso;
	}

	@Override
	public MissaoProgresso cancelarMissao(Missao missao, Usuario usuario) throws Exception {
		MissaoProgresso progresso = null;
		try {
			progresso = buscarMissaoProgresso(missao, usuario);
			progresso.setIdSituacao(Situacao.CANCELADO);

			for (TarefaProgresso tarefa : progresso.getTarefas()) {
				tarefa.setIdSituacao(Situacao.CANCELADO);
			}

			progressoRepositorio.atualizarMissaoProgresso(progresso);
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (progresso == null) {
			throw new HttpClientErrorException(HttpStatus.NOT_ACCEPTABLE);
		}
		return progresso;
	}

	@Override
	public MissaoProgresso completarTarefa(Missao missao, Usuario usuario, TarefaProgresso tarefa) throws Exception {
		MissaoProgresso progresso = null;
		try {
			progresso = buscarMissaoProgresso(missao, usuario);
			tarefa.setIdSituacao(Situacao.COMPLETA);
			progressoRepositorio.atualizarMissaoProgresso(progresso);
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (progresso == null) {
			throw new HttpClientErrorException(HttpStatus.NOT_ACCEPTABLE);
		}
		return progresso;
	}

	@Override
	public MissaoProgresso pausarMissao(Missao missao, Usuario usuario) throws Exception {
		MissaoProgresso progresso = null;
		try {
			progresso = buscarMissaoProgresso(missao, usuario);
			progresso.setIdSituacao(Situacao.PAUSA);
			progressoRepositorio.atualizarMissaoProgresso(progresso);
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (progresso == null) {
			throw new HttpClientErrorException(HttpStatus.NOT_ACCEPTABLE);
		}
		return progresso;

	}

	@Override
	public MissaoProgresso buscarMissaoProgresso(Missao missao, Usuario usuario) throws Exception {
		MissaoProgresso progresso = null;
		try {
			progresso = buscarMissaoProgresso(missao, usuario);
			progresso.setIdSituacao(Situacao.PAUSA);
			progressoRepositorio.atualizarMissaoProgresso(progresso);
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (progresso == null) {
			throw new HttpClientErrorException(HttpStatus.NOT_ACCEPTABLE);
		}
		return progresso;
	}

	@Override
	public List<MissaoProgresso> listarMissaoProgresso(Usuario usuario) throws Exception {
		List<MissaoProgresso> progressosList = null;

		try {
			progressosList = progressoRepositorio.listarMissaoProgresso(usuario);
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (progressosList == null) {
			throw new HttpClientErrorException(HttpStatus.NOT_ACCEPTABLE);
		}
		return progressosList;
	}

	@Override
	public List<MissaoProgresso> listarMissaoProgresso(Missao missao) throws Exception {
		List<MissaoProgresso> progressosList = null;

		try {
			progressosList = progressoRepositorio.listarMissaoProgresso(missao);
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (progressosList == null) {
			throw new HttpClientErrorException(HttpStatus.NOT_ACCEPTABLE);
		}
		return progressosList;
	}
}
