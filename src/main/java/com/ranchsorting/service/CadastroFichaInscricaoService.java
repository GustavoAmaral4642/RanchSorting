package com.ranchsorting.service;

import java.io.Serializable; 
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.validation.ConstraintViolationException;

import com.ranchsorting.model.Competidor;
import com.ranchsorting.model.FichaInscricao;
import com.ranchsorting.model.Passada;
import com.ranchsorting.repository.FichasInscricoes;
import com.ranchsorting.util.jpa.Transactional;

public class CadastroFichaInscricaoService implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private FichasInscricoes fichas;
	
	@Transactional
	public FichaInscricao salvar(FichaInscricao ficha) {

		if (ficha.getPassadas().size() > 0) {

			// divide o valor comprado e pago
			ficha = dividindoValorComprado(ficha);
			ficha = dividindoValorPago(ficha);

			return fichas.guardar(ficha);
			
		} else {
			return ficha;
		}

/*		try {
			// teste para fazer a gravação de mais de uma ficha.
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
*/
	}

	private FichaInscricao dividindoValorComprado(FichaInscricao ficha) {

		// se não for edição, retorna a ficha sem calculo
		if (ficha.getId() != null) {
			return ficha;
		}

		// se o valor comprado não for zero ou nulo
		else if (ficha.getValorComprado() != null || ficha.getValorComprado() != BigDecimal.ZERO) {
			BigDecimal valorAux1 = BigDecimal.valueOf(ficha.getPassadas().size());

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
			BigDecimal valorAux1 = BigDecimal.valueOf(ficha.getPassadas().size());

			// divide o valor pago pela quantidade de fichas compradas
			valorAux1 = ficha.getValorPago().divide(valorAux1, 2);
			ficha.setValorPago(valorAux1);

			return ficha;
		} else {
			return ficha;
		}
	}

	public FichaInscricao preparaPassadas1(FichaInscricao ficha, Competidor comp1) {
		Passada pas1 = new Passada();
		
		pas1.getCompetidores().add(comp1);
		
		pas1.setCompetidor(comp1);
		pas1.setFichaInscricao(ficha);
		
		ficha.getPassadas().add(pas1);

		return ficha;
	}

	public FichaInscricao preparaPassadas2(FichaInscricao ficha, Competidor comp1, Competidor comp2) {

		/* está gravando passada e recebimento mais deuma vez
		 * 
		 * verificar
		 * 
		 * rtirar os comentários*/
		
		Passada pas1 = new Passada();
		Passada pas2 = new Passada();
		List<Passada> pass = new ArrayList<>();
		
		pas1.setCompetidor(comp1);
		pas1.setFichaInscricao(ficha);
		
		pas2.setCompetidor(comp2);
		pas2.setFichaInscricao(ficha);
		
		pass.add(pas1);
		pass.add(pas2);
		
		ficha.getPassadas().addAll(pass);

		return ficha;
	}

	public FichaInscricao preparaPassadas3(FichaInscricao ficha, Competidor comp1, Competidor comp2, Competidor comp3) {

		Passada pas1 = new Passada();
		Passada pas2 = new Passada();
		Passada pas3 = new Passada();

		pas1.setCompetidor(comp1);
		pas1.setFichaInscricao(ficha);
		
		pas2.setCompetidor(comp2);
		pas2.setFichaInscricao(ficha);
		
		pas3.setCompetidor(comp3);
		pas3.setFichaInscricao(ficha);
		
		ficha.getPassadas().add(pas1);
		ficha.getPassadas().add(pas2);
		ficha.getPassadas().add(pas3);

		return ficha;
	}
}