package com.ranchsorting.repository.filter;

import java.io.Serializable;
import java.util.Date;

public class CampeonatoFilter implements Serializable {

	private static final long serialVersionUID = 1L;

	private String nome;
	private Date dataAberturaInicial;
	private Date dataAberturaFinal;
	private Date dataTerminoInicial;
	private Date dataTerminoFinal;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Date getDataAberturaInicial() {
		return dataAberturaInicial;
	}

	public void setDataAberturaInicial(Date dataAberturaInicial) {
		this.dataAberturaInicial = dataAberturaInicial;
	}

	public Date getDataAberturaFinal() {
		return dataAberturaFinal;
	}

	public void setDataAberturaFinal(Date dataAberturaFinal) {
		this.dataAberturaFinal = dataAberturaFinal;
	}

	public Date getDataTerminoInicial() {
		return dataTerminoInicial;
	}

	public void setDataTerminoInicial(Date dataEventoInicial) {
		this.dataTerminoInicial = dataEventoInicial;
	}

	public Date getDataTerminoFinal() {
		return dataTerminoFinal;
	}

	public void setDataTerminoFinal(Date dataEventoFinal) {
		this.dataTerminoFinal = dataEventoFinal;
	}

}