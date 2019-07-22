package com.ranchsorting.service;

import java.io.Serializable;  
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.validation.ConstraintViolationException;

import com.ranchsorting.model.FichaInscricao;
import com.ranchsorting.repository.FichasInscricoes;
import com.ranchsorting.util.jpa.Transactional;

public class CadastroFichaInscricaoService implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private FichasInscricoes fichas;
	
	@Transactional
	public FichaInscricao salvar(FichaInscricao ficha) {
		
		// Quantidade de fichas deve ser informada
		if(ficha.getQntFichas() == null || ficha.getQntFichas()==0){
			throw new NegocioException("Quantidade deve ser informada!");
		}
		
		// valor comprado deve ser informada
		if(ficha.getValorComprado().compareTo(BigDecimal.ZERO) == 0){
			throw new NegocioException("Valor comprado deve ser informado!");
		}
		
		
		List<FichaInscricao> pesquisaFichas = new ArrayList<>();
		
		pesquisaFichas = fichas.qntFichas(ficha);
		
		// se a quantidade de fichas incluídas somado com o que será incluído for maior que quinze, exception
		if((pesquisaFichas.size()+ficha.getQntFichas()) >15){
			throw new NegocioException("Competidor '" + ficha.getCompetidor().getNome()+ "' já possui " 
					+ pesquisaFichas.size() 
					+ " fichas compradas."
					+ " Ficha não será registrada.");
		}
		
		// se a quantidade de fichas já incluídas for maior que quinze, exception
		if(pesquisaFichas.size()>15){
			throw new NegocioException("Competidor '" + ficha.getCompetidor().getNome()+ "' já possui 15 fichas compradas."
					+ " Ficha não será registrada.");	
		}
		
		
		try {
			
			if(ficha.getId() != null ){
				
				//se não for edicao
				return fichas.guardar(ficha);
				
			} else if(ficha.getQntFichas()!= 0){
				// teste para fazer a gravação de mais de uma ficha.
				dividindoValorComprado(ficha);
				dividindoValorPago(ficha);
				
				for(int i=0; i < ficha.getQntFichas() ; i++){
					fichas.guardar(ficha);	
				}
				return ficha;
				
			} else {
				return fichas.guardar(ficha);	
			}			
			
		} catch (ConstraintViolationException ex) {
			throw new NegocioException("Ocorreu algum promblema na gravação da ficha de inscrição."
					+ "Entre em contato com o administrador do Sistema. (ConstraintViolationException)");

		} catch (ArithmeticException ex) {
			throw new NegocioException("Ocorreu algum promblema na gravação da ficha de inscrição."
					+ "Entre em contato com o administrador do Sistema. (ArithmeticException)");

		} catch (RuntimeException ex) {
			throw new NegocioException("Ocorreu algum promblema na gravação da ficha de inscrição."
					+ "Entre em contato com o administrador do Sistema. (RuntimeException)");

		} catch (Exception ex) {
			throw new NegocioException("Ocorreu algum promblema na gravação da ficha de inscrição."
					+ "Entre em contato com o administrador do Sistema. (Exception)");

		}
	}	
	
	private FichaInscricao dividindoValorComprado(FichaInscricao ficha) {

		// se não for edição, retorna a ficha sem calculo
		if (ficha.getId() != null) {
			return ficha;
		}

		// se o valor comprado não for zero ou nulo
		else if (ficha.getValorComprado() != null || ficha.getValorComprado() != BigDecimal.ZERO) {
			BigDecimal valorAux1 = BigDecimal.valueOf(ficha.getQntFichas());

			// divide o valor comprado pela quantidade de fichas compradas
			valorAux1 = ficha.getValorComprado().divide(valorAux1, 2);
			ficha.setValorComprado(valorAux1);

			return ficha;
		} else {
			return ficha;
		}
	}

	private FichaInscricao dividindoValorPago(FichaInscricao ficha) {

		// se não for edição, retorna a ficha sem calculo
		if (ficha.getId() != null) {
			return ficha;
		}
		// se o valor pago não for zero ou nulo
		else if (ficha.getValorPago() != null) {
			BigDecimal valorAux1 = BigDecimal.valueOf(ficha.getQntFichas());

			// divide o valor pago pela quantidade de fichas compradas
			valorAux1 = ficha.getValorPago().divide(valorAux1, 2);
			ficha.setValorPago(valorAux1);

			return ficha;
		} else {
			return ficha;
		}
	}

}