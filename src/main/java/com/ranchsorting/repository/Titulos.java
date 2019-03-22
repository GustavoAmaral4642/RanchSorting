package com.ranchsorting.repository;

import java.io.Serializable;     
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.ranchsorting.model.Titulo;

public class Titulos implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private EntityManager manager;

	public Titulo guardar(Titulo contasReceber){
		return manager.merge(contasReceber);
	}
		
	public Titulo porId(Long id) {
		return manager.find(Titulo.class, id);
	}
	
	public List<Titulo> todosTitulos() {
		try{
			return manager.createQuery("from Titulo", Titulo.class).getResultList();	
		}  catch (NoResultException e) {
			return null;
		}
	}
	
	/*
	@SuppressWarnings("unchecked")
	public List<ContasReceber> filtradas(ContasReceberFilter filtro){
		
		Session session = manager.unwrap(Session.class);
		Criteria criteria = session.createCriteria(ContasReceber.class)
				.createAlias("campeonato", "c");
		
		if(StringUtils.isNotBlank(filtro.getNome())){
			criteria.add(Restrictions.ilike("nome", filtro.getNome(), MatchMode.ANYWHERE));
		}
		
		if(StringUtils.isNotBlank(filtro.getCampeonato())){
			criteria.add(Restrictions.ilike("c.nome", filtro.getCampeonato(), MatchMode.ANYWHERE));
		}
		
		if (filtro.getDataEventoInicial() != null) {
			criteria.add(Restrictions.ge("dataEvento", filtro.getDataEventoInicial()));
		}

		if (filtro.getDataEventoFinal() != null) {
			criteria.add(Restrictions.le("dataEvento", filtro.getDataEventoFinal()));
		}
				
		return criteria.addOrder(Order.asc("nome")).list();
	}
*/
}
