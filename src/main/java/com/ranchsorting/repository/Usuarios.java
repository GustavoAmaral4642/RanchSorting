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

import com.ranchsorting.model.Usuario;
import com.ranchsorting.repository.filter.UsuarioFilter;
import com.ranchsorting.service.NegocioException;
import com.ranchsorting.util.jpa.Transactional;

public class Usuarios implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private EntityManager manager;

	public Usuario guardar(Usuario usuario) {
		return manager.merge(usuario);
	}

	public Usuario porEmail(String email) {
		try {
			return manager.createQuery("from Usuario where upper(email) = :email", Usuario.class)
					.setParameter("email", email.toUpperCase()).getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

	// método para remover registros
	@Transactional 
	public void remover(Usuario usuario) {

		try {
			usuario = porId(usuario.getId());

			manager.remove(usuario);

			manager.flush();
		} catch (PersistenceException e) {
			// esta exceção é lançada pelo banco de dados
			throw new NegocioException("Usuário não pode ser excluído.");
		}
	}

	// Busca usuário por ID
	public Usuario porId(Long id) {
		return manager.find(Usuario.class, id);
	}

	// retorna todos os usuarios
	public List<Usuario> todosUsuarios() {

		return manager.createQuery("from Usuario", Usuario.class).getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<Usuario> filtrados(UsuarioFilter filtro) {

		Session session = manager.unwrap(Session.class);
		Criteria criteria = session.createCriteria(Usuario.class);

		if (StringUtils.isNotBlank(filtro.getNome())) {
			criteria.add(Restrictions.ilike("nome", filtro.getNome(), MatchMode.ANYWHERE));
		}

		if (StringUtils.isNotBlank(filtro.getEmail())) {
			criteria.add(Restrictions.ilike("email", filtro.getEmail(), MatchMode.ANYWHERE));
		}

		return criteria.addOrder(Order.asc("nome")).list();
	}

}
