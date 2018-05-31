package br.com.projeto.aventura.servico;

import static org.junit.Assert.*;

import org.junit.Test;

import br.com.projeto.aventura.modelo.Aventureiro;
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

public class AventureiroTest {

	MissaoServico missaoServico;
	PessoaServico pessoaServico;
	UsuarioServico usuarioServico;
	PessoaFisicaServico pessoaFisicaServico;
	MissaoProgressoServico missaoProgressoServico;
	AvaliacaoServico avaliacaoServico;
	UnidadeHabilidadeServico unidadeHabilidadeServico;
	AventureiroServico aventureiroServico;

	public AventureiroTest() {
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
	public void testCadastrarAventureiro() throws Exception {
		Aventureiro av =aventureiroServico.cadastrarAventureiro(3, 50, 50);
		assertNotNull(av);
	}

}
