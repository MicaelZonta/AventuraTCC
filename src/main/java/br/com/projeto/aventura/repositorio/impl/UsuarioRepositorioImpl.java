package br.com.projeto.aventura.repositorio.impl;

import javax.persistence.Query;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import br.com.projeto.aventura.modelo.Usuario;
import br.com.projeto.aventura.repositorio.UsuarioRepositorio;

@Repository("usuarioRepositorio")
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class UsuarioRepositorioImpl extends RepositorioImpl<Usuario> implements UsuarioRepositorio {

	public UsuarioRepositorioImpl() {
		super(Usuario.class);
	}

	@Override
	public Usuario encontrarUsername(String username) throws Exception {
		openSession();
		Query query = getSession().createQuery("SELECT u FROM Usuario u WHERE username = :username AND ativo = TRUE",
				Usuario.class);
		query.setParameter("username", username);
		Usuario usuario = (Usuario) query.getSingleResult();
		closeSession();
		return usuario;
	}

	public Usuario adicionarUsuario(Usuario usuario) throws Exception {
		usuario = inserir(usuario);
		return usuario;
	}

	public Usuario editarUsuario(Usuario usuario) throws Exception {
		usuario = atualizar(usuario.getIdUsuario(), usuario);
		return usuario;
	}

}
