package br.com.projeto.aventura;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import br.com.projeto.aventura.modelo.Usuario;

public class UsuarioTest {

	// @Test
	// public void test() {
	// fail("Not yet implemented");
	// }

	@Rule
	public ExpectedException thrown = ExpectedException.none();

	@Test
	public void InstanciarUsuarioVazio() {
		Usuario usuario = new Usuario();
		usuario.setIdUsuario(1L);
		assertNotNull(usuario);
		assertEquals(1l,(long) usuario.getIdUsuario());

	}

	@Test
	public void InstanciarUsuario() {
		Usuario usuario = new Usuario();
		usuario.setUsername("username");
		usuario.setPassword("password");
		assertNotNull(usuario);
		assertEquals("username", usuario.getUsername());
		assertEquals("password", usuario.getPassword());

	}

}
