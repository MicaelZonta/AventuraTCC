package br.com.projeto.aventura.servico;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpClientErrorException;

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
import br.com.projeto.aventura.servico.MissaoServico;
import br.com.projeto.aventura.servico.PessoaFisicaServico;
import br.com.projeto.aventura.servico.PessoaServico;
import br.com.projeto.aventura.servico.UsuarioServico;
import br.com.projeto.aventura.servico.impl.MissaoProgressoServicoImpl;
import br.com.projeto.aventura.servico.impl.MissaoServicoImpl;
import br.com.projeto.aventura.servico.impl.PessoaFisicaServicoImpl;
import br.com.projeto.aventura.servico.impl.PessoaServicoImpl;
import br.com.projeto.aventura.servico.impl.UsuarioServicoImpl;

public class MissaoTest {

	MissaoServico missaoServico;
	PessoaServico pessoaServico;
	UsuarioServico usuarioServico;
	PessoaFisicaServico pessoaFisicaServico;

	public MissaoTest() {
		MissaoProgressoServicoImpl mpSer = new MissaoProgressoServicoImpl(new MissaoProgressoRepositorioImpl(), new TarefaProgressoRepositorioImpl());
		UsuarioServicoImpl usuarioSer = new UsuarioServicoImpl(new UsuarioRepositorioImpl());

		missaoServico = new MissaoServicoImpl(new MissaoRepositorioImpl(), mpSer, usuarioSer);
		pessoaServico = new PessoaServicoImpl(new PessoaRepositorioImpl());
		pessoaFisicaServico = new PessoaFisicaServicoImpl(new PessoaFisicaRepositorioImpl());
		usuarioServico = usuarioSer;

	}

	@Test
	public void testCadastrarMissao() throws Exception {
		// Busca Usuario
		Usuario usuario = usuarioServico.encontrarUsuario("user");
		usuario.setFavor(10l);
		assertNotNull(usuario);
		long favor = (usuario.getFavor() - 1);

		// Busca Pessoa
		Pessoa pessoa = pessoaServico.encontrarPessoa(usuario.getIdUsuario());
		assertNotNull(pessoa);

		// Instância missão
		Missao missao = new Missao();
		missao.setDataCriacao(new Date());
		missao.setDescricao("Matar Goku");
		missao.setIdPessoa(1l);
		missao.setLatitude(50d);
		missao.setListaDificuldades(new ArrayList<MissaoDificuldade>());
		missao.setMaxAventureiros(2);

		List<MissaoTarefa> lista = new ArrayList<MissaoTarefa>();
		MissaoTarefa tarefa = new MissaoTarefa();
		tarefa.setDescricao("Vá até Goku");
		tarefa.setNome("Encontre");
		MissaoTarefa tarefa2 = new MissaoTarefa();
		tarefa2.setDescricao("Mate Goku");
		tarefa2.setNome("Mate");
		lista.add(tarefa);
		lista.add(tarefa2);
		missao.setListaTarefas(lista);
		missao.setListaProgressos(new ArrayList<MissaoProgresso>());
		missao.setLongitude(50d);
		missao.setMaxAventureiros(2);
		missao.setNome("Missão Impossível");

		// Cadastra a missão
		missao = missaoServico.cadastrarMissao(missao, usuario);
		assertNotNull(missao);
		assertEquals((long) favor, (long) usuario.getFavor());
	}

	@Test
	public void testEditarMissao() throws Exception {
		Usuario usuario = usuarioServico.encontrarUsuario("user");
		assertNotNull(usuario);

		Pessoa pessoa = pessoaServico.encontrarPessoa(usuario.getIdUsuario());
		assertNotNull(pessoa);

		String nomeChange = "";
		Missao missao = missaoServico.encontrarMissao(4l);
		if (missao.getNome().equals("Missão Impossível")) {
			nomeChange = "Missão Possível";
		} else {
			nomeChange = "Missão Impossível";
		}
		missao.setNome(nomeChange);
		Missao missaoOg = missaoServico.encontrarMissao(missao.getIdMissao());

		if (missaoOg.getIdPessoa() != missao.getIdPessoa()) {
			throw new HttpClientErrorException(HttpStatus.UNAUTHORIZED);
		}
		if (missaoOg.getIdPessoa() != pessoa.getIdPessoa()) {
			throw new HttpClientErrorException(HttpStatus.UNAUTHORIZED);
		}

		boolean update = false;
		if (missao.getNome() != null && missao.getNome().length() > 0) {
			missaoOg.setNome(missao.getNome());
			update = true;
		}
		if (missao.getDescricao() != null && missao.getDescricao().length() > 0) {
			missaoOg.setDescricao(missao.getDescricao());
			update = true;
		}

		if (!update) {
			throw new HttpClientErrorException(HttpStatus.NO_CONTENT);
		}

		missaoOg = missaoServico.editarMissao(missaoOg);

		assertEquals(nomeChange, missaoOg.getNome());
	}

	@Test
	public void testDeletarMissao() throws Exception {
		// Busca Usuario
		Usuario usuario = usuarioServico.encontrarUsuario("user");
		usuario.setFavor(10l);
		assertNotNull(usuario);
		long favor = (usuario.getFavor() - 1);

		// Busca Pessoa
		Pessoa pessoa = pessoaServico.encontrarPessoa(usuario.getIdUsuario());
		assertNotNull(pessoa);

		// Instância missão
		Missao missao = new Missao();
		missao.setDataCriacao(new Date());
		missao.setDescricao("Matar Jiren");
		missao.setIdPessoa(1l);
		missao.setLatitude(50d);
		missao.setListaDificuldades(new ArrayList<MissaoDificuldade>());
		missao.setMaxAventureiros(2);

		List<MissaoTarefa> lista = new ArrayList<MissaoTarefa>();
		MissaoTarefa tarefa = new MissaoTarefa();
		tarefa.setDescricao("Vá até Jiren");
		tarefa.setNome("Encontre");
		MissaoTarefa tarefa2 = new MissaoTarefa();
		tarefa2.setDescricao("Mate Jiren");
		tarefa2.setNome("Mate");
		lista.add(tarefa);
		lista.add(tarefa2);
		missao.setListaTarefas(lista);
		missao.setListaProgressos(new ArrayList<MissaoProgresso>());
		missao.setLongitude(50d);
		missao.setMaxAventureiros(2);
		missao.setNome("Missão Impossível");

		// Cadastra a missão
		missao = missaoServico.cadastrarMissao(missao, usuario);
		assertNotNull(missao);
		assertEquals((long) favor, (long) usuario.getFavor());

		long idMissao = missao.getIdMissao();

		missaoServico.deletarMissao(missao);
		missao = null;

		try {
			missao = missaoServico.encontrarMissao(idMissao);
		} catch (Exception e) {
			assertTrue(true);
			missao = null;
		}
		assertTrue(missao == null);
	}

	@Test
	public void testListarMissao() throws Exception {
		Usuario usuario = usuarioServico.encontrarUsuario("user");
		assertNotNull(usuario);

		PessoaFisica pessoa = pessoaFisicaServico.encontrarPessoaFisicaPorIdUsuario(usuario.getIdUsuario());
		assertNotNull(pessoa);

		Missao missao = new Missao();
		missao.setDescricao("Goku");
		List<Missao> missoes = missaoServico.listarMissao(pessoa, missao);

		assertNotNull(missoes);
		assertTrue((missoes.size() > 0));

	}

	@Test
	public void testEncontrarMissao() throws Exception {
		Missao missao = missaoServico.encontrarMissao(4l);
		assertNotNull(missao);
	}

}
