package com.ranchsorting.repository;

import java.io.Serializable;   
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.ranchsorting.model.Campeonato;
import com.ranchsorting.repository.filter.CampeonatoFilter;
import com.ranchsorting.service.NegocioException;
import com.ranchsorting.util.jpa.Transactional;

public class Campeonatos implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private EntityManager manager;

	public Campeonato guardar(Campeonato campeonato) {
		return manager.merge(campeonato);
	}

	// método para remover registros
	@Transactional
	public void remover(Campeonato campeonato) {

		try {
			campeonato = porId(campeonato.getId());

			manager.remove(campeonato);

			manager.flush();
		} catch (PersistenceException e) {
			// esta exceção é lançada pelo banco de dados
			throw new NegocioException("Campeonato não pode ser excluído.");
		}
	}

	public Campeonato porId(Long id) {
		//return manager.find(Campeonato.class, id);

		Campeonato campeonato = manager.createNamedQuery("Campeonato.buscarCampeonatoPorId", Campeonato.class)
				.setParameter("id", id).getSingleResult();

		return campeonato;
	}

	public List<Campeonato> todosCampeonatos() {
		
		List<Campeonato> campeonatos = manager.createNamedQuery("Campeonato.buscarTodosCampeonatos", Campeonato.class)
				.getResultList();

		return campeonatos;
	}

/*
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

		if (filtro.getDataTerminoInicial() != null) {
			criteria.add(Restrictions.ge("dataTermino", filtro.getDataTerminoInicial()));
		}

		if (filtro.getDataTerminoFinal() != null) {
			criteria.add(Restrictions.le("dataTermino", filtro.getDataTerminoFinal()));
		}

		return criteria.addOrder(Order.asc("nome")).list();
	}
	*/
	@SuppressWarnings("unchecked")
	public List<Campeonato> buscarCampeonatosComPaginacao(int first, int pageSize, CampeonatoFilter filtro,
			String ordenar, String tipoOrdenacao) {

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

		if (filtro.getDataTerminoInicial() != null) {
			criteria.add(Restrictions.ge("dataTermino", filtro.getDataTerminoInicial()));
		}

		if (filtro.getDataTerminoFinal() != null) {
			criteria.add(Restrictions.le("dataTermino", filtro.getDataTerminoFinal()));
		}

		criteria.setFirstResult(first);
		criteria.setMaxResults(pageSize);

		if (tipoOrdenacao.equals("decrescente")) {
			return criteria.addOrder(Order.desc(ordenar)).list();
		} else {
			return criteria.addOrder(Order.asc(ordenar)).list();
		}
	}

	public Long encontrarQuantidadeTotalDeCampeonatos() {
		return manager.createQuery("select count(c) from Campeonato c", Long.class).getSingleResult();
	}
}
