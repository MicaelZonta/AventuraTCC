package br.com.projeto.aventura.repositorio;

import java.util.List;

import br.com.projeto.aventura.modelo.Habilidade;

public interface HabilidadeRepositorio {

	public List<Habilidade> listar() throws Exception;

}
