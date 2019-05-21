package com.ranchsorting.service;

import java.io.Serializable;

import javax.inject.Inject;
import javax.validation.ConstraintViolationException;

import com.ranchsorting.model.FolhaCompeticao;
import com.ranchsorting.model.StatusFicha;
import com.ranchsorting.model.TipoFolha;
import com.ranchsorting.repository.FolhasCompeticoes;
import com.ranchsorting.util.jpa.Transactional;

public class CadastroFolhaAutomaticaService implements Serializable{

	private static final long serialVersionUID = 1L;

	@Inject
	private FolhasCompeticoes folhas;

	@Transactional
	public FolhaCompeticao salvar(FolhaCompeticao folha) {
		
		/*if(folha.getFichaInscricao1().getCompetidor().getId() == folha.getFichaInscricao2().getCompetidor().getId()){
			throw new NegocioException("Os competidores devem ser diferentes!");
		}*/
		
		if(folha.getFichaInscricao1() == null){
			throw new NegocioException("Por favor, selecione a ficha de inscrição número 1!");
		}
		
		if(folha.getFichaInscricao2() == null){
			throw new NegocioException("Por favor, selecione a ficha de inscrição número 2!");
		}
		
		try {
		
			folha.getFichaInscricao1().setStatusFicha(StatusFicha.EMORDEM);
			folha.getFichaInscricao2().setStatusFicha(StatusFicha.EMORDEM);
			folha.setTipoFolha(TipoFolha.AUTOMATICA);
			
			return folhas.guardar(folha);
			
		} catch (NullPointerException ex) {
			throw new NegocioException("Ocorreu algum promblema na gravação da Folha de Competiçao."
					+ "Entre em contato com o administrador do Sistema. (NullPointerException)");
		}
		catch (ConstraintViolationException ex) {
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
