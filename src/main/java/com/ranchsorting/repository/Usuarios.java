package com.ranchsorting.repository;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

import com.ranchsorting.model.Usuario;

public class Usuarios implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private EntityManager manager;

	public Usuario guardar(Usuario usuario){
		return manager.merge(usuario);
	}
	
	public Usuario porEmail(String email) {
		try {
			return manager.createQuery("from Usuario where upper(email) = :email", Usuario.class)
				.setParameter("email", email.toUpperCase())
				.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}
	
	//Busca usuário por ID
	public Usuario porId(Long id) {
		return manager.find(Usuario.class, id);
	}
	
	//retorna todos os usuarios
	public List<Usuario> todosUsuarios() {

		return manager.createQuery("from Usuario", Usuario.class).getResultList();
	}
	
	

}
