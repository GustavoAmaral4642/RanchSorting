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
		// return manager.find(Animal.class, id);

		Animal animal = new Animal();
		// este try catch foi feito para tratar dos donos dos cavalos.
		// se tem dono, vai no try
		// se não tem dono, ocorre uma exceção e entra no catch
		try {
			Object[] vetorAnimal = (Object[]) manager.createNamedQuery("Animal.buscarAnimaisPorId")
					.setParameter("id", id).getSingleResult();
			
			Competidor competidor = new Competidor();

			animal = (Animal) vetorAnimal[0];

			competidor = (Competidor) vetorAnimal[1];
			animal.setCompetidor(competidor);
			
		} catch (NoResultException ex) {
			animal = manager.find(Animal.class, id);
			
		} catch(Exception ex){
			throw new NegocioException("Ocorreu algum promblema na consulta do animal." +
					"Entre em contato com o administrador do Sistema.");
		}

		return animal;
	}

	public List<Animal> todosAnimais() {

		return manager.createQuery("from Animal", Animal.class).getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<Animal> buscarAnimaisComPaginacao(int first, int pageSize, AnimalFilter filtro) {
		
		Session session = manager.unwrap(Session.class);
		Criteria criteria = session.createCriteria(Animal.class);

		if (StringUtils.isNotBlank(filtro.getNome())) {
			criteria.add(Restrictions.ilike("nome", filtro.getNome(), MatchMode.ANYWHERE));
		}

		if (StringUtils.isNotBlank(filtro.getCor())) {
			criteria.add(Restrictions.ilike("cor", filtro.getCor(), MatchMode.ANYWHERE));
		}

		criteria.setFirstResult(first);
		criteria.setMaxResults(pageSize);
		
		return criteria.addOrder(Order.asc("nome")).list();
		
		// consulta em JPQL.
		// com criteria dá para usar filtro melhor
		/*return manager.createNamedQuery("Animal.buscarAnimaisPaginacao")
				.setFirstResult(first)
				.setMaxResults(pageSize)
				.getResultList();*/
	}

	public Long encontrarQuantidadeTotalDeAnimais() {
		return manager.createQuery("select count(a) from Animal a", Long.class).getSingleResult();
	}

}
