package com.ranchsorting.repository.filter;

import java.io.Serializable;
import java.sql.Date;

import com.ranchsorting.model.Campeonato;
import com.ranchsorting.model.Divisao;
import com.ranchsorting.model.Etapa;
import com.ranchsorting.model.OrdemEntrada;
import com.ranchsorting.model.StatusFicha;

public class PassadaFilter implements Serializable {

	private static final long serialVersionUID = 1L;

	private OrdemEntrada ordemEntrada;

	public OrdemEntrada getOrdemEntrada() {
		return ordemEntrada;
	}

	public void setOrdemEntrada(OrdemEntrada ordemEntrada) {
		this.ordemEntrada = ordemEntrada;
	}

}