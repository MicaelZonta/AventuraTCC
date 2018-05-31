package br.com.projeto.aventura.servico.impl;

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
import br.com.projeto.aventura.modelo.SituacaoEnum;
import br.com.projeto.aventura.modelo.TarefaProgresso;
import br.com.projeto.aventura.modelo.TarefaProgressoChave;
import br.com.projeto.aventura.modelo.abstrato.Pessoa;
import br.com.projeto.aventura.repositorio.MissaoProgressoRepositorio;
import br.com.projeto.aventura.repositorio.impl.TarefaProgressoRepositorioImpl;
import br.com.projeto.aventura.servico.MissaoProgressoServico;

@Service("missaoProgressoServico")
public class MissaoProgressoServicoImpl implements MissaoProgressoServico {

	private MissaoProgressoRepositorio progressoRepositorio;
	private TarefaProgressoRepositorioImpl tarefaRepositorio;

	@Autowired
	public MissaoProgressoServicoImpl(MissaoProgressoRepositorio progressoRepositorio,
			TarefaProgressoRepositorioImpl tarefaRepositorio) {
		this.progressoRepositorio = progressoRepositorio;
		this.tarefaRepositorio = tarefaRepositorio;
	}

	@Override
	public MissaoProgresso aceitarMissao(Missao missao, PessoaFisica pessoaFisica) throws Exception {
		MissaoProgresso progresso = null;
		try {
			progresso = new MissaoProgresso();
			progresso.setIdMissao(missao.getIdMissao());
			progresso.setIdPessoa(pessoaFisica.getIdPessoa());
			progresso.setSituacao(encontrarSituacao(SituacaoEnum.INICIADO));

			progressoRepositorio.cadastrarMissaoProgresso(progresso);

			for (MissaoTarefa tarefa : missao.getListaTarefas()) {

				TarefaProgresso tarefaProg = new TarefaProgresso();
				TarefaProgressoChave tarefaChave = new TarefaProgressoChave();

				tarefaChave.setIdMissaoProgresso(progresso.getIdMissaoProgresso());
				tarefaChave.setIdMissaoTarefa(tarefa.getIdMissaoTarefa());

				tarefaProg.setTarefaProgressoChave(tarefaChave);
				tarefaProg.setSituacao(encontrarSituacao(SituacaoEnum.INICIADO));

				tarefaRepositorio.cadastrarTarefaProgresso(tarefaProg);
			}
			progresso = buscarMissaoProgresso(missao, pessoaFisica);
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
			if (mp.getSituacao().getIdSituacao() == SituacaoEnum.CANCELADO.getItem()
					|| mp.getSituacao().getIdSituacao() == SituacaoEnum.DESISTENCIA.getItem()
					|| mp.getSituacao().getIdSituacao() == SituacaoEnum.PAUSA.getItem()) {
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

			if (missao.getIdPessoa() != pessoa.getIdPessoa()) {
				throw new HttpClientErrorException(HttpStatus.NOT_ACCEPTABLE);
			}

			progresso.setSituacao(encontrarSituacao(SituacaoEnum.CANCELADO));

			for (TarefaProgresso tarefa : progresso.getTarefas()) {
				tarefa.setSituacao(encontrarSituacao(SituacaoEnum.CANCELADO));
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
			tarefa.setSituacao(encontrarSituacao(SituacaoEnum.COMPLETA));

			boolean todasTarefasCompletas = true;

			for (TarefaProgresso task : progresso.getTarefas()) {
				if (task.getTarefaProgressoChave().getIdMissaoProgresso() == tarefa.getTarefaProgressoChave()
						.getIdMissaoProgresso()) {
					if (task.getTarefaProgressoChave().getIdMissaoTarefa() == tarefa.getTarefaProgressoChave()
							.getIdMissaoTarefa()) {
						task.setSituacao(tarefa.getSituacao());
					}
				}

				if (task.getSituacao().getIdSituacao() != SituacaoEnum.COMPLETA.getItem()) {
					todasTarefasCompletas = false;
				}
			}

			if (todasTarefasCompletas) {
				progresso.setSituacao(encontrarSituacao(SituacaoEnum.COMPLETA));
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
			progresso.setSituacao(encontrarSituacao(SituacaoEnum.PAUSA));
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
			progresso = progressoRepositorio.buscarMissaoProgresso(missao, pessoaFisica);
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

	@Override
	public Situacao encontrarSituacao(SituacaoEnum situacaoEnum) throws Exception {
		Situacao situacao = null;
		try {
			int idSituacao = situacaoEnum.getItem();
			situacao = progressoRepositorio.encontrarSituacao(idSituacao);
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (situacao == null) {
			throw new HttpClientErrorException(HttpStatus.NOT_ACCEPTABLE);
		}
		return situacao;
	}

	@Override
	public MissaoProgresso atualizarMissaoProgresso(Missao missao, Pessoa pessoa, PessoaFisica pessoaFisica,
			int idSituacao) throws Exception {
		MissaoProgresso progresso = progressoRepositorio.buscarMissaoProgresso(missao, pessoaFisica);

		if (missao.getIdPessoa() != pessoa.getIdPessoa()) {
			throw new HttpClientErrorException(HttpStatus.NOT_ACCEPTABLE);
		}

		if (SituacaoEnum.getIntValue(idSituacao) == SituacaoEnum.DESISTENCIA) {
			throw new HttpClientErrorException(HttpStatus.NOT_ACCEPTABLE);

		}
		try {

			Situacao situacao = encontrarSituacao(SituacaoEnum.getIntValue(idSituacao));
			progresso.setSituacao(situacao);
			progresso = progressoRepositorio.atualizarMissaoProgresso(progresso);

		} catch (Exception e) {

			throw new HttpClientErrorException(HttpStatus.NOT_ACCEPTABLE);

		}
		return progresso;
	}

	@Override
	public MissaoProgresso buscarMissaoProgresso(long idMissaoProgresso) throws Exception {
		try {
			MissaoProgresso mp = progressoRepositorio.encontrarMissaoProgresso(idMissaoProgresso);
			return mp;
		} catch (Exception e) {
		}
		throw new HttpClientErrorException(HttpStatus.NOT_ACCEPTABLE);

	}

}
