package br.com.projeto.aventura.recurso;

import java.util.HashSet;
import java.util.Set;

import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpClientErrorException;

import br.com.projeto.aventura.modelo.Role;
import br.com.projeto.aventura.modelo.Usuario;

public class WebServiceValidador {

	private Set<RoleEnum> listaAutorizados = new HashSet<RoleEnum>();

	public void addAutorizacao(RoleEnum... roleList) {

		for (RoleEnum role : roleList) {
			listaAutorizados.add(role);
		}

	}

	public void removeAutorizacao(RoleEnum role) {
		listaAutorizados.remove(role);
	}

	public boolean validarAutorizacao(Usuario usuario) throws HttpClientErrorException {

		System.out.println("Validando " + usuario.toString());
		boolean autorizado = false;

		for (Role usuarioRole : usuario.getRoles()) {

			for (RoleEnum roleAutorizada : listaAutorizados) {
				autorizado = usuarioRole.getNome().equals(RoleEnum.valueOf(roleAutorizada));
				if (autorizado)
					break;
			}

			if (autorizado)
				break;
		}

		System.out.println("Authorized ? " + autorizado);

		if (!autorizado) {
			throw new HttpClientErrorException(HttpStatus.UNAUTHORIZED);
		}
		return autorizado;
	}

}
