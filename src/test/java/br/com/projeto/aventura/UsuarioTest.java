package br.com.projeto.aventura;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;

import java.util.ArrayList;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import br.com.projeto.aventura.modelo.Usuario;

public class UsuarioTest {

//	@Test
//	public void test() {
//		fail("Not yet implemented");
//	}

	@Rule
	public ExpectedException thrown = ExpectedException.none();
	
	
	@Test
	public void InstanciarUsuarioVazio() {
		Usuario usuario = new Usuario();
		usuario.setIdUsuario(1L);
		assertNotNull(usuario);
		assertEquals(new Long(1L), usuario.getIdUsuario());
		
		
	}
	
	@Test
	public void InstanciarUsuario() {
		Usuario usuario = new Usuario("username", "password");
		assertNotNull(usuario);
		assertEquals("username", usuario.getUsername());
		assertEquals("password", usuario.getPassword());
	}
	
	@Test
	public void InstanciarUsuario2() {
		Usuario usuario = new Usuario("username", "password", 50L);
		assertNotNull(usuario);
		assertEquals("username", usuario.getUsername());
		assertEquals("password", usuario.getPassword());
		assertEquals(new Long(50L), usuario.getFavor());
	}
	
//	@Test()
//	public void RetornaExceptiontToString() {
//		Usuario usuario = null; 
//		usuario.toString();
//		fail();
//	}
	
	@Test
	public void RetornaStringJson() {
		Usuario usuario = new Usuario("username", "password", 50L);
		usuario.setIdUsuario(1L);
		usuario.setRoles(new ArrayList<>());
		String json = usuario.toString();
		assertNotNull(json);
	}

}
