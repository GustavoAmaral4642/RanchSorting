package com.ranchsorting.repository;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.ranchsorting.model.Recebimento;
import com.ranchsorting.repository.filter.RecebimentoFilter;
import com.ranchsorting.service.NegocioException;
import com.ranchsorting.util.jpa.Transactional;

public class Recebimentos implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private EntityManager manager;

	public Recebimento guardar(Recebimento recebimento) {
		return manager.merge(recebimento);
	}

	// método para remover registros
	@Transactional
	public void remover(Recebimento recebimento) {

		try {
			recebimento = porId(recebimento.getId());

			manager.remove(recebimento);

			manager.flush();
		} catch (PersistenceException e) {
			// esta exceção é lançada pelo banco de dados
			throw new NegocioException("Título não pode ser excluído.");
		}
	}

	public Recebimento porId(Long id) {
		return manager.find(Recebimento.class, id);
	}

	public List<Recebimento> todasRecebimentos() {
		try {
			return manager.createQuery("from Recebimento", Recebimento.class).getResultList();
		} catch (NoResultException e) {
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	public List<Recebimento> filtrados(RecebimentoFilter filtro) {

		Session session = manager.unwrap(Session.class);
		Criteria criteria = session.createCriteria(Recebimento.class).createAlias("competidor", "co")
				.createAlias("campeonato", "ca").createAlias("etapa", "e").createAlias("divisao", "d");

		if (StringUtils.isNotBlank(filtro.getCompetidor())) {
			criteria.add(Restrictions.ilike("co.nome", filtro.getCompetidor(), MatchMode.ANYWHERE));
		}

		if (StringUtils.isNotBlank(filtro.getCampeonato())) {
			criteria.add(Restrictions.ilike("ca.nome", filtro.getCampeonato(), MatchMode.ANYWHERE));
		}

		if (StringUtils.isNotBlank(filtro.getEtapa())) {
			criteria.add(Restrictions.ilike("e.nome", filtro.getEtapa(), MatchMode.ANYWHERE));
		}

		if (StringUtils.isNotBlank(filtro.getDivisao())) {
			criteria.add(Restrictions.ilike("d.nome", filtro.getDivisao(), MatchMode.ANYWHERE));
		}

		if (filtro.getDataCadastroInicial() != null) {
			criteria.add(Restrictions.ge("dataCadastro", filtro.getDataCadastroInicial()));
		}

		if (filtro.getDataCadastroFinal() != null) {
			criteria.add(Restrictions.le("dataCadastro", filtro.getDataCadastroFinal()));
		}

		if (filtro.getDataRecebimentoInicial() != null) {
			criteria.add(Restrictions.ge("dataRecebimento", filtro.getDataRecebimentoInicial()));
		}

		if (filtro.getDataRecebimentoFinal() != null) {
			criteria.add(Restrictions.le("dataRecebimento", filtro.getDataRecebimentoFinal()));
		}

		return criteria.addOrder(Order.asc("co.nome")).list();
	}
}
