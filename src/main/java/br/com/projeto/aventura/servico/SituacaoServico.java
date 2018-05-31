package br.com.projeto.aventura.servico;

import java.util.List;

import br.com.projeto.aventura.modelo.Situacao;

public interface SituacaoServico {

	public List<Situacao> listar() throws Exception;

}
