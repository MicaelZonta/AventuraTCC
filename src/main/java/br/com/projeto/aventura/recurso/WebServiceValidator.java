package br.com.projeto.aventura.recurso;

import java.util.List;

import javax.management.relation.RoleNotFoundException;
import javax.servlet.http.HttpServletRequest;

import br.com.projeto.aventura.modelo.Role;
import br.com.projeto.aventura.modelo.Usuario;
import br.com.projeto.aventura.negocios.UsuarioNegociosInterface;
import br.com.projeto.aventura.negocios.impl.UsuarioNegocios;
import br.com.projeto.aventura.repositorio.impl.UsuarioRepositorioImpl;

public interface WebServiceValidator {

	public List<RoleEnum> getPermissions();

	public default boolean validarUsuario(Usuario usuario) throws RoleNotFoundException {

		boolean autorizado = false;

		for (Role usuarioRole : usuario.getRoles()) {

			// Verifica Token
			for (RoleEnum role : getPermissions()) {
				autorizado = usuarioRole.getNome().equals(RoleEnum.valueOf(role));
				if (autorizado) {
					break;
				}
			}

		}

		System.out.println("Authorized ? " + autorizado);
		
		if(!autorizado) {
			throw new RoleNotFoundException();
		}
		return autorizado;
	}

	enum RoleEnum {
		ADMIN, USER;

		public static String valueOf(RoleEnum role) {
			String value = "";

			if (role == RoleEnum.ADMIN) {
				value = "ROLE_ADMIN";
			}
			if (role == RoleEnum.USER) {
				value = "ROLE_USER";
			}

			return value;
		}
	}

}
