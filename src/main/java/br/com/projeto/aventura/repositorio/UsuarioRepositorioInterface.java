package br.com.projeto.aventura.repositorio;

import br.com.projeto.aventura.modelo.Usuario;

public interface UsuarioRepositorioInterface {

	public Usuario findByName(Usuario conta);
	
}
