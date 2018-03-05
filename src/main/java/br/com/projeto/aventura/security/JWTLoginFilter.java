package br.com.projeto.aventura.security;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.projeto.aventura.modelo.Usuario;
import br.com.projeto.aventura.repositorio.impl.UsuarioRepositorio;

public class JWTLoginFilter extends AbstractAuthenticationProcessingFilter {

	protected JWTLoginFilter(String url, AuthenticationManager authManager) {
		super(new AntPathRequestMatcher(url));
		setAuthenticationManager(authManager);
		System.out.println("Setting AuthenticationManager");
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException, IOException, ServletException {

		System.out.println("========== Login Attempt ===============");

		Usuario usuario = new ObjectMapper().readValue(request.getInputStream(), Usuario.class);


		System.out.println(usuario);
		
		
		Authentication auth = getAuthenticationManager().authenticate(new UsernamePasswordAuthenticationToken(
				usuario.getUsername(), usuario.getPassword(), new ArrayList<GrantedAuthority>()));

		System.out.println("Login Successful ? " + auth.isAuthenticated());
		return auth;
	}

	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response,
			FilterChain filterChain, Authentication auth) throws IOException, ServletException {

		System.out.println("Succesful Login!");

		TokenAuthenticationService.addAuthentication(response, auth.getName());
	}

}