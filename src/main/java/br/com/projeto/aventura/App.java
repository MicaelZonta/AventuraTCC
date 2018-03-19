package br.com.projeto.aventura;

import java.util.Date;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.SecurityAutoConfiguration;

import br.com.projeto.aventura.modelo.Celular;
import br.com.projeto.aventura.modelo.DDD;
import br.com.projeto.aventura.modelo.PessoaFisica;

@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
public class App {
	public static void main( String[] args )
    {
        SpringApplication.run(App.class, args); 
        /*
        Celular celular = new Celular();
        celular.setNumero("123456789");
        DDD ddd = new DDD();
        ddd.setDDD("011");
        ddd.setIdDDD(1);
        celular.setDDD(ddd);
        PessoaFisica pf = new PessoaFisica("Micael", "Pinheiro", new Date(), null, "12345678911", "mock@outlook.com.br",celular);
        
        System.out.println(pf.toString());*/
    }
}
