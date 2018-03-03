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
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.projeto.aventura.modelo.Usuario;

public class JWTLoginFilter extends AbstractAuthenticationProcessingFilter {

	protected JWTLoginFilter(String url, AuthenticationManager authManager) {
		super(new AntPathRequestMatcher(url));
		System.out.println(this.getClass().getName());
		setAuthenticationManager(authManager);
		System.out.println("Set AuthenticationManager");
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException, IOException, ServletException {

		System.out.println("========== Login Attempt ===============");
		
		Usuario conta = new ObjectMapper().readValue(request.getInputStream(),Usuario.class);
		System.out.println(conta);
		return getAuthenticationManager().authenticate(
				new UsernamePasswordAuthenticationToken(conta.getUsername(), conta.getPassword(), new ArrayList<GrantedAuthority>()));
	}

	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response,
			FilterChain filterChain, Authentication auth) throws IOException, ServletException {
		
		System.out.println("Succesful Login!");
		
			
		TokenAuthenticationService.addAuthentication(response, auth.getName());
	}

}