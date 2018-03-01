package br.com.projeto.aventura.repositorio.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Repository;

import br.com.projeto.aventura.modelo.Usuario;
import br.com.projeto.aventura.repositorio.UsuarioRepositorioInterface;
import br.com.projeto.aventura.repositorio.Repositorio;

@Repository("rUsuario")
public class UsuarioRep extends RepositorioImpl<Usuario> implements UsuarioRepositorioInterface {


	@Override
	public Usuario findByName(Usuario conta) {
		if (conta.getUsername().equals("admin")) {
			conta.setPassword("password");
			conta.setRoles(new ArrayList<GrantedAuthority>());
			conta.getRoles().add(new SimpleGrantedAuthority("ROLE_ADMIN"));
			return conta;
		} else if(conta.getUsername().equals("user")){
			conta.setPassword("password");
			conta.setRoles(new ArrayList<GrantedAuthority>());
			conta.getRoles().add(new SimpleGrantedAuthority("ROLE_USER"));
			return conta;
		}
		else {
			return null;
		}
	}


}
