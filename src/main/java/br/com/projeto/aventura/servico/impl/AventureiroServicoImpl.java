package br.com.projeto.aventura.servico.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import br.com.projeto.aventura.modelo.Avaliacao;
import br.com.projeto.aventura.modelo.Aventureiro;
import br.com.projeto.aventura.modelo.Habilidade;
import br.com.projeto.aventura.modelo.Missao;
import br.com.projeto.aventura.modelo.MissaoDificuldade;
import br.com.projeto.aventura.modelo.MissaoProgresso;
import br.com.projeto.aventura.modelo.Posicao;
import br.com.projeto.aventura.modelo.UnidadeHabilidade;
import br.com.projeto.aventura.modelo.UnidadeHabilidadeChave;
import br.com.projeto.aventura.modelo.abstrato.Unidade;
import br.com.projeto.aventura.repositorio.AventureiroRepositorio;
import br.com.projeto.aventura.servico.AventureiroServico;
import br.com.projeto.aventura.servico.MissaoProgressoServico;
import br.com.projeto.aventura.servico.MissaoServico;
import br.com.projeto.aventura.servico.UnidadeHabilidadeServico;
import br.com.projeto.aventura.servico.UnidadeServico;

@Service("aventureiroServico")
public class AventureiroServicoImpl implements AventureiroServico {

	private AventureiroRepositorio aventureiroRepo;
	private UnidadeHabilidadeServico unidadeHabilidadeServ;
	private MissaoProgressoServico missaoProgServ;
	private MissaoServico missaoServ;
	private UnidadeServico unidadeServ;

	@Autowired
	public AventureiroServicoImpl(AventureiroRepositorio aventureiroRepo,
			UnidadeHabilidadeServico unidadeHabilidadeServ, MissaoProgressoServico missaoProgServ,
			MissaoServico missaoServ,UnidadeServico unidadeServ) {
		super();
		this.aventureiroRepo = aventureiroRepo;
		this.unidadeHabilidadeServ = unidadeHabilidadeServ;
		this.unidadeServ = unidadeServ;
		this.missaoProgServ = missaoProgServ;
		this.missaoServ = missaoServ;
	}

	@Override
	public Aventureiro evoluirHabilidades(Avaliacao avaliacao) throws Exception {
		Aventureiro av = encontrarAventureiroPorIdPessoa(avaliacao.getAvaliacaoChave().getIdPessoa());
		MissaoProgresso mp = missaoProgServ.buscarMissaoProgresso(avaliacao.getAvaliacaoChave().getIdMissaoProgresso());
		Missao m = missaoServ.encontrarMissao(mp.getIdMissao());
		List<Habilidade> habilidadesUtilizadas = new ArrayList<Habilidade>();

		List<UnidadeHabilidade> avHabilidades = unidadeHabilidadeServ.listarUnidadeHabilidade(av.getIdUnidade());
		if (avHabilidades == null) {
			avHabilidades = new ArrayList<UnidadeHabilidade>();
		}

		for (MissaoDificuldade md : m.getListaDificuldades()) {
			habilidadesUtilizadas.add(md.getHabilidade());
		}

		boolean possuiHabilidade;
		for (Habilidade habilidade : habilidadesUtilizadas) {
			possuiHabilidade = false;

			for (UnidadeHabilidade avHabilidade : avHabilidades) {

				if (avHabilidade.getUnidadeHabilidadeChave().getIdHabilidade() == habilidade.getIdHabilidade()) {
					possuiHabilidade = true;

					int exp = avHabilidade.getExp() + avaliacao.getEstrelas() * 20;
					int expLevelUp = (int) (avHabilidade.getNivel() * 100);

					while (exp >= expLevelUp) {
						expLevelUp = (int) (avHabilidade.getNivel() * 100);

						avHabilidade.setNivel(avHabilidade.getNivel() + 1);

						exp -= expLevelUp;

					}
					avHabilidade.setExp(exp);

					unidadeHabilidadeServ.atualizarUnidadeHabilidade(avHabilidade);

				} // Fim possui Habilidade

			} // Fim for

			if (!possuiHabilidade) {
				UnidadeHabilidade newHabilidade = new UnidadeHabilidade();
				newHabilidade.setExp(0);
				newHabilidade.setNivel(1L);
				newHabilidade.setUnidadeHabilidadeChave(new UnidadeHabilidadeChave());
				newHabilidade.getUnidadeHabilidadeChave().setIdHabilidade(habilidade.getIdHabilidade());
				newHabilidade.getUnidadeHabilidadeChave().setIdUnidade(av.getIdUnidade());

				unidadeHabilidadeServ.cadastrarUnidadeHabilidade(newHabilidade);
			}

		}

		return av;
	}

	@Override
	public Aventureiro encontrarAventureiroPorIdPessoa(long idPessoa) throws Exception {
		try {
			return aventureiroRepo.buscarAventureiroPorIdPessoa(idPessoa);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(idPessoa);
		}
		throw new HttpClientErrorException(HttpStatus.NOT_ACCEPTABLE);

	}

	@Override
	public Aventureiro atualizarAventureiro(Aventureiro aventureiro) throws Exception {
		try {
			return aventureiroRepo.atualizarAventureiro(aventureiro);
		} catch (Exception e) {
			e.printStackTrace();
		}
		throw new HttpClientErrorException(HttpStatus.NOT_ACCEPTABLE);
	}

	@Override
	public Aventureiro cadastrarAventureiro(long idPessoa, double latitude, double longitude) throws Exception {
		Aventureiro aventureiro = new Aventureiro();
		aventureiro.setIdPessoa(idPessoa);
		
		try {
			aventureiro = aventureiroRepo.cadastrarAventureiro(aventureiro);

			aventureiro.setPosicao(new Posicao());
			aventureiro.getPosicao().setIdUnidade(aventureiro.getIdUnidade());
			aventureiro.getPosicao().setLatitude(50d);
			aventureiro.getPosicao().setLongitude(50d);
			aventureiro = aventureiroRepo.atualizarAventureiro(aventureiro);
			return aventureiro;
		} catch (Exception e) {
			e.printStackTrace();
		}
		throw new HttpClientErrorException(HttpStatus.NOT_ACCEPTABLE);
	}

}
