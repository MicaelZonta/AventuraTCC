package br.com.projeto.aventura.configuracao;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import br.com.projeto.aventura.modelo.Role;
import br.com.projeto.aventura.modelo.Usuario;

public class HibernateConfig {
	@SuppressWarnings("unused")
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
			cfg.configure("hibernate.cfg.xml");
			SessionFactory sf = cfg.buildSessionFactory();
			return sf;

		} catch (HibernateException e) {
			throw e;
		}
	}
}
