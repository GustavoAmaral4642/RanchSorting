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

import com.ranchsorting.model.Competidor;
import com.ranchsorting.repository.filter.AnimalFilter;
import com.ranchsorting.repository.filter.CompetidorFilter;
import com.ranchsorting.service.NegocioException;
import com.ranchsorting.util.jpa.Transactional;

public class Competidores implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private EntityManager manager;

	public Competidor guardar(Competidor competidor) {
		return manager.merge(competidor);
	}

	// método para remover registros
	@Transactional
	public void remover(Competidor competidor) {

		try {
			competidor = porId(competidor.getId());

			manager.remove(competidor);

			manager.flush();
		} catch (PersistenceException e) {
			// esta exceção é lançada pelo banco de dados
			throw new NegocioException("Competidor não pode ser excluído.");
		}
	}

	public Competidor porId(Long id) {
		// return manager.find(Competidor.class, id);

		Competidor competidor = manager.createNamedQuery("Competidor.buscarCompetidorPorId", Competidor.class)
				.setParameter("id", id).getSingleResult();

		return competidor;
	}

	public Competidor porNome(String nome) {
		try {
			return manager.createQuery("from Competidor where upper(nome) = :nome", Competidor.class)
					.setParameter("nome", nome.toUpperCase()).getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

	public List<Competidor> todosCompetidores() {
		try {
			return manager.createQuery("from Competidor", Competidor.class).getResultList();
		} catch (NoResultException e) {
			return null;
		}
	}

	// usar somente em combobox
	public List<Competidor> consultaCompetidoresParaCombobox() {
		try {
			String jpql = "from Competidor";
			List<Competidor> resultados = manager.createQuery(jpql, Competidor.class).getResultList();

			return resultados;
		} catch (NoResultException e) {
			return null;
		}
	}
/*
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
*/
	
	@SuppressWarnings("unchecked")
	public List<Competidor> buscarCompetidoresComPaginacao(int first, int pageSize, CompetidorFilter filtro) {

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

		criteria.setFirstResult(first);
		criteria.setMaxResults(pageSize);

		return criteria.addOrder(Order.asc("nome")).list();

		// consulta em JPQL.
		// com criteria dá para usar filtro melhor
		/*
		 * return manager.createNamedQuery("Animal.buscarAnimaisPaginacao")
		 * .setFirstResult(first) .setMaxResults(pageSize) .getResultList();
		 */
	}

	public Long encontrarQuantidadeTotalDeCompetidores() {
		return manager.createQuery("select count(c) from Competidor c", Long.class).getSingleResult();
	}

}
