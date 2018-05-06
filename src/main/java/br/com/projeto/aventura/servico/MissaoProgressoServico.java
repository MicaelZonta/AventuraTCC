package br.com.projeto.aventura.servico;

import java.util.List;

import br.com.projeto.aventura.modelo.Missao;
import br.com.projeto.aventura.modelo.MissaoProgresso;
import br.com.projeto.aventura.modelo.PessoaFisica;
import br.com.projeto.aventura.modelo.TarefaProgresso;
import br.com.projeto.aventura.modelo.Usuario;

public interface MissaoProgressoServico {

	public MissaoProgresso aceitarMissao(Missao missao, PessoaFisica pessoa) throws Exception;

	public MissaoProgresso cancelarMissao(Missao missao, Usuario usuario) throws Exception;

	public MissaoProgresso completarTarefa(Missao missao, Usuario usuario, TarefaProgresso tarefa) throws Exception;

	public MissaoProgresso pausarMissao(Missao missao, Usuario usuario) throws Exception;

	public MissaoProgresso buscarMissaoProgresso(Missao missao, Usuario usuario) throws Exception;

	public List<MissaoProgresso> listarMissaoProgresso(Usuario usuario) throws Exception;

	public List<MissaoProgresso> listarMissaoProgresso(Missao missao) throws Exception;


}
