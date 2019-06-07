package com.ranchsorting.repository.filter;

import java.io.Serializable;
import java.sql.Date;

import com.ranchsorting.model.Campeonato;
import com.ranchsorting.model.Divisao;
import com.ranchsorting.model.Etapa;
import com.ranchsorting.model.StatusFicha;

public class PassadaFilter implements Serializable {

	private static final long serialVersionUID = 1L;

	private String campeonato;
	private String etapa;
	private String divisao;
	private String competidor;
	private Campeonato objCampeonato;
	private Etapa objEtapa;
	private Divisao objDivisao;
	private Date dataInscricaoInicial;
	private Date dataInscricaoFinal;
	private StatusFicha statusFicha;

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

	public Campeonato getObjCampeonato() {
		return objCampeonato;
	}

	public void setObjCampeonato(Campeonato objCampeonato) {
		this.objCampeonato = objCampeonato;
	}

	public Etapa getObjEtapa() {
		return objEtapa;
	}

	public void setObjEtapa(Etapa objEtapa) {
		this.objEtapa = objEtapa;
	}

	public Divisao getObjDivisao() {
		return objDivisao;
	}

	public void setObjDivisao(Divisao objDivisao) {
		this.objDivisao = objDivisao;
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

	public StatusFicha getStatusFicha() {
		return statusFicha;
	}

	public void setStatusFicha(StatusFicha statusFicha) {
		this.statusFicha = statusFicha;
	}

}