package com.ranchsorting.repository.filter;

import java.io.Serializable;
import java.sql.Date;

public class TituloFilter implements Serializable {

	private static final long serialVersionUID = 1L;

	private String numeroTitulo;
	private String campeonato;
	private String etapa;
	private String competidor;
	private Date dataTituloInicial;
	private Date dataTituloFinal;

	public String getNumeroTitulo() {
		return numeroTitulo;
	}

	public void setNumeroTitulo(String numeroTitulo) {
		this.numeroTitulo = numeroTitulo;
	}

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

	public Date getDataTituloInicial() {
		return dataTituloInicial;
	}

	public void setDataTituloInicial(Date dataTituloInicial) {
		this.dataTituloInicial = dataTituloInicial;
	}

	public Date getDataTituloFinal() {
		return dataTituloFinal;
	}

	public void setDataTituloFinal(Date dataTituloFinal) {
		this.dataTituloFinal = dataTituloFinal;
	}

}