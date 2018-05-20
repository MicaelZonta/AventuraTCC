package br.com.projeto.aventura.recurso;

import static org.junit.Assert.*;
import static org.mockito.Matchers.notNull;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import br.com.projeto.aventura.modelo.Missao;
import br.com.projeto.aventura.modelo.MissaoDificuldade;
import br.com.projeto.aventura.modelo.MissaoProgresso;
import br.com.projeto.aventura.modelo.MissaoTarefa;
import br.com.projeto.aventura.modelo.Usuario;
import br.com.projeto.aventura.modelo.abstrato.Pessoa;
import br.com.projeto.aventura.repositorio.impl.MissaoProgressoRepositorioImpl;
import br.com.projeto.aventura.repositorio.impl.MissaoRepositorioImpl;
import br.com.projeto.aventura.repositorio.impl.PessoaRepositorioImpl;
import br.com.projeto.aventura.repositorio.impl.UsuarioRepositorioImpl;
import br.com.projeto.aventura.servico.MissaoProgressoServico;
import br.com.projeto.aventura.servico.MissaoServico;
import br.com.projeto.aventura.servico.PessoaServico;
import br.com.projeto.aventura.servico.UsuarioServico;
import br.com.projeto.aventura.servico.impl.MissaoProgressoServicoImpl;
import br.com.projeto.aventura.servico.impl.MissaoServicoImpl;
import br.com.projeto.aventura.servico.impl.PessoaServicoImpl;
import br.com.projeto.aventura.servico.impl.UsuarioServicoImpl;
import ch.qos.logback.core.util.COWArrayList;

public class MissaoRecursoTest {

	MissaoServico missaoServico;
	PessoaServico pessoaServico;
	UsuarioServico usuarioServico;

	public MissaoRecursoTest() {
		MissaoProgressoServicoImpl mpSer = new MissaoProgressoServicoImpl(new MissaoProgressoRepositorioImpl());
		UsuarioServicoImpl usuarioSer = new UsuarioServicoImpl(new UsuarioRepositorioImpl());
		missaoServico = new MissaoServicoImpl(new MissaoRepositorioImpl(), mpSer, usuarioSer);
		pessoaServico = new PessoaServicoImpl(new PessoaRepositorioImpl());
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
	public void testEditarMissao() {
		fail("Not yet implemented");
	}

	@Test
	public void testDeletarMissao() {
		fail("Not yet implemented");
	}

	@Test
	public void testListarMissao() {
		fail("Not yet implemented");
	}

	@Test
	public void testEncontrarMissao() {
		fail("Not yet implemented");
	}

}
