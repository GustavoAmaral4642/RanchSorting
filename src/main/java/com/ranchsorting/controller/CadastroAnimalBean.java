package com.ranchsorting.controller;

import java.io.Serializable;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.ranchsorting.model.Animal;
import com.ranchsorting.model.Competidor;
import com.ranchsorting.model.Etnia;
import com.ranchsorting.repository.Competidores;
import com.ranchsorting.service.CadastroAnimalService;
import com.ranchsorting.util.jsf.FacesUtil;

@Named
@ViewScoped
public class CadastroAnimalBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private Competidores competidores;

	@Inject
	private CadastroAnimalService cadastroAnimalService;

	private Animal animal;

	private List<Competidor> todosCompetidores;

	public CadastroAnimalBean() {
		limpar();
	}

	public void inicializar() {
		todosCompetidores = competidores.todosCompetidores();
	}

	public void limpar() {
		animal = new Animal();
		animal.setEtnia(Etnia.MACHO);
	}

	public void salvar() {
		
		this.animal = cadastroAnimalService.salvar(animal);
		
		limpar();
		
		FacesUtil.addInfoMessage("Animal salvo com sucesso!");
	}

	public Animal getAnimal() {
		return animal;
	}

	public void setAnimal(Animal animal) {
		this.animal = animal;
	}

	public List<Competidor> getTodosCompetidores() {
		return todosCompetidores;
	}

	public boolean isEditando(){
		return this.getAnimal().getId() != null;
	}
	
}
