package com.ranchsorting.repository;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import com.ranchsorting.model.Usuario;

public class Usuarios {

	private static final long serialVersionUID = 1L;

	@Inject
	private EntityManager manager;

	//Busca usuário por ID
	public Usuario porId(Long id) {
		return manager.find(Usuario.class, id);
	}
	
	//retorna todos os usuarios
	public List<Usuario> todosUsuarios() {

		return manager.createQuery("from Usuario", Usuario.class).getResultList();
	}

}
