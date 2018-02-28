package br.com.projeto.aventura.repositorio;

import java.util.List;

public interface Repositorio<T> {

	T buscaPorId(Long id);
	
	List<T> buscaTodos();
	
	T inserir(T object);
	
	T atualizar(Long id, T object);
	
	T excluir(T object);
	
}
