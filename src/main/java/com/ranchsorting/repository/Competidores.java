package com.ranchsorting.repository;

import java.io.Serializable; 
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

import com.ranchsorting.model.Competidor;

public class Competidores implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private EntityManager manager;

	public Competidor guardar(Competidor competidor){
		return manager.merge(competidor);
	}
	
	public Competidor porId(Long id) {
		return manager.find(Competidor.class, id);
	}
	
	public List<Competidor> todosCompetidores() {
		try{
			return manager.createQuery("from Competidor", Competidor.class).getResultList();	
		}  catch (NoResultException e) {
			return null;
		}
		
	}
}
