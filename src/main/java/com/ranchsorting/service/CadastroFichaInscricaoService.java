package com.ranchsorting.service;

import java.io.Serializable;
import java.math.BigDecimal;

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

		
			/*// teste para fazer a gravação de mais de uma ficha.
			if (ficha.getQntInscricoes() > 0) {

				// divide o valor comprado e pago
				ficha = dividindoValorComprado(ficha);
				ficha = dividindoValorPago(ficha);

				// atençao nesse for.
				for (int i = 1; i < ficha.getQntInscricoes(); i++) {
					fichas.guardar(ficha);
				}
				// o ultimo looping cai aqui
				return fichas.guardar(ficha);
			} else {
				
			}*/
			return fichas.guardar(ficha);

		}  
	}
/*
	private FichaInscricao dividindoValorComprado(FichaInscricao ficha) {

		// se não for edição, retorna a ficha sem calculo
		if (ficha.getId() != null) {
			return ficha;
		}
		// se o valor comprado não for zero ou nulo
		else if (ficha.getValorComprado() != null || ficha.getValorComprado() != BigDecimal.ZERO) {
			BigDecimal valorAux1 = BigDecimal.valueOf(ficha.getQntInscricoes());

			//divide o valor comprado pela quantidade de fichas compradas
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
			BigDecimal valorAux1 = BigDecimal.valueOf(ficha.getQntInscricoes());

			// divide o valor pago pela quantidade de fichas compradas
			valorAux1 = ficha.getValorPago().divide(valorAux1, 2);
			ficha.setValorPago(valorAux1);
			return ficha;
		} else {
			return ficha;
		}
	}
*/

