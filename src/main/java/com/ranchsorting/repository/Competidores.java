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

import com.ranchsorting.model.Competidor;
import com.ranchsorting.repository.filter.CompetidorFilter;

public class Competidores implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private EntityManager manager;

	public Competidor guardar(Competidor competidor) {
		return manager.merge(competidor);
	}

	public Competidor porId(Long id) {
		return manager.find(Competidor.class, id);
	}

	public List<Competidor> todosCompetidores() {
		try {
			return manager.createQuery("from Competidor", Competidor.class).getResultList();
		} catch (NoResultException e) {
			return null;
		}
	}
		
	@SuppressWarnings("unchecked")
	public List<Competidor> filtrados(CompetidorFilter filtro) {

		Session session = manager.unwrap(Session.class);
		Criteria criteria = session.createCriteria(Competidor.class);

		if (StringUtils.isNotBlank(filtro.getNome())) {
			criteria.add(Restrictions.ilike("nome", filtro.getNome(), MatchMode.ANYWHERE));
		}

		if (filtro.getDtNascimentoDe() != null) {
			criteria.add(Restrictions.ge("dataNascimento", filtro.getDtNascimentoDe()));
		}

		if (filtro.getDtNascimentoAte() != null) {
			criteria.add(Restrictions.le("dataNascimento", filtro.getDtNascimentoAte()));
		}

		if (StringUtils.isNotBlank(filtro.getResponsavel())) {
			criteria.add(Restrictions.ilike("responsavel", filtro.getResponsavel(), MatchMode.ANYWHERE));
		}

		if (StringUtils.isNotBlank(filtro.getContato())) {
			criteria.add(Restrictions.ilike("contato", filtro.getContato(), MatchMode.ANYWHERE));
		}

		return criteria.addOrder(Order.asc("nome")).list();
	}

}
