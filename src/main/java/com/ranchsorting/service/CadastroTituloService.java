package com.ranchsorting.service;

import java.io.Serializable;   

import javax.inject.Inject;
import javax.validation.ConstraintViolationException;

import com.ranchsorting.model.Recebimento;
import com.ranchsorting.model.Titulo;
import com.ranchsorting.repository.Recebimentos;
import com.ranchsorting.repository.Titulos;
import com.ranchsorting.util.jpa.Transactional;

public class CadastroTituloService implements Serializable{

	private static final long serialVersionUID = 1L;

	@Inject
	private Titulos titulos;
	
	@Inject
	private Recebimentos recebimentos;
	
	@Transactional
	public Titulo salvar(Titulo titulo){
		
		try{
			return titulos.guardar(titulo);	
		} catch(ConstraintViolationException ex){
			throw new NegocioException("Ocorreu algum promblema na gravação do Título." +
										"Entre em contato com o administrador do Sistema.");
		} catch(RuntimeException ex){
			throw new NegocioException("Ocorreu algum promblema na gravação do Título." +
					"Entre em contato com o administrador do Sistema.");
		}
		
	}
	
	@Transactional
	public Titulo salvarRecebimento(Titulo titulo, Recebimento recebimento){
		
		try{
			recebimentos.guardarRecebimentos(recebimento);
			return titulos.guardar(titulo);	
		} catch(ConstraintViolationException ex){
			throw new NegocioException("Ocorreu algum promblema na gravação do Título." +
										"Entre em contato com o administrador do Sistema.");
		} catch(RuntimeException ex){
			throw new NegocioException("Ocorreu algum promblema na gravação do Título." +
					"Entre em contato com o administrador do Sistema.");
		}
		
	}
}
