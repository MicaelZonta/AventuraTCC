package br.com.projeto.aventura.recurso;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.projeto.aventura.modelo.Usuario;
import br.com.projeto.aventura.negocios.UsuarioNegociosInterface;

public abstract class WebService {

	@Autowired
	public UsuarioNegociosInterface usuarioNeg;
	
	public Usuario getUsuario(HttpServletRequest request) {
		return usuarioNeg.encontrarUsername(request.getRemoteUser());
	}
	
}
