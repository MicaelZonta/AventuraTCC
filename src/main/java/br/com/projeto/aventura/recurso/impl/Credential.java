package br.com.projeto.aventura.recurso.impl;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.projeto.aventura.modelo.Usuario;
import br.com.projeto.aventura.recurso.WebService;
import br.com.projeto.aventura.recurso.WebServiceValidator;
import br.com.projeto.aventura.recurso.WebServiceValidator.RoleEnum;

@RestController
public class Credential extends WebService implements WebServiceValidator {

	@RequestMapping("/check-my-credential")
	public String checkMyCredential(HttpServletRequest request) {

		try {
			System.out.println("Check-My-Credential Called");
			Usuario usuario = getUsuario(request);
			validarUsuario(usuario);
			return usuario.toString();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<RoleEnum> getPermissions() {
		List<RoleEnum> role = new ArrayList();
		role.add(RoleEnum.ADMIN);
		role.add(RoleEnum.USER);
		return role;
	}
}
