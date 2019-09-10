package com.ranchsorting.service;

import java.io.Serializable;
import java.util.List;

import com.ranchsorting.model.FichaInscricao;
import com.ranchsorting.model.OrdemEntrada;
import com.ranchsorting.model.Passada;
import com.ranchsorting.model.TipoCampeonato;
import com.ranchsorting.model.TipoFicha;

public class ManutencaoOrdemEntradaService implements Serializable{

	private static final long serialVersionUID = 1L;

	public OrdemEntrada sorteioOrdemEntrada(OrdemEntrada ordem, List<Passada> passadasCompetidores, List<FichaInscricao> fichasFiltradas) {

		// sorteio para prova de ranch sorting amador
		if (ordem.getCampeonato().getTipoCampeonato().equals(TipoCampeonato.RANCHSORTING)
				&& ordem.getDivisao().getTipoFicha().equals(TipoFicha.INDIVIDUAL)) {
			
			if (passadasCompetidores == null || passadasCompetidores.size() == 0) {
				throw new NegocioException("Os competidores não foram carregados. Ordem não será gerada!");
			}

		}
		
		return ordem;
	}

}
