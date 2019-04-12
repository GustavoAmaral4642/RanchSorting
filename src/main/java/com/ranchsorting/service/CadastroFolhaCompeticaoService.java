package com.ranchsorting.service;

import java.io.Serializable; 

import javax.inject.Inject;
import javax.validation.ConstraintViolationException;

import com.ranchsorting.model.FolhaCompeticao;
import com.ranchsorting.repository.FolhasCompeticoes;
import com.ranchsorting.util.jpa.Transactional;

public class CadastroFolhaCompeticaoService implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private FolhasCompeticoes folhas;

	@Transactional
	public FolhaCompeticao salvar(FolhaCompeticao folha) {

		try {
			return folhas.guardar(folha);
	

		} catch (ConstraintViolationException ex) {
			throw new NegocioException("Ocorreu algum promblema na gravação da Folha de Competiçao."
					+ "Entre em contato com o administrador do Sistema. (ConstraintViolationException)");
		} catch (ArithmeticException ex) {
			throw new NegocioException("Ocorreu algum promblema na gravação da Folha de Competiçao."
					+ "Entre em contato com o administrador do Sistema. (ArithmeticException)");
		} catch (RuntimeException ex) {
			throw new NegocioException("Ocorreu algum promblema na gravação da Folha de Competiçao."
					+ "Entre em contato com o administrador do Sistema. (RuntimeException)");
		} catch (Exception ex) {
			throw new NegocioException("Ocorreu algum promblema na gravação da Folha de Competiçao."
					+ "Entre em contato com o administrador do Sistema. (Exception)");
		}
	}
}
