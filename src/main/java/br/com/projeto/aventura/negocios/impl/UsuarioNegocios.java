package br.com.projeto.aventura.negocios.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.projeto.aventura.modelo.Usuario;
import br.com.projeto.aventura.negocios.UsuarioNegociosInterface;
import br.com.projeto.aventura.repositorio.UsuarioRepositorio;
import br.com.projeto.aventura.repositorio.impl.UsuarioRepositorioImpl;

@Service("usuarioService")
public class UsuarioNegocios implements UsuarioNegociosInterface {

	public UsuarioRepositorio usuarioRepositorio;
	
	@Autowired
	public void setUsuarioRep(UsuarioRepositorio usuarioRepositorio) {
		this.usuarioRepositorio = usuarioRepositorio;
	}

	@Override
	public Usuario encontrarUsername(String username) {
		try {
			return usuarioRepositorio.encontrarUsername(username);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

}
