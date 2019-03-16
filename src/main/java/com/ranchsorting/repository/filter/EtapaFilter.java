package com.ranchsorting.repository.filter;

import java.io.Serializable;
import java.util.Date;

public class EtapaFilter implements Serializable {

	private static final long serialVersionUID = 1L;

	private String nome;
	private String campeonato;
	private Date dataEventoInicial;
	private Date dataEventoFinal;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCampeonato() {
		return campeonato;
	}

	public void setCampeonato(String campeonato) {
		this.campeonato = campeonato;
	}

	public Date getDataEventoInicial() {
		return dataEventoInicial;
	}

	public void setDataEventoInicial(Date dataEventoInicial) {
		this.dataEventoInicial = dataEventoInicial;
	}

	public Date getDataEventoFinal() {
		return dataEventoFinal;
	}

	public void setDataEventoFinal(Date dataEventoFinal) {
		this.dataEventoFinal = dataEventoFinal;
	}

}