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
import br.com.projeto.aventura.modelo.TarefaProgressoChave;
import br.com.projeto.aventura.modelo.abstrato.Pessoa;
import br.com.projeto.aventura.repositorio.MissaoProgressoRepositorio;
import br.com.projeto.aventura.servico.MissaoProgressoServico;

@Service("missaoProgressoServico")
public class MissaoProgressoServicoImpl implements MissaoProgressoServico {

	private MissaoProgressoRepositorio progressoRepositorio;

	@Autowired
	public MissaoProgressoServicoImpl(MissaoProgressoRepositorio progressoRepositorio) {
		this.progressoRepositorio = progressoRepositorio;
	}

	@Override
	public MissaoProgresso aceitarMissao(Missao missao, PessoaFisica pessoaFisica) throws Exception {
		MissaoProgresso progresso = null;
		try {
			progresso = new MissaoProgresso();
			progresso.setIdMissao(missao.getIdMissao());
			progresso.setIdPessoa(pessoaFisica.getIdPessoa());
			progresso.setIdSituacao(Situacao.INICIADO);

			progressoRepositorio.cadastrarMissaoProgresso(progresso);

			List<TarefaProgresso> tarefas = new ArrayList<TarefaProgresso>();

			for (MissaoTarefa tarefa : missao.getListaTarefas()) {
				TarefaProgresso tarefaProg = new TarefaProgresso();
				TarefaProgressoChave tarefaChave = new TarefaProgressoChave();
				tarefaChave.setIdMissaoProgresso(progresso.getIdMissaoProgresso());
				tarefaChave.setIdMissaoTarefa(tarefa.getIdMissaoTarefa());
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

	private MissaoProgresso buscarMissaoProgressoDisponivel(Missao missao, PessoaFisica pessoaFisica) {
		try {
			MissaoProgresso mp = buscarMissaoProgresso(missao, pessoaFisica);
			if (mp.getIdSituacao() == Situacao.CANCELADO || mp.getIdSituacao() == Situacao.DESISTENCIA
					|| mp.getIdSituacao() == Situacao.PAUSA) {
				return null;
			} else {
				return mp;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public MissaoProgresso cancelarMissao(Missao missao, PessoaFisica pessoaFisica, Pessoa pessoa) throws Exception {
		MissaoProgresso progresso = null;
		try {
			progresso = buscarMissaoProgressoDisponivel(missao, pessoaFisica);

			if (progresso.getIdPessoa() != pessoa.getIdPessoa()) {
				throw new HttpClientErrorException(HttpStatus.NOT_ACCEPTABLE);
			}

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
	public MissaoProgresso completarTarefa(Missao missao, PessoaFisica pessoaFisica, TarefaProgresso tarefa)
			throws Exception {
		MissaoProgresso progresso = null;
		try {
			progresso = buscarMissaoProgressoDisponivel(missao, pessoaFisica);
			tarefa.setIdSituacao(Situacao.COMPLETA);

			boolean todasTarefasCompletas = true;

			for (TarefaProgresso task : progresso.getTarefas()) {
				if (task.getIdSituacao() != Situacao.COMPLETA) {
					todasTarefasCompletas = false;
				}
			}

			if (todasTarefasCompletas) {
				progresso.setIdSituacao(Situacao.COMPLETA);
			}
			
			progresso = progressoRepositorio.atualizarMissaoProgresso(progresso);

		} catch (Exception e) {
			e.printStackTrace();
		}

		if (progresso == null) {
			throw new HttpClientErrorException(HttpStatus.NOT_ACCEPTABLE);
		}
		return progresso;
	}

	@Override
	public MissaoProgresso pausarMissao(Missao missao, PessoaFisica pessoaFisica, Pessoa pessoa) throws Exception {
		MissaoProgresso progresso = null;
		try {

			progresso = buscarMissaoProgressoDisponivel(missao, pessoaFisica);
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
	public MissaoProgresso buscarMissaoProgresso(Missao missao, PessoaFisica pessoaFisica) throws Exception {
		MissaoProgresso progresso = null;
		try {
			progresso = buscarMissaoProgresso(missao, pessoaFisica);
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (progresso == null) {
			throw new HttpClientErrorException(HttpStatus.NOT_ACCEPTABLE);
		}
		return progresso;
	}

	@Override
	public List<MissaoProgresso> listarMissaoProgresso(PessoaFisica pessoaFisica) throws Exception {
		List<MissaoProgresso> progressosList = null;

		try {
			progressosList = progressoRepositorio.listarMissaoProgresso(pessoaFisica);
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
