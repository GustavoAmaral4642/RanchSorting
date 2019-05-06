package com.ranchsorting.repository.filter;

import java.io.Serializable;
import java.sql.Date;

public class FichaInscricaoFilter implements Serializable {

	private static final long serialVersionUID = 1L;

	private String campeonato;
	private String etapa;
	private String divisao;
	private String competidor;
	private Date dataInscricaoInicial;
	private Date dataInscricaoFinal;

	public String getCampeonato() {
		return campeonato;
	}

	public void setCampeonato(String campeonato) {
		this.campeonato = campeonato;
	}

	public String getEtapa() {
		return etapa;
	}

	public void setEtapa(String etapa) {
		this.etapa = etapa;
	}

	public String getDivisao() {
		return divisao;
	}

	public void setDivisao(String divisao) {
		this.divisao = divisao;
	}

	public String getCompetidor() {
		return competidor;
	}

	public void setCompetidor(String competidor) {
		this.competidor = competidor;
	}

	public Date getDataInscricaoInicial() {
		return dataInscricaoInicial;
	}

	public void setDataInscricaoInicial(Date dataInscricaoInicial) {
		this.dataInscricaoInicial = dataInscricaoInicial;
	}

	public Date getDataInscricaoFinal() {
		return dataInscricaoFinal;
	}

	public void setDataInscricaoFinal(Date dataInscricaoFinal) {
		this.dataInscricaoFinal = dataInscricaoFinal;
	}

}