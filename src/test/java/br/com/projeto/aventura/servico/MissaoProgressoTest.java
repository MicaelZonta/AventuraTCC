package br.com.projeto.aventura.servico;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Test;

import br.com.projeto.aventura.modelo.Missao;
import br.com.projeto.aventura.modelo.MissaoDificuldade;
import br.com.projeto.aventura.modelo.MissaoProgresso;
import br.com.projeto.aventura.modelo.MissaoTarefa;
import br.com.projeto.aventura.modelo.PessoaFisica;
import br.com.projeto.aventura.modelo.SituacaoEnum;
import br.com.projeto.aventura.modelo.TarefaProgresso;
import br.com.projeto.aventura.modelo.Usuario;
import br.com.projeto.aventura.modelo.abstrato.Pessoa;
import br.com.projeto.aventura.repositorio.impl.MissaoProgressoRepositorioImpl;
import br.com.projeto.aventura.repositorio.impl.MissaoRepositorioImpl;
import br.com.projeto.aventura.repositorio.impl.PessoaFisicaRepositorioImpl;
import br.com.projeto.aventura.repositorio.impl.PessoaRepositorioImpl;
import br.com.projeto.aventura.repositorio.impl.TarefaProgressoRepositorioImpl;
import br.com.projeto.aventura.repositorio.impl.UsuarioRepositorioImpl;
import br.com.projeto.aventura.servico.impl.MissaoProgressoServicoImpl;
import br.com.projeto.aventura.servico.impl.MissaoServicoImpl;
import br.com.projeto.aventura.servico.impl.PessoaFisicaServicoImpl;
import br.com.projeto.aventura.servico.impl.PessoaServicoImpl;
import br.com.projeto.aventura.servico.impl.UsuarioServicoImpl;

public class MissaoProgressoTest {

	MissaoServico missaoServico;
	PessoaServico pessoaServico;
	UsuarioServico usuarioServico;
	PessoaFisicaServico pessoaFisicaServico;
	MissaoProgressoServico missaoProgressoServico;

	public MissaoProgressoTest() {
		MissaoProgressoServicoImpl mpSer = new MissaoProgressoServicoImpl(new MissaoProgressoRepositorioImpl(),
				new TarefaProgressoRepositorioImpl());
		UsuarioServicoImpl usuarioSer = new UsuarioServicoImpl(new UsuarioRepositorioImpl());

		missaoServico = new MissaoServicoImpl(new MissaoRepositorioImpl(), mpSer, usuarioSer);
		pessoaServico = new PessoaServicoImpl(new PessoaRepositorioImpl());
		pessoaFisicaServico = new PessoaFisicaServicoImpl(new PessoaFisicaRepositorioImpl());
		usuarioServico = usuarioSer;
		missaoProgressoServico = mpSer;
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
		MissaoTarefa tarefa2 = new MissaoTarefa();
		tarefa2.setDescricao("Coma");
		tarefa2.setNome("Coma");
		lista.add(tarefa);
		lista.add(tarefa2);
		missao.setListaTarefas(lista);
		missao.setListaProgressos(new ArrayList<MissaoProgresso>());
		missao.setLongitude(50d);
		missao.setMaxAventureiros(2);
		missao.setNome("Goibada" + Math.random() * 100);

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

		// Aceita missão
		MissaoProgresso mp = missaoProgressoServico.aceitarMissao(missaoNova, pessoaFisica);
		assertNotNull(mp);
		assertTrue(mp.getIdMissaoProgresso() > 0);
	}

	@Test
	public void testCancelarMissao() throws Exception {
		// Busca Usuario
		Usuario usuario = usuarioServico.encontrarUsuario("user");
		assertNotNull(usuario);

		// Busca Pessoa
		Pessoa giver = pessoaServico.encontrarPessoa(usuario.getIdUsuario());
		assertNotNull(giver);

		// Buscar Usuario
		usuario = usuarioServico.encontrarUsuario("user");
		assertNotNull(usuario);

		// Busca Usuario
		usuario = usuarioServico.encontrarUsuario("micael");
		assertNotNull(usuario);

		// Busca Pessoa
		PessoaFisica taker = pessoaFisicaServico.encontrarPessoaFisicaPorIdUsuario(usuario.getIdUsuario());
		assertNotNull(taker);

		// Busca MP
		List<MissaoProgresso> mpFind = missaoProgressoServico.listarMissaoProgresso(taker);
		assertTrue(mpFind.size() > 0);

		// Busca missão
		Missao missao = missaoServico.encontrarMissao(mpFind.get(0).getIdMissao());
		assertNotNull(missao);

		MissaoProgresso mp = missaoProgressoServico.cancelarMissao(missao, taker, giver);
		assertNotNull(mp);
		assertTrue(mp.getSituacao().getIdSituacao() == SituacaoEnum.CANCELADO.getItem());

		mp = missaoProgressoServico.atualizarMissaoProgresso(missao, giver, taker, SituacaoEnum.INICIADO.getItem());
	}

	@Test
	public void testCompletarTarefa() throws Exception {

		// Busca Usuario
		Usuario usuario = usuarioServico.encontrarUsuario("micael");
		assertNotNull(usuario);

		// Busca Pessoa
		PessoaFisica taker = pessoaFisicaServico.encontrarPessoaFisicaPorIdUsuario(usuario.getIdUsuario());
		assertNotNull(taker);

		// Busca MP
		List<MissaoProgresso> mpFind = missaoProgressoServico.listarMissaoProgresso(taker);
		assertTrue(mpFind.size() > 0);

		// Busca missão
		Missao missao = missaoServico.encontrarMissao(mpFind.get(mpFind.size() - 1).getIdMissao());
		assertNotNull(missao);

		// Busca Tarefa
		TarefaProgresso tarefa = mpFind.get(mpFind.size() - 1).getTarefas().get(0);

		MissaoProgresso mp = missaoProgressoServico.completarTarefa(missao, taker, tarefa);
		assertNotNull(mp);
		assertTrue(mp.getTarefas().get(0).getSituacao().getIdSituacao() == SituacaoEnum.COMPLETA.getItem());

	}

	@Test
	public void testPausarMissao() throws Exception {
		// Busca Usuario
		Usuario usuario = usuarioServico.encontrarUsuario("user");
		assertNotNull(usuario);

		// Busca Pessoa
		Pessoa giver = pessoaServico.encontrarPessoa(usuario.getIdUsuario());
		assertNotNull(giver);

		// Buscar Usuario
		usuario = usuarioServico.encontrarUsuario("user");
		assertNotNull(usuario);

		// Busca Usuario
		usuario = usuarioServico.encontrarUsuario("micael");
		assertNotNull(usuario);

		// Busca Pessoa
		PessoaFisica taker = pessoaFisicaServico.encontrarPessoaFisicaPorIdUsuario(usuario.getIdUsuario());
		assertNotNull(taker);

		// Busca MP
		List<MissaoProgresso> mpFind = missaoProgressoServico.listarMissaoProgresso(taker);
		assertTrue(mpFind.size() > 0);

		// Busca missão
		Missao missao = missaoServico.encontrarMissao(mpFind.get(0).getIdMissao());
		assertNotNull(missao);

		MissaoProgresso mp = missaoProgressoServico.atualizarMissaoProgresso(missao, giver, taker,
				SituacaoEnum.INICIADO.getItem());

		mp = missaoProgressoServico.pausarMissao(missao, taker, giver);
		assertNotNull(mp);
		assertTrue(mp.getSituacao().getIdSituacao() == SituacaoEnum.PAUSA.getItem());

		mp = missaoProgressoServico.atualizarMissaoProgresso(missao, giver, taker, SituacaoEnum.INICIADO.getItem());
	}

	@Test
	public void testBuscarMissaoProgresso() throws Exception {
		// Busca Usuario
		Usuario usuario = usuarioServico.encontrarUsuario("micael");
		assertNotNull(usuario);

		// Busca Pessoa
		PessoaFisica taker = pessoaFisicaServico.encontrarPessoaFisicaPorIdUsuario(usuario.getIdUsuario());
		assertNotNull(taker);

		// Busca MP
		List<MissaoProgresso> mpFind = missaoProgressoServico.listarMissaoProgresso(taker);
		assertTrue(mpFind.size() > 0);

		// Busca missão
		Missao missao = missaoServico.encontrarMissao(mpFind.get(0).getIdMissao());
		assertNotNull(missao);

		MissaoProgresso mp = missaoProgressoServico.buscarMissaoProgresso(missao, taker);
		assertNotNull(mp);
	}

	@Test
	public void testListarMissaoProgressoPessoaFisica() throws Exception {
		// Busca Usuario
		Usuario usuario = usuarioServico.encontrarUsuario("micael");
		assertNotNull(usuario);

		// Busca Pessoa
		PessoaFisica taker = pessoaFisicaServico.encontrarPessoaFisicaPorIdUsuario(usuario.getIdUsuario());
		assertNotNull(taker);

		// Busca MP
		List<MissaoProgresso> mpFind = missaoProgressoServico.listarMissaoProgresso(taker);
		assertTrue(mpFind.size() > 0);
	}

	@Test
	public void testListarMissaoProgressoMissao() throws Exception {
		// Busca Usuario
		Usuario usuario = usuarioServico.encontrarUsuario("micael");
		assertNotNull(usuario);

		// Busca Pessoa
		PessoaFisica taker = pessoaFisicaServico.encontrarPessoaFisicaPorIdUsuario(usuario.getIdUsuario());
		assertNotNull(taker);

		// Busca MP
		List<MissaoProgresso> mpFind = missaoProgressoServico.listarMissaoProgresso(taker);
		assertTrue(mpFind.size() > 0);

		// Busca missão
		Missao missao = missaoServico.encontrarMissao(mpFind.get(0).getIdMissao());
		assertNotNull(missao);

		List<MissaoProgresso> missaoList = missaoProgressoServico.listarMissaoProgresso(missao);
		assertNotNull(missaoList);
	}

}
