package br.com.projeto.aventura.repositorio;

import java.util.List;

import br.com.projeto.aventura.modelo.Missao;
import br.com.projeto.aventura.modelo.PessoaFisica;

/*
 * Parte da Missão em si
 */
public interface MissaoRepositorio {

	public Missao cadastrarMissao(Missao missao) throws Exception;

	public Missao editarMissao(Missao missao) throws Exception;

	public Missao deletarMissao(Missao missao) throws Exception;

	public List<Missao> listarMissao(PessoaFisica pessoa, Missao missao) throws Exception;

}
