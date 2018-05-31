package br.com.projeto.aventura.servico;

import java.util.List;

import br.com.projeto.aventura.modelo.UnidadeHabilidade;

public interface UnidadeHabilidadeServico {

	public UnidadeHabilidade cadastrarUnidadeHabilidade(UnidadeHabilidade unidadeHabilidade) throws Exception;
	
	public UnidadeHabilidade atualizarUnidadeHabilidade(UnidadeHabilidade unidadeHabilidade) throws Exception;
	
	public List<UnidadeHabilidade> listarUnidadeHabilidade(long idUnidade) throws Exception;
	
}
