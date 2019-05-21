package com.ranchsorting.repository.filter;

import java.io.Serializable;
import java.sql.Date;

public class RecebimentoFilter implements Serializable {

	private static final long serialVersionUID = 1L;

	private String competidor;
	private String campeonato;
	private String etapa;
	private String divisao;
	private Date dataCadastroInicial;
	private Date dataCadastroFinal;
	private Date dataRecebimentoInicial;
	private Date dataRecebimentoFinal;

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

	public Date getDataCadastroInicial() {
		return dataCadastroInicial;
	}

	public void setDataCadastroInicial(Date dataCadastroInicial) {
		this.dataCadastroInicial = dataCadastroInicial;
	}

	public Date getDataCadastroFinal() {
		return dataCadastroFinal;
	}

	public void setDataCadastroFinal(Date dataCadastroFinal) {
		this.dataCadastroFinal = dataCadastroFinal;
	}

	public Date getDataRecebimentoInicial() {
		return dataRecebimentoInicial;
	}

	public void setDataRecebimentoInicial(Date dataRecebimentoInicial) {
		this.dataRecebimentoInicial = dataRecebimentoInicial;
	}

	public Date getDataRecebimentoFinal() {
		return dataRecebimentoFinal;
	}

	public void setDataRecebimentoFinal(Date dataRecebimentoFinal) {
		this.dataRecebimentoFinal = dataRecebimentoFinal;
	}

}