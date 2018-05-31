package br.com.projeto.aventura.servico;

import java.util.List;

import br.com.projeto.aventura.modelo.Habilidade;

public interface HabilidadeServico {

	public List<Habilidade> listar() throws Exception;

}
