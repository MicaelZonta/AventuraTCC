package br.com.projeto.aventura;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.SecurityAutoConfiguration;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.security.core.userdetails.UserDetailsService;

import br.com.projeto.aventura.negocios.impl.LoginNegocios;

@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
public class App 
{
    public static void main( String[] args )
    {
        SpringApplication.run(App.class, args);           
    }
}
