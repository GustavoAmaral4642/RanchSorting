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

import com.ranchsorting.model.FichaInscricao;
import com.ranchsorting.repository.filter.FichaInscricaoFilter;

public class FichasInscricoes implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private EntityManager manager;

	public FichaInscricao guardar(FichaInscricao ficha) {
		return manager.merge(ficha);
	}

	public FichaInscricao porId(Long id) {
		return manager.find(FichaInscricao.class, id);
	}

	public List<FichaInscricao> todasFichas() {

		return manager.createQuery("from FichaInscricao", FichaInscricao.class).getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<FichaInscricao> filtradas(FichaInscricaoFilter filtro) {

		Session session = manager.unwrap(Session.class);
		Criteria criteria = session.createCriteria(FichaInscricao.class)
				.createAlias("campeonato", "ca")
				.createAlias("etapa", "e")
				.createAlias("competidor", "co");

		if (StringUtils.isNotBlank(filtro.getCampeonato())) {
			criteria.add(Restrictions.ilike("ca.nome", filtro.getCampeonato(), MatchMode.ANYWHERE));
		}

		if (StringUtils.isNotBlank(filtro.getEtapa())) {
			criteria.add(Restrictions.ilike("e.nome", filtro.getEtapa(), MatchMode.ANYWHERE));
		}

		if (StringUtils.isNotBlank(filtro.getCompetidor())) {
			criteria.add(Restrictions.ilike("co.nome", filtro.getCompetidor(), MatchMode.ANYWHERE));
		}

		if (filtro.getDataInscricaoInicial() != null) {
			criteria.add(Restrictions.ge("dataInscricao", filtro.getDataInscricaoInicial()));
		}

		if (filtro.getDataInscricaoFinal() != null) {
			criteria.add(Restrictions.le("dataInscricao", filtro.getDataInscricaoFinal()));
		}
		
		return criteria.addOrder(Order.asc("ca.nome")).list();
	}

}
