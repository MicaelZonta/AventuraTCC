package br.com.projeto.aventura.recurso.impl;

import java.util.ArrayList;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;

import br.com.projeto.aventura.modelo.Role;
import br.com.projeto.aventura.modelo.Usuario;
import br.com.projeto.aventura.recurso.RoleEnum;
import br.com.projeto.aventura.recurso.WebService;
import br.com.projeto.aventura.recurso.WebServiceValidador;
import br.com.projeto.aventura.servico.UsuarioServico;

@RestController
@RequestMapping("/" + UsuarioRecurso.URL_USUARIO)
public class UsuarioRecurso extends WebService {
	public static final String URL_USUARIO = "usuario";
	public static final String URL_CADASTRAR = "cadastrar";
	public static final String URL_EDITAR = "editar";
	public static final String URL_EXCLUIR = "excluir";

	public static String getUrlUsuario() {
		return "/" + URL_USUARIO;
	}

	public static String getUrlCadastrar() {
		return "/" + URL_USUARIO + "/" + URL_CADASTRAR;
	}

	public static String getUrlEditar() {
		return "/" + URL_USUARIO + "/" + URL_EDITAR;
	}

	public static String getUrlExcluir() {
		return "/" + URL_USUARIO + "/" + URL_EXCLUIR;
	}

	public UsuarioRecurso(UsuarioServico usuarioServ) {
		super(usuarioServ);
		WebServiceValidador validadorBasico = new WebServiceValidador();
		validadorBasico.addAutorizacao(RoleEnum.ADMIN, RoleEnum.USER);
		adicionarValidador(URL_EDITAR, validadorBasico);
		adicionarValidador(URL_EXCLUIR, validadorBasico);
	}

	@RequestMapping(method = RequestMethod.POST, value = URL_CADASTRAR)
	public String cadastrarUsuario(@RequestParam(value = "username", defaultValue = "") String username,
			@RequestParam(value = "password", defaultValue = "") String password) {
		Usuario usuario = new Usuario();
		usuario.setUsername(username);
		usuario.setPassword(password);
		usuario.setRoles(new ArrayList<Role>());
		Role role = new Role();
		role.setIdRole((long) 1);
		usuario.getRoles().add(role);

		try {
			usuarioServico.cadastrarUsuario(usuario);
		} catch (Exception e) {
			e.printStackTrace();
			throw new HttpClientErrorException(HttpStatus.BAD_REQUEST);
		}
		return usuario.toString();
	}

	@RequestMapping(method = RequestMethod.PATCH, value = URL_EDITAR)
	public String editarUsuario(@RequestParam(value = "password", defaultValue = "") String password) {
		Usuario usuario = getUsuario(URL_EDITAR);
		usuario.setPassword(password);

		try {
			usuarioServico.editarUsuario(usuario);
		} catch (Exception e) {
			e.printStackTrace();
			throw new HttpClientErrorException(HttpStatus.BAD_REQUEST);
		}
		return usuario.toString();
	}

	@RequestMapping(method = RequestMethod.DELETE, value = URL_EXCLUIR)
	public String excluirUsuario() {
		Usuario usuario = getUsuario(URL_EXCLUIR);
		try {
			usuarioServico.excluirUsuario(usuario);
		} catch (Exception e) {
			e.printStackTrace();
			throw new HttpClientErrorException(HttpStatus.BAD_REQUEST);
		}
		return usuario.toString();
	}

}
