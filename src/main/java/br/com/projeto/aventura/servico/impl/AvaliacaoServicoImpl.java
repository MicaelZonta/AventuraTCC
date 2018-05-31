package br.com.projeto.aventura.servico.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import br.com.projeto.aventura.modelo.Avaliacao;
import br.com.projeto.aventura.modelo.AvaliacaoChave;
import br.com.projeto.aventura.modelo.Missao;
import br.com.projeto.aventura.modelo.MissaoProgresso;
import br.com.projeto.aventura.modelo.PessoaFisica;
import br.com.projeto.aventura.modelo.Usuario;
import br.com.projeto.aventura.modelo.abstrato.Pessoa;
import br.com.projeto.aventura.repositorio.AvaliacaoRepositorio;
import br.com.projeto.aventura.servico.AvaliacaoServico;
import br.com.projeto.aventura.servico.MissaoProgressoServico;
import br.com.projeto.aventura.servico.MissaoServico;
import br.com.projeto.aventura.servico.PessoaFisicaServico;
import br.com.projeto.aventura.servico.PessoaServico;

@Service("avaliacaoServico")
public class AvaliacaoServicoImpl implements AvaliacaoServico {

	private AvaliacaoRepositorio avaliacaoRep;
	private MissaoProgressoServico missaoProgressoServ;
	private MissaoServico missaoServ;
	private PessoaServico pessoaServico;
	private PessoaFisicaServico pessoaFServico;

	@Autowired
	public AvaliacaoServicoImpl(AvaliacaoRepositorio avaliacaoRep, MissaoProgressoServico missaoProgressoServ,
			MissaoServico missaoServ, PessoaServico pessoaServico, PessoaFisicaServico pessoaFServico) {
		super();
		this.avaliacaoRep = avaliacaoRep;
		this.missaoProgressoServ = missaoProgressoServ;
		this.missaoServ = missaoServ;
		this.pessoaServico = pessoaServico;
		this.pessoaFServico = pessoaFServico;
	}

	@Override
	public Avaliacao cadastrarAvaliacao(Integer nota, String descricao, Missao missao, Usuario usuario,
			Usuario usuarioAvaliado) throws Exception {
		try {
			missao = missaoServ.encontrarMissao(missao.getIdMissao());
			Pessoa giver = pessoaServico.encontrarPessoa(usuario.getIdUsuario());
			PessoaFisica taker = pessoaFServico.encontrarPessoaFisicaPorIdUsuario(usuarioAvaliado.getIdUsuario());

			if (giver.getIdPessoa() == missao.getIdPessoa()) {

				MissaoProgresso mp = missaoProgressoServ.buscarMissaoProgresso(missao, taker);
				if (mp != null) {
					Avaliacao avaliacao = new Avaliacao();
					avaliacao.setDescricao(descricao);
					avaliacao.setEstrelas(nota);
					avaliacao.setAvaliacaoChave(new AvaliacaoChave());
					avaliacao.getAvaliacaoChave().setIdMissaoProgresso(mp.getIdMissaoProgresso());
					avaliacao.getAvaliacaoChave().setIdPessoa(taker.getIdPessoa());
					avaliacaoRep.cadastrarAvaliacao(avaliacao);
					return avaliacao;
				}
			}
		} catch (Exception e) {
		}

		throw new HttpClientErrorException(HttpStatus.NOT_ACCEPTABLE);
	}
	
	

}
