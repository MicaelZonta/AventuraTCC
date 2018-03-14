package br.com.projeto.aventura.recurso;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.projeto.aventura.servico.LoginServico;

@RestController
@RequestMapping("/login")
public class LoginRecurso {
	
	private LoginServico loginServico;
	
	@Autowired
	public LoginRecurso(LoginServico loginServico) {
		this.loginServico = loginServico;
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/{username}")
	public String verificaCredencial(@PathVariable String username) {
		return loginServico.verificaCredencial(username);
	}
	
}
