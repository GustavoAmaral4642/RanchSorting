package com.ranchsorting.service;

import java.io.Serializable;  

import javax.inject.Inject;
import javax.validation.ConstraintViolationException;

import com.ranchsorting.model.Campeonato;
import com.ranchsorting.repository.Campeonatos;
import com.ranchsorting.util.jpa.Transactional;

public class CadastroCampeonatoService implements Serializable{

	private static final long serialVersionUID = 1L;

	@Inject
	private Campeonatos campeonatos;
	
	@Transactional
	public Campeonato salvar(Campeonato campeonato){
		
		try{
			return campeonatos.guardar(campeonato);	
		
		}catch (ConstraintViolationException ex) {
			throw new NegocioException("Ocorreu algum promblema na gravação do campeonato."
					+ "Entre em contato com o administrador do Sistema. (ConstraintViolationException)");
		} catch (ArithmeticException ex) {
			throw new NegocioException("Ocorreu algum promblema na gravação do campeonato."
					+ "Entre em contato com o administrador do Sistema. (ArithmeticException)");
		} catch (RuntimeException ex) {
			throw new NegocioException("Ocorreu algum promblema na gravação do campeonato."
					+ "Entre em contato com o administrador do Sistema. (RuntimeException)");
		} catch (Exception ex) {
			throw new NegocioException("Ocorreu algum promblema na gravação do campeonato."
					+ "Entre em contato com o administrador do Sistema. (Exception)");
		}
		
	}
	
}
