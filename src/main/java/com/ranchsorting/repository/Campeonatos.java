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
import com.ranchsorting.repository.filter.CampeonatoFilter;

public class Campeonatos implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private EntityManager manager;

	public Campeonato guardar(Campeonato campeonato) {
		return manager.merge(campeonato);
	}

	public Campeonato porId(Long id) {
		return manager.find(Campeonato.class, id);
	}

	public List<Campeonato> todosCampeonatos() {
		try {
			return manager.createQuery("from Campeonato", Campeonato.class).getResultList();
		} catch (NoResultException e) {
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	public List<Campeonato> filtrados(CampeonatoFilter filtro) {

		Session session = manager.unwrap(Session.class);
		Criteria criteria = session.createCriteria(Campeonato.class);

		if (StringUtils.isNotBlank(filtro.getNome())) {
			criteria.add(Restrictions.ilike("nome", filtro.getNome(), MatchMode.ANYWHERE));
		}

		if (filtro.getDataAberturaInicial() != null) {
			criteria.add(Restrictions.ge("dataAbertura", filtro.getDataAberturaInicial()));
		}

		if (filtro.getDataAberturaFinal() != null) {
			criteria.add(Restrictions.le("dataAbertura", filtro.getDataAberturaFinal()));
		}

		if (filtro.getDataEventoInicial() != null) {
			criteria.add(Restrictions.ge("dataEvento", filtro.getDataEventoInicial()));
		}

		if (filtro.getDataEventoFinal() != null) {
			criteria.add(Restrictions.le("dataEvento", filtro.getDataEventoFinal()));
		}

		return criteria.addOrder(Order.asc("nome")).list();
	}

}