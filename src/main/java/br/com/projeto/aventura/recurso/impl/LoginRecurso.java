package br.com.projeto.aventura.recurso.impl;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.projeto.aventura.recurso.WebService;
import br.com.projeto.aventura.servico.UsuarioServico;

@RestController
@RequestMapping("/" + LoginRecurso.URL_LOGIN)
public class LoginRecurso extends WebService {

	public static final String URL_LOGIN = "login";
	public static final String URL_CHECK = "check";

	public static String getUrlLogin() {
		return "/" + URL_LOGIN + "/" + URL_LOGIN;
	}

	public static String getUrlCheck() {
		return "/" + URL_LOGIN + "/" + URL_CHECK;
	}

	public LoginRecurso(UsuarioServico usuarioServico) {
		super(usuarioServico);
	}

	@RequestMapping(method = RequestMethod.GET, value = URL_CHECK)
	public String verificaCredencial() {
		return getUsuario().toString();
	}

}
