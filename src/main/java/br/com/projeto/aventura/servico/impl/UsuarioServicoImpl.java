package br.com.projeto.aventura.servico.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import br.com.projeto.aventura.Util.UtilString;
import br.com.projeto.aventura.modelo.Usuario;
import br.com.projeto.aventura.repositorio.UsuarioRepositorio;
import br.com.projeto.aventura.servico.UsuarioServico;

@Service("usuarioServico")
public class UsuarioServicoImpl implements UsuarioServico {

	private UsuarioRepositorio usuarioRepositorio;

	@Autowired
	public UsuarioServicoImpl(UsuarioRepositorio usuarioRepositorio) {
		this.usuarioRepositorio = usuarioRepositorio;
	}

	@Override
	public Usuario encontrarUsuario(String username) {
		Usuario usuario = null;
		try {
			usuario = usuarioRepositorio.encontrarUsername(username);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (usuario == null) {
			throw new HttpClientErrorException(HttpStatus.UNAUTHORIZED);
		}

		return usuario;
	}

	@Override
	public Usuario cadastrarUsuario(Usuario usuario) throws Exception {
		usuario.setFavor(1l);
		usuario.setAtivo(true);
		if (validar(usuario))
			usuario = usuarioRepositorio.adicionarUsuario(usuario);
		else
			throw new HttpClientErrorException(HttpStatus.BAD_REQUEST);
		return usuario;
	}

	@Override
	public Usuario editarUsuario(Usuario usuario) throws Exception {
		if (validar(usuario))
			usuario = usuarioRepositorio.editarUsuario(usuario);
		else
			throw new HttpClientErrorException(HttpStatus.BAD_REQUEST);
		return usuario;
	}

	@Override
	public Usuario excluirUsuario(Usuario usuario) throws Exception {
		usuario.setAtivo(false);
		if (validar(usuario))
			usuario = editarUsuario(usuario);
		else
			throw new HttpClientErrorException(HttpStatus.BAD_REQUEST);
		return usuario;
	}

	private boolean validar(Usuario usuario) {
		if (UtilString.isEmpty(usuario.getUsername())) {
			return false;
		}
		if (UtilString.isEmpty(usuario.getPassword())) {
			return false;
		}
		if (usuario.getRoles().size() <= 0) {
			return false;
		}
		return true;
	}

}
