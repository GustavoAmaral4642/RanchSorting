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

	public List<Passada> todasPassadas() {

		return manager.createQuery("from Passada", Passada.class).getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<Passada> filtradas(PassadaFilter filtro) {

		Session session = manager.unwrap(Session.class);
		Criteria criteria = session.createCriteria(Passada.class)
				.createAlias("campeonato", "ca")
				.createAlias("etapa", "e")
				.createAlias("divisao", "d");

		if (StringUtils.isNotBlank(filtro.getCampeonato())) {
			criteria.add(Restrictions.ilike("ca.nome", filtro.getCampeonato(), MatchMode.ANYWHERE));
		}

		// busca campeonatos por objeto
		if (filtro.getObjCampeonato() != null) {
			criteria.add(Restrictions.ilike("ca.nome", filtro.getObjCampeonato().getNome()));
		}
		
		if (StringUtils.isNotBlank(filtro.getEtapa())) {
			criteria.add(Restrictions.ilike("e.nome", filtro.getEtapa(), MatchMode.ANYWHERE));
		}

		// busca etapas por objeto
		if (filtro.getObjEtapa() != null) {
			criteria.add(Restrictions.ilike("e.nome", filtro.getObjEtapa().getNome()));
		}
		
		if (StringUtils.isNotBlank(filtro.getDivisao())) {
			criteria.add(Restrictions.ilike("d.nome", filtro.getDivisao(), MatchMode.ANYWHERE));
		}
		
		// busca divisoes por objeto
		if (filtro.getObjDivisao() != null) {
			criteria.add(Restrictions.ilike("d.nome", filtro.getObjDivisao().getNome()));
		}

		return criteria.addOrder(Order.asc("ca.nome")).list();
	}
	
}
