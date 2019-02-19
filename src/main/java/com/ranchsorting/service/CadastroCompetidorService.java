package com.ranchsorting.service;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.inject.Inject;
import javax.validation.ConstraintViolationException;

import com.ranchsorting.model.Competidor;
import com.ranchsorting.model.TipoAnuidade;
import com.ranchsorting.repository.Competidores;
import com.ranchsorting.util.jpa.Transactional;

public class CadastroCompetidorService implements Serializable{

	private static final long serialVersionUID = 1L;

	@Inject
	private Competidores competidores;
	
	@Transactional
	public Competidor salvar(Competidor competidor){
		
		if(competidor.getDataPagamentoAnuidade()==null
				|| competidor.getTipoAnuidade()==TipoAnuidade.NAO
				|| competidor.getValorPagoAnuidade().equals(BigDecimal.ZERO)){
			
			competidor.setTipoAnuidade(TipoAnuidade.NAO);
		}
		
		try{
			return competidores.guardar(competidor);	
		} catch(ConstraintViolationException ex){
			throw new NegocioException("Ocorreu algum promblema na gravação do competidor." +
										"Entre em contato com o administrador do Sistema.");
		} catch(RuntimeException ex){
			throw new NegocioException("Ocorreu algum promblema na gravação do competidor." +
					"Entre em contato com o administrador do Sistema.");
		}
		
	}
}
