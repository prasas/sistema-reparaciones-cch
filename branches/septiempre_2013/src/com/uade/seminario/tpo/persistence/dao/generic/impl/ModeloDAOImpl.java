package com.uade.seminario.tpo.persistence.dao.generic.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.uade.seminario.tpo.model.Modelo;
import com.uade.seminario.tpo.persistence.hbt.HibernateUtil;

public class ModeloDAOImpl extends GenericDAOImpl<Modelo> {
	private static ModeloDAOImpl instancia = null;
	private static SessionFactory sf = null;
	public static ModeloDAOImpl getInstancia(){
		if(instancia == null){
			sf = HibernateUtil.getSessionFactory();
			instancia = new ModeloDAOImpl();
		} 
		return instancia;
	}

	public Modelo findByNroSerie(int nroModelo) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		String hql = "from Modelo m where m.nroModelo = :nroModelo";
		Query query = session.createQuery(hql);
		query.setParameter("nroModelo", nroModelo);
		Modelo retorno =  (Modelo)query.uniqueResult();
		session.close();
		return retorno;
	}
	public List<Modelo> todosLosModelos() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		String hql = "from Modelo";
		Query query = session.createQuery(hql);
		List<Modelo> retorno =  query.list();
		session.close();
		return retorno;
	}	
	
}
