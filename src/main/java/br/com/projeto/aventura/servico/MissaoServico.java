package br.com.projeto.aventura.servico;

import java.util.List;

import br.com.projeto.aventura.modelo.Missao;
import br.com.projeto.aventura.modelo.Usuario;

public interface MissaoServico {

	public Missao cadastrarMissao(Missao missao) throws Exception;

	public Missao editarMissao(Missao missao) throws Exception;

	public Missao deletarMissao(Missao missao) throws Exception;

	public List<Missao> listarMissao(Usuario usuario, Missao missao) throws Exception;
}
