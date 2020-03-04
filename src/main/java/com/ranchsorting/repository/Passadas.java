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

import com.ranchsorting.model.Passada;
import com.ranchsorting.repository.filter.PassadaFilter;

public class Passadas implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private EntityManager manager;

	public Passada guardar(Passada passada) {
		return manager.merge(passada);
	}

	public Passada porId(Long id) {
		return manager.find(Passada.class, id);
	}

	public List<Passada> porIdComFichas(Long id) {
		return manager.createQuery("from Passada", Passada.class).getResultList();
	}
	
	public List<Passada> todasPassadas() {

		return manager.createQuery("from Passada", Passada.class).getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<Passada> filtradas(PassadaFilter filtro, Passada passada) {

		Session session = manager.unwrap(Session.class);
		Criteria criteria = session.createCriteria(Passada.class)
				.createAlias("ordemEntrada", "o");

		if(passada != null){
			criteria.add(Restrictions.eq("id",passada.getId()));
		}
		
		if (filtro.getOrdemEntrada() != null) {
			criteria.add(Restrictions.eq("o.id",filtro.getOrdemEntrada().getId()));
		}

		return criteria.addOrder(Order.asc("id")).list();
	}
	
}
