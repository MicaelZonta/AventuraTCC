package br.com.projeto.aventura.repositorio.impl;

import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;

import br.com.projeto.aventura.configuracao.HibernateConfig;
import br.com.projeto.aventura.repositorio.Repositorio;

public abstract class RepositorioImpl<T> implements Repositorio<T> {

	private Session session;
	private Class<T> modelo;

	@Override
	public T buscaPorId(Long id) {
		T e = getSession().get(modelo, id);
		return e;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<T> buscaTodos() {
		Query query = getSession().createQuery("select t from " + modelo.getSimpleName() + " t");
		return query.getResultList();
	}

	@Override
	public T inserir(T object) {
		getSession().save(object);
		getSession().persist(object);
		return object;
	}

	@Override
	public T atualizar(Long id, T object) {
		getSession().beginTransaction();
		getSession().saveOrUpdate(object);
		getSession().getTransaction().commit();
		return object;
	}

	@Override
	public T excluir(T object) {
		getSession().beginTransaction();
		getSession().delete(object);
		getSession().getTransaction().commit();
		return object;
	}

	protected void openSession() {
		session = HibernateConfig.getSession();
		session.beginTransaction();
	}
	
	protected Session getSession() {
		return session;
	}
	protected void closeSession() {
		session.getTransaction().commit();
		session.close();
	}

}
