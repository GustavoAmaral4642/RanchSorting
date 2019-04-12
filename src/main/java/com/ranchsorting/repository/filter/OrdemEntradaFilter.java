package com.ranchsorting.repository.filter;

import java.io.Serializable;
import java.sql.Date;

import com.ranchsorting.model.Campeonato;
import com.ranchsorting.model.Divisao;
import com.ranchsorting.model.Etapa;

public class OrdemEntradaFilter implements Serializable {

	private static final long serialVersionUID = 1L;

	private Campeonato campeonato;
	private Etapa etapa;
	private Divisao divisao;
	private String competidor;
	private Date dataInscricaoInicial;
	private Date dataInscricaoFinal;
	private String nomeCampeonato;
	private String nomeEtapa;
	private String nomeDivisao;
	
	
	public Campeonato getCampeonato() {
		return campeonato;
	}

	public void setCampeonato(Campeonato campeonato) {
		this.campeonato = campeonato;
	}

	public Etapa getEtapa() {
		return etapa;
	}

	public void setEtapa(Etapa etapa) {
		this.etapa = etapa;
	}

	public Divisao getDivisao() {
		return divisao;
	}

	public void setDivisao(Divisao divisao) {
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

	public String getNomeCampeonato() {
		return nomeCampeonato;
	}

	public void setNomeCampeonato(String nomeCampeonato) {
		this.nomeCampeonato = nomeCampeonato;
	}

	public String getNomeEtapa() {
		return nomeEtapa;
	}

	public void setNomeEtapa(String nomeEtapa) {
		this.nomeEtapa = nomeEtapa;
	}

	public String getNomeDivisao() {
		return nomeDivisao;
	}

	public void setNomeDivisao(String nomeDivisao) {
		this.nomeDivisao = nomeDivisao;
	}

}