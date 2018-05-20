package br.com.projeto.aventura.servico;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Test;

import br.com.projeto.aventura.modelo.Missao;
import br.com.projeto.aventura.modelo.MissaoDificuldade;
import br.com.projeto.aventura.modelo.MissaoProgresso;
import br.com.projeto.aventura.modelo.MissaoTarefa;
import br.com.projeto.aventura.modelo.PessoaFisica;
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
		MissaoProgressoServicoImpl mpSer = new MissaoProgressoServicoImpl(new MissaoProgressoRepositorioImpl(), new TarefaProgressoRepositorioImpl());
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
	public void testCancelarMissao() {
		fail("Not yet implemented");
	}

	@Test
	public void testCompletarTarefa() {
		fail("Not yet implemented");
	}

	@Test
	public void testPausarMissao() {
		fail("Not yet implemented");
	}

	@Test
	public void testBuscarMissaoProgresso() {
		fail("Not yet implemented");
	}

	@Test
	public void testListarMissaoProgressoPessoaFisica() {
		fail("Not yet implemented");
	}

	@Test
	public void testListarMissaoProgressoMissao() {
		fail("Not yet implemented");
	}

}
