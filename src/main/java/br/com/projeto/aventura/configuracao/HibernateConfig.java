package br.com.projeto.aventura.configuracao;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateConfig {
	@SuppressWarnings("unused")
	private static SessionFactory factory = buildSessionFactory();


	public static SessionFactory buildSessionFactory() {

		try {
			Configuration cfg = new Configuration();
			cfg.configure();
			SessionFactory sf = cfg.buildSessionFactory();
			return sf;

		} catch (HibernateException e) {
			throw e;
		}
	}
}
