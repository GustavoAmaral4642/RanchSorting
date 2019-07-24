package com.ranchsorting.service;

import java.io.Serializable;

import javax.inject.Inject;
import javax.validation.ConstraintViolationException;

import com.ranchsorting.model.Competidor;
import com.ranchsorting.repository.Competidores;
import com.ranchsorting.util.jpa.Transactional;

public class CadastroCompetidorService implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private Competidores competidores;

	@Transactional
	public Competidor salvar(Competidor competidor) {

		Competidor competidorExistente = competidores.porNome(competidor.getNome());

		// Testa a inclusão e edição de registros
		if (competidorExistente != null && !competidorExistente.equals(competidor)) {

			throw new NegocioException("Já existe um competidor com este nome");
		}

		try {

			return competidores.guardar(competidor);

		} catch (ConstraintViolationException ex) {
			throw new NegocioException("Ocorreu algum promblema na gravação do competidor."
					+ "Entre em contato com o administrador do Sistema. (ConstraintViolationException)");

		} catch (ArithmeticException ex) {
			throw new NegocioException("Ocorreu algum promblema na gravação do competidor."
					+ "Entre em contato com o administrador do Sistema. (ArithmeticException)");

		} catch (RuntimeException ex) {
			throw new NegocioException("Ocorreu algum promblema na gravação do competidor."
					+ "Entre em contato com o administrador do Sistema. (RuntimeException)");

		} catch (Exception ex) {
			throw new NegocioException("Ocorreu algum promblema na gravação do competidor."
					+ "Entre em contato com o administrador do Sistema. (Exception)");

		}

	}
}
