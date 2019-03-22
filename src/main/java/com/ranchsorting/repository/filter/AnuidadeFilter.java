package com.ranchsorting.repository.filter;

import java.io.Serializable;
import java.sql.Date;

public class AnuidadeFilter implements Serializable {

	private static final long serialVersionUID = 1L;

	private String competidor;
	private String campeonato;
	private Date dataPagamentoInicial;
	private Date dataPagamentoFinal;

	public String getCompetidor() {
		return competidor;
	}

	public void setCompetidor(String competidor) {
		this.competidor = competidor;
	}

	public String getCampeonato() {
		return campeonato;
	}

	public void setCampeonato(String campeonato) {
		this.campeonato = campeonato;
	}

	public Date getDataPagamentoInicial() {
		return dataPagamentoInicial;
	}

	public void setDataPagamentoInicial(Date dataPagamentoInicial) {
		this.dataPagamentoInicial = dataPagamentoInicial;
	}

	public Date getDataPagamentoFinal() {
		return dataPagamentoFinal;
	}

	public void setDataPagamentoFinal(Date dataPagamentoFinal) {
		this.dataPagamentoFinal = dataPagamentoFinal;
	}

}