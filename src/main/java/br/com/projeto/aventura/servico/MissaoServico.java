package br.com.projeto.aventura.servico;

import java.util.List;

import br.com.projeto.aventura.modelo.Missao;
import br.com.projeto.aventura.modelo.PessoaFisica;

public interface MissaoServico {

	public Missao cadastrarMissao(Missao missao) throws Exception;

	public Missao editarMissao(Missao missao) throws Exception;

	public Missao deletarMissao(Missao missao) throws Exception;
	
	public Missao encontrarMissao(long idMissao) throws Exception;

	public List<Missao> listarMissao(PessoaFisica usuario, Missao missao) throws Exception;
}
