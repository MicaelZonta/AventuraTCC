package br.com.projeto.aventura.recurso;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpClientErrorException;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.projeto.aventura.modelo.Usuario;
import br.com.projeto.aventura.servico.UsuarioServico;

public abstract class WebService {

	@Autowired
	protected UsuarioServico usuarioServico;

	private HashMap<String, WebServiceValidador> listaValidadores = new HashMap<String, WebServiceValidador>();

	public WebService(UsuarioServico usuarioServico) {
		this.usuarioServico = usuarioServico;
	}

	public void adicionarValidador(String URL, WebServiceValidador validador) {
		listaValidadores.put(URL, validador);
	}

	public boolean validarAcesso(String URL, Usuario usuario) {
		return listaValidadores.get(URL).validarAutorizacao(usuario);
	}

	public Usuario getUsuario(HttpServletRequest request) {
		return getUsuario(request, null);
	}

	public Usuario getUsuario(HttpServletRequest request, String URL) {
		Usuario usuario = null;
		try {
			usuario = usuarioServico.encontrarUsuario(request.getRemoteUser());
		} catch (Exception e) {
			e.printStackTrace();
			throw new HttpClientErrorException(HttpStatus.UNAUTHORIZED);
		}

		if (URL != null && !URL.isEmpty()) {
			validarAcesso(URL, usuario);
		}

		return usuario;
	}

	public <T> T lerJson(HttpServletRequest request, Class<T> type) {
		T json = null;
		try {
			json = new ObjectMapper().readValue(request.getInputStream(), type);
		} catch (JsonParseException e) {
			e.printStackTrace();
			throw new HttpClientErrorException(HttpStatus.UNPROCESSABLE_ENTITY);
		} catch (JsonMappingException e) {
			e.printStackTrace();
			throw new HttpClientErrorException(HttpStatus.UNPROCESSABLE_ENTITY);
		} catch (IOException e) {
			e.printStackTrace();
			throw new HttpClientErrorException(HttpStatus.UNPROCESSABLE_ENTITY);
		}

		return json;
	}
}
