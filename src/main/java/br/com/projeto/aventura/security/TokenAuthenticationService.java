package br.com.projeto.aventura.security;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import br.com.projeto.aventura.modelo.Usuario;
import br.com.projeto.aventura.negocios.impl.UsuarioNeg;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class TokenAuthenticationService {

	// EXPIRATION_TIME = 10 dias
	static final long EXPIRATION_TIME = 860_000_000;
	static final String SECRET = "BurnInHell";
	static final String TOKEN_PREFIX = "Bearer";
	static final String HEADER_STRING = "Authorization";

	static void addAuthentication(HttpServletResponse response, String username) {
		String JWT = Jwts.builder().setSubject(username)
				.setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
				.signWith(SignatureAlgorithm.HS512, SECRET).compact();

		response.addHeader(HEADER_STRING, TOKEN_PREFIX + " " + JWT);
	}

	static Authentication getAuthentication(HttpServletRequest request) {
		System.out.println("=== Getting Authentication Token ===");

		String token = request.getHeader(HEADER_STRING);

		if (token != null) {

			System.out.println("Token Found!");
			String user = Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token.replace(TOKEN_PREFIX, "")).getBody()
					.getSubject();

			if (user != null) {

				System.out.println("Username written!");
				
				//Injeção de Dependencia
				UsuarioNeg nUsuario = new UsuarioNeg();
				Usuario conta = nUsuario.findByUsername(user);

				UsernamePasswordAuthenticationToken username = new UsernamePasswordAuthenticationToken(
						conta.getUsername(), null, conta.getRoles());

				System.out.println(conta);

				return username;
			}
		}
		return null;
	}

}