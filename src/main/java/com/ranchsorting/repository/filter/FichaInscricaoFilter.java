package com.ranchsorting.repository.filter;

import java.io.Serializable;
import java.sql.Date;

public class FichaInscricaoFilter implements Serializable {

	private static final long serialVersionUID = 1L;

	private String campeonato;
	private String etapa;
	private String competidor;
	private String animal;
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

	public String getCompetidor() {
		return competidor;
	}

	public void setCompetidor(String competidor) {
		this.competidor = competidor;
	}

	public String getAnimal() {
		return animal;
	}

	public void setAnimal(String animal) {
		this.animal = animal;
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