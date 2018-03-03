
package br.com.projeto.aventura.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Override
	public void configure(HttpSecurity httpSecurity) throws Exception {
		httpSecurity.csrf().disable().authorizeRequests()
			.antMatchers(HttpMethod.POST, "/check-my-role").permitAll()
			.antMatchers(HttpMethod.POST, "/only-admin").hasRole("ADMIN")
			.antMatchers(HttpMethod.POST, "/login").permitAll()
			.anyRequest().authenticated()
			.and()
			
			// filtra requisições de login
			.addFilterBefore(new JWTLoginFilter("/login", authenticationManager()),
	                UsernamePasswordAuthenticationFilter.class)
			
			// filtra outras requisições para verificar a presença do JWT no header
			.addFilterBefore(new JWTAuthenticationFilter(),
	                UsernamePasswordAuthenticationFilter.class);
		
		System.out.println(this.getClass().getName());
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		//TO-DO IMPLEMENTAR HIBERNATE

		auth.inMemoryAuthentication()
			.withUser("admin").password("{noop}password").roles("ADMIN")
			.and()
			.withUser("user").password("{noop}password").roles("USER");
	}

	
	
	@Bean
	public AuthenticationManager authenticationManagerBean() throws Exception {
		//Parece inutiil mas resolve problemas de compatibilidade com Springs mais atuais
	    return super.authenticationManagerBean();
	}
}