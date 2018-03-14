
package br.com.projeto.aventura.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import br.com.projeto.aventura.negocios.UsuarioNegociosInterface;
import br.com.projeto.aventura.negocios.impl.LoginNegocios;
import ch.qos.logback.core.net.SyslogOutputStream;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	LoginNegocios loginNeg;

	@Override
	public void configure(HttpSecurity httpSecurity) throws Exception {

		System.out.println("Configuring WebSecurityConfigurerAdapter...");
		httpSecurity.csrf().disable().authorizeRequests()
//				.antMatchers(HttpMethod.POST, "/check-my-role")
				.antMatchers(HttpMethod.GET, "/hello").authenticated()
				.antMatchers(HttpMethod.POST, "/login").permitAll().anyRequest().authenticated().and()

				// filtra requisições de login
				.addFilterBefore(new JWTLoginFilter("/login", authenticationManager()),
						UsernamePasswordAuthenticationFilter.class)

				// filtra outras requisições para verificar a presença do JWT no header
				.addFilterBefore(new JWTAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		// Realiza a autenticação
		System.out.println("Building Authentication Manager...");
		auth.authenticationProvider(getAuthenticationProvider());
	}

	@Bean
	public BCryptPasswordEncoder getPasswordEncoder() {
		// Encarregado de encodar password
		System.out.println("Getting Password Encoder...");
		return new BCryptPasswordEncoder();
	}

	@Bean
	public DaoAuthenticationProvider getAuthenticationProvider() {
		// Encarregado de criar um autenticador
		System.out.println("Getting Authentication Provider...");
		DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
		auth.setUserDetailsService(loginNeg);
		auth.setPasswordEncoder(getPasswordEncoder());
		return auth;
	}

}