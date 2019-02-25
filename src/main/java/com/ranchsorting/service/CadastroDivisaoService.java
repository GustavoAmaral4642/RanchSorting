package com.ranchsorting.service;

import java.io.Serializable;

import javax.inject.Inject;
import javax.validation.ConstraintViolationException;

import com.ranchsorting.model.Divisao;
import com.ranchsorting.repository.Divisoes;
import com.ranchsorting.util.jpa.Transactional;

public class CadastroDivisaoService implements Serializable{

	private static final long serialVersionUID = 1L;

	@Inject
	private Divisoes divisoes;
	
	@Transactional
	public Divisao salvar(Divisao divisao){
		
		try{
			return divisoes.guardar(divisao);	
		} catch(ConstraintViolationException ex){
			throw new NegocioException("Ocorreu algum promblema na gravação da divisão." +
										"Entre em contato com o administrador do Sistema.");
		} catch(RuntimeException ex){
			throw new NegocioException("Ocorreu algum promblema na gravação do divisão." +
					"Entre em contato com o administrador do Sistema.");
		}
		
	}
}
