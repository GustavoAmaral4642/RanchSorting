package com.ranchsorting.repository;

import java.io.Serializable;  
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.ranchsorting.model.OrdemEntrada;
import com.ranchsorting.repository.filter.OrdemEntradaFilter;


public class OrdensEntradas implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private EntityManager manager;

	public OrdemEntrada guardar(OrdemEntrada ordem) {
		return manager.merge(ordem);
	}

	public OrdemEntrada porId(Long id) {
		return manager.find(OrdemEntrada.class, id);
	}

	
	@SuppressWarnings("unchecked")
	public List<OrdemEntrada> filtradas(OrdemEntradaFilter filtro) {

		Session session = manager.unwrap(Session.class);
		Criteria criteria = session.createCriteria(OrdemEntrada.class);

		return criteria.addOrder(Order.asc("id")).list();
	}
}
