package com.ranchsorting.service;

import java.io.Serializable;  

import javax.inject.Inject;
import javax.validation.ConstraintViolationException;

import com.ranchsorting.model.Etapa;
import com.ranchsorting.repository.Etapas;
import com.ranchsorting.util.jpa.Transactional;

public class CadastroEtapaService implements Serializable{

	private static final long serialVersionUID = 1L;

	@Inject
	private Etapas etapas;
	
	@Transactional
	public Etapa salvar(Etapa etapa){
		
		try{
			return etapas.guardar(etapa);	
		} catch(ConstraintViolationException ex){
			throw new NegocioException("Ocorreu algum promblema na gravação da etapa." +
										"Entre em contato com o administrador do Sistema.");
		} catch(RuntimeException ex){
			throw new NegocioException("Ocorreu algum promblema na gravação da etapa." +
					"Entre em contato com o administrador do Sistema.");
		}
		
	}
}
