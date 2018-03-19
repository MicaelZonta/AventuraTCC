package br.com.projeto.aventura.servico.impl;

import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import br.com.projeto.aventura.modelo.Role;
import br.com.projeto.aventura.modelo.Usuario;
import br.com.projeto.aventura.servico.LoginServico;
import br.com.projeto.aventura.servico.UsuarioServico;

@Service("loginServico")
public class LoginServicoImpl implements LoginServico {

	private UsuarioServico usuarioServico;

	@Autowired
	public LoginServicoImpl(UsuarioServico usuarioServico) {
		this.usuarioServico = usuarioServico;
	}

	@Override
	public UserDetails loadUserByUsername(String username) {
		Usuario usuario;
		try {
			usuario = usuarioServico.encontrarUsuario(username);
		} catch (Exception e) {
			e.printStackTrace();
			throw new HttpClientErrorException(HttpStatus.UNAUTHORIZED);
		}

		return new org.springframework.security.core.userdetails.User(usuario.getUsername(), usuario.getPassword(),
				mapRolesToAuthorities(usuario.getRoles()));
	}

	public Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {
		return roles.stream().map(role -> new SimpleGrantedAuthority(role.getNome())).collect(Collectors.toList());
	}

}
