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

import com.ranchsorting.model.Campeonato;
import com.ranchsorting.model.Etapa;
import com.ranchsorting.repository.filter.EtapaFilter;

public class Etapas implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private EntityManager manager;

	public Etapa  guardar(Etapa etapa){
		return manager.merge(etapa);
	}
	
	public Etapa porId(Long id) {
		return manager.find(Etapa.class, id);
	}
	
	public List<Etapa> todasEtapas() {
		try{
			return manager.createQuery("from Etapa", Etapa.class).getResultList();	
		}  catch (NoResultException e) {
			return null;
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<Etapa> filtradas(EtapaFilter filtro){
		
		Session session = manager.unwrap(Session.class);
		Criteria criteria = session.createCriteria(Etapa.class)
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

	//busca etapas do campeonato
	public List<Etapa> etapasDoCampeonato(Campeonato campeonato) {
		return manager.createQuery("from Etapa where campeonato = :campeonato", Etapa.class)
				.setParameter("campeonato", campeonato).getResultList();
	}

}
