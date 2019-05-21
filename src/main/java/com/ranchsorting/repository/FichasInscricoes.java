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
import com.ranchsorting.model.StatusFicha;
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
		Criteria criteria = session.createCriteria(FichaInscricao.class).createAlias("campeonato", "ca")
				.createAlias("etapa", "e").createAlias("divisao", "d").createAlias("competidor", "co");

		if (StringUtils.isNotBlank(filtro.getCampeonato())) {
			criteria.add(Restrictions.ilike("ca.nome", filtro.getCampeonato(), MatchMode.ANYWHERE));
		}

		if (StringUtils.isNotBlank(filtro.getEtapa())) {
			criteria.add(Restrictions.ilike("e.nome", filtro.getEtapa(), MatchMode.ANYWHERE));
		}

		if (StringUtils.isNotBlank(filtro.getDivisao())) {
			criteria.add(Restrictions.ilike("d.nome", filtro.getDivisao(), MatchMode.ANYWHERE));
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

		if (filtro.getStatusFicha() != null && filtro.getStatusFicha().equals(StatusFicha.CADASTRADA)) {
			criteria.add(Restrictions.eq("statusFicha", StatusFicha.CADASTRADA));
		}

		if (filtro.getStatusFicha() != null && filtro.getStatusFicha().equals(StatusFicha.EMORDEM)) {
			criteria.add(Restrictions.eq("statusFicha", StatusFicha.EMORDEM));
		}
		
		return criteria.addOrder(Order.asc("ca.nome")).list();
	}
/*
	@SuppressWarnings("unchecked")
	public List<FichaParaFolhaAutomatica> filtradasParaFolhaAutomatica(FichaInscricaoFilter filtro) {

		Session session = manager.unwrap(Session.class);
		Criteria criteria = session.createCriteria(FichaInscricao.class)
					.createAlias("campeonato", "ca")
					.createAlias("etapa", "e")
					.createAlias("divisao", "d")
					.createAlias("competidor", "co");

		ProjectionList pl = Projections.projectionList()
				.add(Projections.property("id").as("id1"))
				.add(Projections.property("co.nome").as("nomeCompetidor1"))
				.add(Projections.property("ca.nome").as("nomeCampeonato"))
				.add(Projections.property("e.nome").as("nomeEtapa"))
				.add(Projections.property("d.nome").as("nomeDivisao"));
		
		criteria.setProjection(pl)
			.addOrder(Order.asc("id1"))
			.setResultTransformer(Transformers.aliasToBean(FichaParaFolhaAutomatica.class));

		return criteria.list();
	}*/
}
