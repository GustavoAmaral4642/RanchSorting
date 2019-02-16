package com.ranchsorting.controller;

import java.io.Serializable;

import javax.faces.bean.ViewScoped;
import javax.inject.Named;

import com.ranchsorting.model.Animal;

@Named
@ViewScoped
public class CadastroAnimalBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private Animal animal;

	public CadastroAnimalBean() {
		animal = new Animal();
	}

	public void inicializar() {
	
	}

	public void limpar() {
		animal = new Animal();
	}

	public void salvar() {

	}

	public Animal getAnimal() {
		return animal;
	}

	public void setAnimal(Animal animal) {
		this.animal = animal;
	}

}
