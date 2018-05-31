package br.com.projeto.aventura.servico;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Test;

import br.com.projeto.aventura.configuracao.HibernateConfig;
import br.com.projeto.aventura.modelo.Avaliacao;
import br.com.projeto.aventura.modelo.Aventureiro;
import br.com.projeto.aventura.modelo.Habilidade;
import br.com.projeto.aventura.modelo.Missao;
import br.com.projeto.aventura.modelo.MissaoDificuldade;
import br.com.projeto.aventura.modelo.MissaoProgresso;
import br.com.projeto.aventura.modelo.MissaoTarefa;
import br.com.projeto.aventura.modelo.PessoaFisica;
import br.com.projeto.aventura.modelo.SituacaoEnum;
import br.com.projeto.aventura.modelo.UnidadeHabilidade;
import br.com.projeto.aventura.modelo.Usuario;
import br.com.projeto.aventura.modelo.abstrato.Pessoa;
import br.com.projeto.aventura.repositorio.impl.AvaliacaoRepositorioImpl;
import br.com.projeto.aventura.repositorio.impl.AventureiroRepositorioImpl;
import br.com.projeto.aventura.repositorio.impl.MissaoProgressoRepositorioImpl;
import br.com.projeto.aventura.repositorio.impl.MissaoRepositorioImpl;
import br.com.projeto.aventura.repositorio.impl.PessoaFisicaRepositorioImpl;
import br.com.projeto.aventura.repositorio.impl.PessoaRepositorioImpl;
import br.com.projeto.aventura.repositorio.impl.TarefaProgressoRepositorioImpl;
import br.com.projeto.aventura.repositorio.impl.UnidadeHabilidadeRepositorioImpl;
import br.com.projeto.aventura.repositorio.impl.UnidadeRepositorioImpl;
import br.com.projeto.aventura.repositorio.impl.UsuarioRepositorioImpl;
import br.com.projeto.aventura.servico.impl.AvaliacaoServicoImpl;
import br.com.projeto.aventura.servico.impl.AventureiroServicoImpl;
import br.com.projeto.aventura.servico.impl.MissaoProgressoServicoImpl;
import br.com.projeto.aventura.servico.impl.MissaoServicoImpl;
import br.com.projeto.aventura.servico.impl.PessoaFisicaServicoImpl;
import br.com.projeto.aventura.servico.impl.PessoaServicoImpl;
import br.com.projeto.aventura.servico.impl.UnidadeHabilidadeServicoImpl;
import br.com.projeto.aventura.servico.impl.UnidadeServicoImpl;
import br.com.projeto.aventura.servico.impl.UsuarioServicoImpl;

public class AvaliacaoTest {

	MissaoServico missaoServico;
	PessoaServico pessoaServico;
	UsuarioServico usuarioServico;
	PessoaFisicaServico pessoaFisicaServico;
	MissaoProgressoServico missaoProgressoServico;
	AvaliacaoServico avaliacaoServico;
	UnidadeHabilidadeServico unidadeHabilidadeServico;
	AventureiroServico aventureiroServico;

	public AvaliacaoTest() {
		MissaoProgressoServicoImpl mpSer = new MissaoProgressoServicoImpl(new MissaoProgressoRepositorioImpl(),
				new TarefaProgressoRepositorioImpl());
		UsuarioServicoImpl usuarioSer = new UsuarioServicoImpl(new UsuarioRepositorioImpl());

		missaoServico = new MissaoServicoImpl(new MissaoRepositorioImpl(), mpSer, usuarioSer);
		pessoaServico = new PessoaServicoImpl(new PessoaRepositorioImpl());
		pessoaFisicaServico = new PessoaFisicaServicoImpl(new PessoaFisicaRepositorioImpl());
		usuarioServico = usuarioSer;
		missaoProgressoServico = mpSer;
		avaliacaoServico = new AvaliacaoServicoImpl(new AvaliacaoRepositorioImpl(), missaoProgressoServico,
				missaoServico, pessoaServico, pessoaFisicaServico);
		unidadeHabilidadeServico = new UnidadeHabilidadeServicoImpl(new UnidadeHabilidadeRepositorioImpl());
		aventureiroServico = new AventureiroServicoImpl(new AventureiroRepositorioImpl(), unidadeHabilidadeServico,
				missaoProgressoServico, missaoServico, new UnidadeServicoImpl(new UnidadeRepositorioImpl()));

	}

	@Test
	public void testAceitarMissao() throws Exception {
		/*
		 * CADASTRAR MISSÃO PARA TESTAR
		 */
		// Busca Usuario
		Usuario user = usuarioServico.encontrarUsuario("user");
		user.setFavor(10l);
		assertNotNull(user);
		long favor = (user.getFavor() - 1);

		// Busca Pessoa
		Pessoa pessoa = pessoaServico.encontrarPessoa(user.getIdUsuario());
		assertNotNull(pessoa);

		// Instância missão
		Missao missao = new Missao();
		missao.setDataCriacao(new Date());
		missao.setDescricao("Comer Goiabada");
		missao.setIdPessoa(1l);
		missao.setLatitude(50d);
		missao.setListaDificuldades(new ArrayList<MissaoDificuldade>());
		missao.setMaxAventureiros(2);

		List<MissaoTarefa> lista = new ArrayList<MissaoTarefa>();
		MissaoTarefa tarefa = new MissaoTarefa();
		tarefa.setDescricao("Vá até a goiabada");
		tarefa.setNome("Encontre");
		lista.add(tarefa);
		missao.setListaTarefas(lista);

		missao.setListaProgressos(new ArrayList<MissaoProgresso>());
		missao.setLongitude(50d);
		missao.setMaxAventureiros(2);
		missao.setNome("Goibada" + Math.random() * 100);

		// Missão dificuldade
		MissaoDificuldade md = new MissaoDificuldade();
		Habilidade h1 = new Habilidade();
		h1.setIdHabilidade(1L);
		h1.setNome("Golf");
		md.setHabilidade(h1);
		missao.getListaDificuldades().add(md);
		// Cadastra a missão
		missao = missaoServico.cadastrarMissao(missao, user);
		assertNotNull(missao);
		assertEquals((long) favor, (long) user.getFavor());

		long idMissao = missao.getIdMissao();

		// Busca Usuario
		Usuario usuario = usuarioServico.encontrarUsuario("micael");
		assertNotNull(usuario);

		// Busca Pessoa
		PessoaFisica pessoaFisica = pessoaFisicaServico.encontrarPessoaFisicaPorIdUsuario(usuario.getIdUsuario());
		assertNotNull(pessoaFisica);

		// Busca Missao
		Missao missaoNova = missaoServico.encontrarMissao(idMissao);
		assertNotNull(missaoNova);
		assertTrue(missaoNova.getListaDificuldades().size() > 0);

		
		// Aceita missão
		MissaoProgresso mp = missaoProgressoServico.aceitarMissao(missaoNova, pessoaFisica);
		assertNotNull(mp);
		assertTrue(mp.getIdMissaoProgresso() > 0);

		// Completar Tarefa
		mp = missaoProgressoServico.completarTarefa(missaoNova, pessoaFisica, mp.getTarefas().get(0));
		assertNotNull(mp);
		assertTrue(mp.getTarefas().get(0).getSituacao().getIdSituacao() == SituacaoEnum.COMPLETA.getItem());

		// Avaliar
		Avaliacao avaliacao = avaliacaoServico.cadastrarAvaliacao(5, "", missaoNova, user, usuario);
		assertNotNull(avaliacao);

		/// List Habilidade
		Aventureiro av = aventureiroServico.evoluirHabilidades(avaliacao);
		assertNotNull(av);
		List<UnidadeHabilidade> listUnidade = unidadeHabilidadeServico.listarUnidadeHabilidade(av.getIdUnidade());
		assertNotNull(listUnidade);
		assertTrue(listUnidade.size() > 0);
	}

}
