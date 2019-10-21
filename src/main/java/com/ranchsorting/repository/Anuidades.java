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

import com.ranchsorting.model.Anuidade;
import com.ranchsorting.model.Competidor;
import com.ranchsorting.repository.filter.AnuidadeFilter;

public class Anuidades implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private EntityManager manager;

	public Anuidade guardar(Anuidade anuidade) {
		return manager.merge(anuidade);
	}

	public Anuidade porId(Long id) {
		return manager.find(Anuidade.class, id);
	}

	public List<Anuidade> todasAnuidades() {

		return manager.createQuery("from Anuidade", Anuidade.class).getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<Anuidade> filtrados(AnuidadeFilter filtro) {

		Session session = manager.unwrap(Session.class);
		Criteria criteria = session.createCriteria(Anuidade.class).createAlias("competidor", "co")
				.createAlias("campeonato", "ca");

		if (StringUtils.isNotBlank(filtro.getCompetidor())) {
			criteria.add(Restrictions.ilike("co.nome", filtro.getCompetidor(), MatchMode.ANYWHERE));
		}

		if (StringUtils.isNotBlank(filtro.getCampeonato())) {
			criteria.add(Restrictions.ilike("ca.nome", filtro.getCampeonato(), MatchMode.ANYWHERE));
		}

		if (filtro.getDataPagamentoInicial() != null) {
			criteria.add(Restrictions.ge("dataPagamento", filtro.getDataPagamentoInicial()));
		}

		if (filtro.getDataPagamentoFinal() != null) {
			criteria.add(Restrictions.le("dataPagamento", filtro.getDataPagamentoFinal()));
		}

		return criteria.addOrder(Order.asc("co.nome")).list();
	}

}
