package br.com.projeto.aventura.negocios.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.projeto.aventura.modelo.Usuario;
import br.com.projeto.aventura.negocios.UsuarioNegociosInterface;
import br.com.projeto.aventura.repositorio.UsuarioRepositorioInterface;
import br.com.projeto.aventura.repositorio.impl.UsuarioRepositorio;

@Service("sUsuario")
public class UsuarioNegocios implements UsuarioNegociosInterface {

	@Autowired
	public UsuarioRepositorioInterface usuarioRep;

	public void setUsuarioRep(UsuarioRepositorioInterface usuarioRep) {
		this.usuarioRep = usuarioRep;
	}

	@Override
	public Usuario encontrarUsername(String username) {
		try {
			Usuario usuario = new Usuario();
			usuario.setUsername(username);
			return usuarioRep.encontrarUsername(usuario);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

}
