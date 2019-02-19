package com.ranchsorting.repository;

import java.io.Serializable;  
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import com.ranchsorting.model.Animal;

public class Animais implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private EntityManager manager;

	public Animal guardar(Animal animal){
		return manager.merge(animal);
	}
	
	public Animal porId(Long id) {
		return manager.find(Animal.class, id);
	}
	
	public List<Animal> todosAnimais() {

		return manager.createQuery("from Animal", Animal.class).getResultList();
	}

}
