package br.com.projeto.aventura.negocios;

import br.com.projeto.aventura.modelo.Usuario;

public interface UsuarioNegociosInterface {

	public Usuario findByUsername(String username);
	
}
