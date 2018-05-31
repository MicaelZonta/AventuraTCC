package br.com.projeto.aventura.repositorio;

import java.util.List;

import br.com.projeto.aventura.modelo.Situacao;

public interface SituacaoRepositorio {

	public List<Situacao> listar() throws Exception;

}
