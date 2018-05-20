package br.com.projeto.aventura;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.SecurityAutoConfiguration;

import br.com.projeto.aventura.modelo.Missao;
import br.com.projeto.aventura.modelo.MissaoTarefa;

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
        
        Missao missao = new Missao();
        missao.setDataCriacao(new Date());
        missao.setDescricao("Matar Goku");
        missao.setIdPessoa(1l);
        missao.setLatitude(50d);
        missao.setListaDificuldades(null);
        
        List<MissaoTarefa> lista = new ArrayList<MissaoTarefa>();
        MissaoTarefa tarefa = new MissaoTarefa();
        tarefa.setDescricao("Vá até Goku");
        tarefa.setNome("Encontre");
        
        MissaoTarefa tarefa2 = new MissaoTarefa();
        tarefa2.setDescricao("Mate Goku");
        tarefa2.setNome("Mate");
        
        lista.add(tarefa);
        lista.add(tarefa2);
        
        missao.setListaTarefas(lista);
        
        missao.setListaProgressos(null);
        
        missao.setLongitude(50d);
        
        missao.setMaxAventureiros(2);
        
        missao.setNome("Missão Impossível");
        
        System.out.println(missao);
        
    }
}
