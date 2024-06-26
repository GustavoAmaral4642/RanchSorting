package com.ranchsorting.repository;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.ranchsorting.model.Campeonato;
import com.ranchsorting.model.Competidor;
import com.ranchsorting.model.Divisao;
import com.ranchsorting.model.Etapa;
import com.ranchsorting.model.FichaInscricao;
import com.ranchsorting.model.StatusFicha;
import com.ranchsorting.repository.filter.FichaInscricaoFilter;
import com.ranchsorting.service.NegocioException;

public class FichasInscricoes implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private EntityManager manager;

	public FichaInscricao guardar(FichaInscricao ficha) {
		return manager.merge(ficha);
	}

	public FichaInscricao porId(Long id) {
		
		FichaInscricao ficha = new FichaInscricao();
		
		try {
			Object[] vetorFicha = (Object[]) manager.createNamedQuery("FichaInscricao.buscarFichaParaAlteracao").setParameter("id", id)
					.getSingleResult();

			Campeonato campeonato = new Campeonato();
			Etapa etapa = new Etapa();
			Divisao divisao = new Divisao();
			Competidor competidor = new Competidor();

			ficha = (FichaInscricao) vetorFicha[0];

			campeonato = (Campeonato) vetorFicha[1];
			etapa = (Etapa) vetorFicha[2];
			divisao = (Divisao) vetorFicha[3];
			competidor = (Competidor) vetorFicha[4];
			
			ficha.setCampeonato(campeonato);
			ficha.setEtapa(etapa);
			ficha.setDivisao(divisao);
			ficha.setCompetidor(competidor);

		} catch (NoResultException ex) {
			ficha = manager.find(FichaInscricao.class, id);

		} catch (Exception ex) {
			throw new NegocioException("Ocorreu algum promblema na consulta da Ficha de Inscrição."
					+ "Entre em contato com o administrador do Sistema.");
		}
		return ficha;
	}

	public List<FichaInscricao> todasFichas() {

		return manager.createQuery("from FichaInscricao", FichaInscricao.class).getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<FichaInscricao> filtradas(FichaInscricaoFilter filtro) {

		Session session = manager.unwrap(Session.class);
		Criteria criteria = session.createCriteria(FichaInscricao.class).createAlias("campeonato", "ca")
				.createAlias("etapa", "e").createAlias("divisao", "d").createAlias("competidor", "co");

		if (filtro.getId() != null) {
			criteria.add(Restrictions.eq("id", filtro.getId()));
		}

		if (StringUtils.isNotBlank(filtro.getCompetidor())) {
			criteria.add(Restrictions.ilike("co.nome", filtro.getCompetidor(), MatchMode.ANYWHERE));
		}

		// busca Competidores por objeto
		if (filtro.getObjCompetidor() != null) {
			criteria.add(Restrictions.ilike("co.nome", filtro.getObjCompetidor().getNome()));
		}

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

	@SuppressWarnings("unchecked")
	public List<FichaInscricao> buscarFichasInscricoesComPaginacao(int first, int pageSize, FichaInscricaoFilter filtro,
			String ordenar, String tipoOrdenacao) {

		Session session = manager.unwrap(Session.class);
		Criteria criteria = session.createCriteria(FichaInscricao.class);
		criteria.setFetchMode("campeonato", FetchMode.JOIN).createAlias("campeonato", "ca");
		criteria.setFetchMode("etapa", FetchMode.JOIN).createAlias("etapa", "e");
		criteria.setFetchMode("divisao", FetchMode.JOIN).createAlias("divisao", "d");
		criteria.setFetchMode("competidor", FetchMode.JOIN).createAlias("competidor", "co");

		if (filtro.getId() != null) {
			criteria.add(Restrictions.eq("id", filtro.getId()));
		}

		if (StringUtils.isNotBlank(filtro.getCompetidor())) {
			criteria.add(Restrictions.ilike("co.nome", filtro.getCompetidor(), MatchMode.ANYWHERE));
		}

		// busca Competidores por objeto
		if (filtro.getObjCompetidor() != null) {
			criteria.add(Restrictions.ilike("co.nome", filtro.getObjCompetidor().getNome()));
		}

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

		criteria.setFirstResult(first);
		criteria.setMaxResults(pageSize);

		if (tipoOrdenacao.equals("decrescente")) {
			return criteria.addOrder(Order.desc(ordenar)).list();
		} else {
			return criteria.addOrder(Order.asc(ordenar)).list();
		}
	}

	public Long encontrarQuantidadeTotalDeFichasInscricoes() {
		return manager.createQuery("select count(f) from FichaInscricao f", Long.class).getSingleResult();
	}

}
