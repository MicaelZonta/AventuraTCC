package br.com.projeto.aventura.servico;

import java.util.List;

import br.com.projeto.aventura.modelo.Missao;
import br.com.projeto.aventura.modelo.MissaoProgresso;
import br.com.projeto.aventura.modelo.PessoaFisica;
import br.com.projeto.aventura.modelo.Situacao;
import br.com.projeto.aventura.modelo.SituacaoEnum;
import br.com.projeto.aventura.modelo.TarefaProgresso;
import br.com.projeto.aventura.modelo.abstrato.Pessoa;

public interface MissaoProgressoServico {

	public MissaoProgresso aceitarMissao(Missao missao, PessoaFisica pessoa) throws Exception;

	public MissaoProgresso cancelarMissao(Missao missao, PessoaFisica pessoaFisica, Pessoa pessoa) throws Exception;

	public MissaoProgresso completarTarefa(Missao missao, PessoaFisica pessoa, TarefaProgresso tarefa) throws Exception;

	public MissaoProgresso pausarMissao(Missao missao, PessoaFisica pessoaFisica , Pessoa pessoa) throws Exception;

	public MissaoProgresso buscarMissaoProgresso(Missao missao, PessoaFisica pessoa) throws Exception;

	public List<MissaoProgresso> listarMissaoProgresso(PessoaFisica pessoa) throws Exception;

	public List<MissaoProgresso> listarMissaoProgresso(Missao missao) throws Exception;
	
	public Situacao encontrarSituacao(SituacaoEnum situacaoEnum) throws Exception;

	public MissaoProgresso atualizarMissaoProgresso(Missao missao, Pessoa pessoa, PessoaFisica pessoaFisica, int idSituacao) throws Exception;
	
	public MissaoProgresso buscarMissaoProgresso(long idMissaoProgresso) throws Exception;
}
