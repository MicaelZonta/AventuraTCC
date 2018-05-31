package br.com.projeto.aventura.configuracao;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import br.com.projeto.aventura.modelo.Avaliacao;
import br.com.projeto.aventura.modelo.AvaliacaoChave;
import br.com.projeto.aventura.modelo.Aventureiro;
import br.com.projeto.aventura.modelo.Celular;
import br.com.projeto.aventura.modelo.DDD;
import br.com.projeto.aventura.modelo.Habilidade;
import br.com.projeto.aventura.modelo.Missao;
import br.com.projeto.aventura.modelo.MissaoDificuldade;
import br.com.projeto.aventura.modelo.MissaoProgresso;
import br.com.projeto.aventura.modelo.MissaoTarefa;
import br.com.projeto.aventura.modelo.PessoaFisica;
import br.com.projeto.aventura.modelo.Posicao;
import br.com.projeto.aventura.modelo.Role;
import br.com.projeto.aventura.modelo.Situacao;
import br.com.projeto.aventura.modelo.TarefaProgresso;
import br.com.projeto.aventura.modelo.TarefaProgressoChave;
import br.com.projeto.aventura.modelo.UnidadeHabilidade;
import br.com.projeto.aventura.modelo.UnidadeHabilidadeChave;
import br.com.projeto.aventura.modelo.Usuario;
import br.com.projeto.aventura.modelo.abstrato.Pessoa;
import br.com.projeto.aventura.modelo.abstrato.Unidade;

public class HibernateConfig {
	private static SessionFactory factory = buildSessionFactory();
	private static Session session = null;

	public static Session getSession() {
		System.out.println("Opening Session...");
		session = factory.openSession();
		return session;
	}

	public static SessionFactory buildSessionFactory() {

		try {
			Configuration cfg = new Configuration();
			cfg.addPackage("br.com.projeto.aventura.modelo");
			cfg.addAnnotatedClass(Usuario.class);
			cfg.addAnnotatedClass(Role.class);
			cfg.addAnnotatedClass(Pessoa.class);
			cfg.addAnnotatedClass(Celular.class);
			cfg.addAnnotatedClass(DDD.class);
			cfg.addAnnotatedClass(PessoaFisica.class);
			cfg.addAnnotatedClass(Aventureiro.class);
			cfg.addAnnotatedClass(Unidade.class);
			cfg.addAnnotatedClass(Posicao.class);
			cfg.addAnnotatedClass(Missao.class);
			cfg.addAnnotatedClass(MissaoProgresso.class);
			cfg.addAnnotatedClass(MissaoDificuldade.class);
			cfg.addAnnotatedClass(Avaliacao.class);
			cfg.addAnnotatedClass(AvaliacaoChave.class);
			cfg.addAnnotatedClass(Habilidade.class);
			cfg.addAnnotatedClass(UnidadeHabilidade.class);
			cfg.addAnnotatedClass(UnidadeHabilidadeChave.class);
			cfg.addAnnotatedClass(MissaoTarefa.class);
			cfg.addAnnotatedClass(Situacao.class);
			cfg.addAnnotatedClass(TarefaProgresso.class);
			cfg.addAnnotatedClass(TarefaProgressoChave.class);
			cfg.configure("hibernate.cfg.xml");
			SessionFactory sf = cfg.buildSessionFactory();
			return sf;

		} catch (HibernateException e) {
			throw e;
		}
	}
}
