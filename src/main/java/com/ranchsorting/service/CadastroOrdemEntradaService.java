package com.ranchsorting.service;

import java.io.Serializable;   

import javax.inject.Inject;
import javax.validation.ConstraintViolationException;

import com.ranchsorting.model.OrdemEntrada;
import com.ranchsorting.repository.OrdensEntradas;
import com.ranchsorting.util.jpa.Transactional;

public class CadastroOrdemEntradaService implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private OrdensEntradas ordens;

	@Transactional
	public OrdemEntrada salvar(OrdemEntrada ordem) {
		
		return ordens.guardar(ordem);
		
/*
		try {

		} catch (NullPointerException ex) {
			throw new NegocioException("Ocorreu algum promblema na gravação da Ordem de Entrada."
					+ "Entre em contato com o administrador do Sistema. (NullPointerException)");
		} catch (ConstraintViolationException ex) {
			throw new NegocioException("Ocorreu algum promblema na gravação da Ordem de Entrada."
					+ "Entre em contato com o administrador do Sistema. (ConstraintViolationException)");
		} catch (ArithmeticException ex) {
			throw new NegocioException("Ocorreu algum promblema na gravação da Ordem de Entrada."
					+ "Entre em contato com o administrador do Sistema. (ArithmeticException)");
		} catch (RuntimeException ex) {
			throw new NegocioException("Ocorreu algum promblema na gravação da Ordem de Entrada."
					+ "Entre em contato com o administrador do Sistema. (RuntimeException)");
		} catch (Exception ex) {
			throw new NegocioException("Ocorreu algum promblema na gravação da Ordem de Entrada."
					+ "Entre em contato com o administrador do Sistema. (Exception)");
		}
	*/}
}
