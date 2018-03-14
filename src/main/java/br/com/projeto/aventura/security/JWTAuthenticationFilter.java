package br.com.projeto.aventura.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

public class JWTAuthenticationFilter extends GenericFilterBean {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain)
			throws IOException, ServletException {
		// Envia Auth para o WebService
		System.out.println("Filtering Request");

		Authentication authentication = TokenAuthenticationService.getAuthentication((HttpServletRequest) request);

		if(authentication != null) {
			System.out.println("Authenticated ? " + authentication.isAuthenticated());
			SecurityContextHolder.getContext().setAuthentication(authentication);
			filterChain.doFilter(request, response);
		} else {
			filterChain.doFilter(request, response);
		}
		

	}

}