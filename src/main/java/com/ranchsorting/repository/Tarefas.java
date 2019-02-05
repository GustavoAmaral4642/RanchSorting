package com.ranchsorting.repository;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import com.ranchsorting.model.Tarefa;


public class Tarefas {

	private static final long serialVersionUID = 1L;

	@Inject
	private EntityManager manager;

	// Busca tarefa por ID
	public Tarefa porId(Long id) {
		return manager.find(Tarefa.class, id);
	}

	// retorna todas as tarefas
	public List<Tarefa> todasTarefas() {

		return manager.createQuery("from Tarefa", Tarefa.class).getResultList();
	}

}
