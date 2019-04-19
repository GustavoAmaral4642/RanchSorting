package com.ranchsorting.repository.filter;

import java.io.Serializable;
import java.sql.Date;

public class FolhaCompeticaoFilter implements Serializable {

	private static final long serialVersionUID = 1L;

	private String campeonato;
	private String etapa;
	private String competidor;
	private String divisao;
	private Date dataCompeticaoInicial;
	private Date dataCompeticaoFinal;

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

	public String getCompetidor() {
		return competidor;
	}

	public void setCompetidor(String competidor) {
		this.competidor = competidor;
	}

	public String getDivisao() {
		return divisao;
	}

	public void setDivisao(String divisao) {
		this.divisao = divisao;
	}

	public Date getDataCompeticaoInicial() {
		return dataCompeticaoInicial;
	}

	public void setDataCompeticaoInicial(Date dataCompeticaoInicial) {
		this.dataCompeticaoInicial = dataCompeticaoInicial;
	}

	public Date getDataCompeticaoFinal() {
		return dataCompeticaoFinal;
	}

	public void setDataCompeticaoFinal(Date dataCompeticaoFinal) {
		this.dataCompeticaoFinal = dataCompeticaoFinal;
	}

	
}