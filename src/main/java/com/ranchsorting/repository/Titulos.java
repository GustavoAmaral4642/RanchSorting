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
import com.ranchsorting.repository.filter.TituloFilter;

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
	
	
	@SuppressWarnings("unchecked")
	public List<Titulo> filtrados(TituloFilter filtro){
		
		Session session = manager.unwrap(Session.class);
		Criteria criteria = session.createCriteria(Titulo.class)
				.createAlias("competidor", "co")
				.createAlias("campeonato", "ca")
				.createAlias("etapa", "e");
		
		if(filtro.getNumeroTituloDe() != null){
			criteria.add(Restrictions.ge("id", filtro.getNumeroTituloDe()));
		}
		
		if(filtro.getNumeroTituloAte() != null){
			criteria.add(Restrictions.le("id", filtro.getNumeroTituloAte()));
		}
		
		if(StringUtils.isNotBlank(filtro.getCompetidor())){
			criteria.add(Restrictions.ilike("co.nome", filtro.getCompetidor(), MatchMode.ANYWHERE));
		}
		
		if(StringUtils.isNotBlank(filtro.getCampeonato())){
			criteria.add(Restrictions.ilike("ca.nome", filtro.getCampeonato(), MatchMode.ANYWHERE));
		}
		
		if(StringUtils.isNotBlank(filtro.getEtapa())){
			criteria.add(Restrictions.ilike("e.nome", filtro.getEtapa(), MatchMode.ANYWHERE));
		}
		
		if (filtro.getDataTituloInicial() != null) {
			criteria.add(Restrictions.ge("dataTitulo", filtro.getDataTituloInicial()));
		}

		if (filtro.getDataTituloFinal() != null) {
			criteria.add(Restrictions.le("dataTitulo", filtro.getDataTituloFinal()));
		}

		return criteria.addOrder(Order.asc("id")).list();
	}

}
