package com.ranchsorting.repository;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.ranchsorting.model.Campeonato;
import com.ranchsorting.model.Competidor;
import com.ranchsorting.model.Etapa;
import com.ranchsorting.repository.filter.EtapaFilter;
import com.ranchsorting.service.NegocioException;
import com.ranchsorting.util.jpa.Transactional;

public class Etapas implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private EntityManager manager;

	public Etapa guardar(Etapa etapa) {
		return manager.merge(etapa);
	}

	// método para remover registros
	@Transactional
	public void remover(Etapa etapa) {

		try {
			etapa = porId(etapa.getId());

			manager.remove(etapa);

			manager.flush();
		} catch (PersistenceException e) {
			// esta exceção é lançada pelo banco de dados
			throw new NegocioException("Etapa não pode ser excluída.");
		}
	}

	public Etapa porId(Long id) {
		// return manager.find(Etapa.class, id);
		Etapa etapa = new Etapa();
		// este try catch foi feito para tratar dos campeonatos das etapas.
		// se tem campeonato, vai no try
		// se não tem campeonato, ocorre uma exceção e entra no catch
		try {
			Object[] vetorEtapa = (Object[]) manager.createNamedQuery("Etapa.buscarEtapasPorId")
					.setParameter("id", id).getSingleResult();

			Campeonato campeonato = new Campeonato();

			etapa = (Etapa) vetorEtapa[0];

			campeonato = (Campeonato) vetorEtapa[1];
			etapa.setCampeonato(campeonato);

		} catch (NoResultException ex) {
			etapa = manager.find(Etapa.class, id);

		} catch (Exception ex) {
			throw new NegocioException("Ocorreu algum promblema na consulta da Etapa."
					+ "Entre em contato com o administrador do Sistema.");
		}

		return etapa;
	}

	public List<Etapa> todasEtapas() {
		try {
			return manager.createQuery("from Etapa", Etapa.class).getResultList();
		} catch (NoResultException e) {
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	public List<Etapa> filtradas(EtapaFilter filtro) {

		Session session = manager.unwrap(Session.class);
		Criteria criteria = session.createCriteria(Etapa.class).createAlias("campeonato", "c");

		if (StringUtils.isNotBlank(filtro.getNome())) {
			criteria.add(Restrictions.ilike("nome", filtro.getNome(), MatchMode.ANYWHERE));
		}

		if (StringUtils.isNotBlank(filtro.getCampeonato())) {
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

	// busca etapas do campeonato
	public List<Etapa> etapasDoCampeonato(Campeonato campeonato) {
		
		List<Etapa> etapas = manager.createNamedQuery("Etapa.buscarEtapasPorCampeonato", Etapa.class)
				.setParameter("campeonato", campeonato).getResultList();

		return etapas;
	}

	@SuppressWarnings("unchecked")
	public List<Etapa> buscarEtapasComPaginacao(int first, int pageSize, EtapaFilter filtro, String ordenar,
			String tipoOrdenacao) {

		Session session = manager.unwrap(Session.class);
		Criteria criteria = session.createCriteria(Etapa.class);
		criteria.setFetchMode("campeonato", FetchMode.JOIN);

		if (StringUtils.isNotBlank(filtro.getNome())) {
			criteria.add(Restrictions.ilike("nome", filtro.getNome(), MatchMode.ANYWHERE));
		}

		if (StringUtils.isNotBlank(filtro.getCampeonato())) {
			criteria.add(Restrictions.ilike("c.nome", filtro.getCampeonato(), MatchMode.ANYWHERE));
		}

		if (filtro.getDataEventoInicial() != null) {
			criteria.add(Restrictions.ge("dataEvento", filtro.getDataEventoInicial()));
		}

		if (filtro.getDataEventoFinal() != null) {
			criteria.add(Restrictions.le("dataEvento", filtro.getDataEventoFinal()));
		}
		criteria.setFirstResult(first);
		criteria.setMaxResults(pageSize);

		if (tipoOrdenacao.equals("decrescente")) {
			return criteria.addOrder(Order.desc(ordenar)).list();
		} else {
			return criteria.addOrder(Order.asc(ordenar)).list();
		}
	}

	public Long encontrarQuantidadeTotalDeEtapas() {
		return manager.createQuery("select count(e) from Etapa e", Long.class).getSingleResult();
	}

}
