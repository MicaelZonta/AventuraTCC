package br.com.projeto.aventura.repositorio.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import br.com.projeto.aventura.modelo.Role;
import br.com.projeto.aventura.modelo.Usuario;
import br.com.projeto.aventura.repositorio.UsuarioRepositorioInterface;
import br.com.projeto.aventura.repositorio.Repositorio;

@Repository("rUsuario")
public class UsuarioRepositorio extends RepositorioImpl<Usuario> implements UsuarioRepositorioInterface {

	@Override
	public Usuario encontrarUsername(Usuario usuario) {

		try {
			openSession();
			Query query = getSession().createQuery("SELECT u FROM Usuario u WHERE username = :sUsername ", Usuario.class);
			query.setParameter("sUsername", usuario.getUsername());
			usuario = (Usuario) query.getSingleResult();
			closeSession();
			return usuario;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

}
