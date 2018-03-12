package br.com.projeto.aventura.repositorio;

import br.com.projeto.aventura.modelo.Usuario;

public interface UsuarioRepositorio {

	public Usuario encontrarUsername(String usuario) throws Exception;
	
}
