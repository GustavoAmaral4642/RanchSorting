package com.ranchsorting.service;

import java.io.Serializable;

import javax.inject.Inject;
import javax.validation.ConstraintViolationException;

import com.ranchsorting.model.Recebimento;
import com.ranchsorting.repository.Recebimentos;
import com.ranchsorting.util.jpa.Transactional;

public class CadastroRecebimentoService implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private Recebimentos recebimentos;

	@Transactional
	public Recebimento salvar(Recebimento recebimento) {

		try {
			return recebimentos.guardar(recebimento);

		} catch (ConstraintViolationException ex) {
			throw new NegocioException("Ocorreu algum promblema na gravação do recebimento."
					+ "Entre em contato com o administrador do Sistema. (ConstraintViolationException)");

		} catch (ArithmeticException ex) {
			throw new NegocioException("Ocorreu algum promblema na gravação do recebimento."
					+ "Entre em contato com o administrador do Sistema. (ArithmeticException)");

		} catch (RuntimeException ex) {
			throw new NegocioException("Ocorreu algum promblema na gravação do recebimento."
					+ "Entre em contato com o administrador do Sistema. (RuntimeException)");

		} catch (Exception ex) {
			throw new NegocioException("Ocorreu algum promblema na gravação do recebimento."
					+ "Entre em contato com o administrador do Sistema. (Exception)");

		}

	}
	
}
