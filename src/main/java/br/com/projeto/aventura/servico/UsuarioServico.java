package br.com.projeto.aventura.servico;

import br.com.projeto.aventura.modelo.Usuario;

public interface UsuarioServico {

	public Usuario encontrarUsuario(String username) throws Exception;

	public Usuario cadastrarUsuario(Usuario usuario) throws Exception;

	public Usuario editarUsuario(Usuario usuario) throws Exception;

	public Usuario excluirUsuario(Usuario usuario) throws Exception;
}
