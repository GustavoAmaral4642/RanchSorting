package com.ranchsorting.service;

import java.io.Serializable; 

import javax.inject.Inject;
import javax.validation.ConstraintViolationException;

import com.ranchsorting.model.Anuidade;
import com.ranchsorting.repository.Anuidades;
import com.ranchsorting.util.jpa.Transactional;

public class CadastroAnuidadeService implements Serializable{

	private static final long serialVersionUID = 1L;

	@Inject
	private Anuidades anuidades;
	
	@Transactional
	public Anuidade salvar(Anuidade anuidade){
		
		try{
			return anuidades.guardar(anuidade);	
		} catch(ConstraintViolationException ex){
			throw new NegocioException("Ocorreu algum promblema na gravação da anuidade." +
										"Entre em contato com o administrador do Sistema.");
		} catch(RuntimeException ex){
			throw new NegocioException("Ocorreu algum promblema na gravação da anuidade." +
					"Entre em contato com o administrador do Sistema.");
		}
		
	}
}
