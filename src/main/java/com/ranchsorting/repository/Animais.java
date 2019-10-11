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
import org.primefaces.model.LazyDataModel;

import com.ranchsorting.model.Animal;
import com.ranchsorting.model.Competidor;
import com.ranchsorting.repository.filter.AnimalFilter;
import com.ranchsorting.service.NegocioException;
import com.ranchsorting.util.jpa.Transactional;

public class Animais implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private EntityManager manager;


	public Animal guardar(Animal animal) {
		return manager.merge(animal);
	}

	// método para remover registros
	@Transactional
	public void remover(Animal animal) {

		try {
			animal = porId(animal.getId());

			manager.remove(animal);

			manager.flush();
		} catch (PersistenceException e) {
			// esta exceção é lançada pelo banco de dados
			throw new NegocioException("Animal não pode ser excluído.");
		}
	}

	public Animal porId(Long id) {
		return manager.find(Animal.class, id);
	}

	public List<Animal> todosAnimais() {

		return manager.createQuery("from Animal", Animal.class).getResultList();
	}

	public List<Animal> animaisCompetidor(Competidor competidor) {
		return manager.createQuery("from Animal where competidor = :competidor", Animal.class)
				.setParameter("competidor", competidor).getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<Animal> filtrados(AnimalFilter filtro) {

		Session session = manager.unwrap(Session.class);
		Criteria criteria = session.createCriteria(Animal.class).createAlias("competidor", "c");

		if (StringUtils.isNotBlank(filtro.getNome())) {
			criteria.add(Restrictions.ilike("nome", filtro.getNome(), MatchMode.ANYWHERE));
		}

		if (StringUtils.isNotBlank(filtro.getCor())) {
			criteria.add(Restrictions.ilike("cor", filtro.getCor(), MatchMode.ANYWHERE));
		}

		if (StringUtils.isNotBlank(filtro.getCompetidor())) {
			criteria.add(Restrictions.ilike("c.nome", filtro.getCompetidor(), MatchMode.ANYWHERE));
		}

		return criteria.addOrder(Order.asc("nome")).list();
	}

	@SuppressWarnings("unchecked")
	public List<Animal> buscarAnimaisComPaginacao(int first, int pageSize) {		
		return manager.createNamedQuery("Animal.buscarAnimaisPaginacao")
				.setFirstResult(first)
				.setMaxResults(pageSize)
				.getResultList();
	}

	public Long encontrarQuantidadeTotalDeAnimais(){
		return manager.createQuery("select count(a) from Animal a", Long.class).getSingleResult();
	}
	
}
