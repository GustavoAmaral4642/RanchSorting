package com.ranchsorting.service;

import java.io.Serializable;

import javax.inject.Inject;
import javax.validation.ConstraintViolationException;

import com.ranchsorting.model.Animal;
import com.ranchsorting.repository.Animais;
import com.ranchsorting.util.jpa.Transactional;

public class CadastroAnimalService implements Serializable{

	private static final long serialVersionUID = 1L;

	@Inject
	private Animais animales;
	
	@Transactional
	public Animal salvar(Animal animal){
		return animales.guardar(animal);
		/*
		try{
			return animales.guardar(animal);	
		} catch(ConstraintViolationException ex){
			throw new NegocioException("Ocorreu algum promblema na gravação do animal." +
										"Entre em contato com o administrador do Sistema.");
		} catch(RuntimeException ex){
			throw new NegocioException("Ocorreu algum promblema na gravação do animal." +
					"Entre em contato com o administrador do Sistema.");
		}*/
		
	}
}
