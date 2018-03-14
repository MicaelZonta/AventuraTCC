package br.com.projeto.aventura.servico.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.projeto.aventura.modelo.Role;
import br.com.projeto.aventura.modelo.Usuario;
import br.com.projeto.aventura.repositorio.UsuarioRepositorio;
import br.com.projeto.aventura.servico.LoginServico;

@Service("loginServico")
public class LoginServicoImpl implements LoginServico {

	private UsuarioRepositorio usuarioRepositorio;
	
	public LoginServicoImpl(UsuarioRepositorio usuarioRepositorio) {
		this.usuarioRepositorio = usuarioRepositorio;
	}
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Usuario usuario = null;
		try {
			usuario = usuarioRepositorio.encontrarUsername(username);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (usuario == null) {
			throw new UsernameNotFoundException("Invalid username or password.");
		}
		return new org.springframework.security.core.userdetails.User(usuario.getUsername(), usuario.getPassword(),
				mapRolesToAuthorities(usuario.getRoles()));
	}

	public Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {
		return roles.stream().map(role -> new SimpleGrantedAuthority(role.getNome())).collect(Collectors.toList());
	}

	@Override
	public String verificaCredencial(String username) {
		try {
			Usuario usuario = usuarioRepositorio.encontrarUsername(username);
			
			if(validarUsuario(usuario)) {
				return "O usuário " + username + " é valido!!";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new String("seu usuário não existe ou não tem acesso!!");
	}

	@Override
	public List<RoleEnum> getPermissions() {
		List<RoleEnum> role = new ArrayList<RoleEnum>();
		role.add(RoleEnum.ADMIN);
		role.add(RoleEnum.USER);
		return role;
	}
}
