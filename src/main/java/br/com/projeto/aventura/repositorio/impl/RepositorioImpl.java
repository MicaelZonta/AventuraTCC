package br.com.projeto.aventura.repositorio.impl;

import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;

import br.com.projeto.aventura.configuracao.HibernateConfig;
import br.com.projeto.aventura.repositorio.Repositorio;

public abstract class RepositorioImpl<T> implements Repositorio<T> {

	private Session session;
	private Class<T> modelo;

	public RepositorioImpl(Class<T> modelo){
		this.modelo = modelo;
	}
	
	@Override
	public T buscaPorId(Long id) {
		openSession();
		T e = getSession().get(modelo, id);
		closeSession();
		return e;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<T> buscaTodos() {
		openSession();
		Query query = getSession().createQuery("select t from " + modelo.getSimpleName() + " t");
		List<T> lista = query.getResultList();
		closeSession();
		return lista;
	}

	@Override
	public T inserir(T object) {
		openSession();
		getSession().save(object);
		getSession().persist(object);
		closeSession();
		return object;
	}

	@Override
	public T atualizar(Long id, T object) {
		openSession();
		getSession().saveOrUpdate(object);
		closeSession();
		return object;
	}

	@Override
	public T excluir(T object) {
		openSession();
		getSession().delete(object);
		closeSession();
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
