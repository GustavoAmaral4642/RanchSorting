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

import com.ranchsorting.model.Divisao;
import com.ranchsorting.repository.filter.DivisaoFilter;

public class Divisoes implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private EntityManager manager;

	public Divisao guardar(Divisao divisao){
		return manager.merge(divisao);
	}
	
	public Divisao porId(Long id) {
		return manager.find(Divisao.class, id);
	}
	
	public List<Divisao> todosDivisoes() {
		return manager.createQuery("from Divisao", Divisao.class).getResultList();
	}
	
	@SuppressWarnings("unchecked")
	public List<Divisao> filtrados(DivisaoFilter filtro){
		
		Session session = manager.unwrap(Session.class);
		Criteria criteria = session.createCriteria(Divisao.class);
		
		if(StringUtils.isNotBlank(filtro.getNome())){
			criteria.add(Restrictions.ilike("nome", filtro.getNome(), MatchMode.ANYWHERE));
		}
				
		return criteria.addOrder(Order.asc("nome")).list();
	}
	
}
