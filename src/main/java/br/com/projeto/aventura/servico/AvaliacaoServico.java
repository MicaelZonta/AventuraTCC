package br.com.projeto.aventura.servico;

import br.com.projeto.aventura.modelo.Avaliacao;
import br.com.projeto.aventura.modelo.Missao;
import br.com.projeto.aventura.modelo.Usuario;

public interface AvaliacaoServico {

	public Avaliacao cadastrarAvaliacao(Integer nota, String descricao,Missao missao, Usuario usuario, Usuario usuarioAvaliado) throws Exception;

}
