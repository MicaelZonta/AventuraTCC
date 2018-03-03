package br.com.projeto.aventura.negocios.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.projeto.aventura.modelo.Usuario;
import br.com.projeto.aventura.negocios.UsuarioNegociosInterface;
import br.com.projeto.aventura.repositorio.impl.UsuarioRep;


@Service("sConta")
public class UsuarioNeg implements UsuarioNegociosInterface {

	@Autowired
	private UsuarioRep rConta;

	@Override
	public Usuario findByUsername(String username) {
		Usuario conta = new Usuario();
		conta.setUsername(username);
		return rConta.findByName(conta);
	}

}
