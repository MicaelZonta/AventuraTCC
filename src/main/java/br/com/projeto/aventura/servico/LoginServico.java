package br.com.projeto.aventura.servico;

import java.util.List;

import javax.management.relation.RoleNotFoundException;

import org.springframework.security.core.userdetails.UserDetailsService;

import br.com.projeto.aventura.modelo.Role;
import br.com.projeto.aventura.modelo.Usuario;

public interface LoginServico extends UserDetailsService {

	public List<RoleEnum> getPermissions();

	String verificaCredencial(String username);
	
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
