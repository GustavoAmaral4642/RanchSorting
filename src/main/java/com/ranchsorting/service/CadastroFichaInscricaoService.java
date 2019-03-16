package com.ranchsorting.service;

import java.io.Serializable; 

import javax.inject.Inject;
import javax.validation.ConstraintViolationException;

import com.ranchsorting.model.FichaInscricao;
import com.ranchsorting.repository.FichasInscricoes;
import com.ranchsorting.util.jpa.Transactional;

public class CadastroFichaInscricaoService implements Serializable{

	private static final long serialVersionUID = 1L;

	@Inject
	private FichasInscricoes fichas;
	
	@Transactional
	public FichaInscricao salvar(FichaInscricao ficha){
		
		try{
			return fichas.guardar(ficha);	
		} catch(ConstraintViolationException ex){
			throw new NegocioException("Ocorreu algum promblema na gravação da ficha de inscrição." +
										"Entre em contato com o administrador do Sistema.");
		} catch(RuntimeException ex){
			throw new NegocioException("Ocorreu algum promblema na gravação da ficha de inscrição." +
					"Entre em contato com o administrador do Sistema.");
		} catch(Exception ex){
			throw new NegocioException("Ocorreu algum promblema na gravação da ficha de inscrição." +
					"Entre em contato com o administrador do Sistema.");
		}
		
	}
}
