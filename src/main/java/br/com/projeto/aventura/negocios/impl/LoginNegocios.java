package br.com.projeto.aventura.negocios.impl;

import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.projeto.aventura.modelo.Role;
import br.com.projeto.aventura.modelo.Usuario;
import br.com.projeto.aventura.negocios.UsuarioNegociosInterface;

@Service("sLogin")
public class LoginNegocios implements UserDetailsService {

	@Autowired
	UsuarioNegociosInterface usuarioNeg;

	public void setUsuarioNeg(UsuarioNegociosInterface usuarioNeg) {
		this.usuarioNeg = usuarioNeg;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Usuario usuario = usuarioNeg.encontrarUsername(username);
		if (usuario == null) {
			throw new UsernameNotFoundException("Invalid username or password.");
		}
		return new org.springframework.security.core.userdetails.User(usuario.getUsername(), usuario.getPassword(),
				mapRolesToAuthorities(usuario.getRoles()));
	}

	public Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {
		return roles.stream().map(role -> new SimpleGrantedAuthority(role.getNome())).collect(Collectors.toList());
	}

}
