package com.ranchsorting.repository.filter;

import java.io.Serializable;
import java.util.Date;

import com.ranchsorting.model.Animal;
import com.ranchsorting.model.Campeonato;
import com.ranchsorting.model.Competidor;
import com.ranchsorting.model.Divisao;
import com.ranchsorting.model.Etapa;

public class FolhaCompeticaoFilter implements Serializable {

	private static final long serialVersionUID = 1L;

	private Campeonato objCampeonato;
	private Etapa objEtapa;
	private Competidor objCompetidor1;
	private Competidor objCompetidor2;
	private String competidor;
	private Animal objAnimal1;
	private Animal objAnimal2;
	private Divisao objDivisao;
	private Date dataCompeticaoInicial;
	private Date dataCompeticaoFinal;

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

	public Competidor getObjCompetidor1() {
		return objCompetidor1;
	}

	public void setObjCompetidor1(Competidor objCompetidor1) {
		this.objCompetidor1 = objCompetidor1;
	}

	public Competidor getObjCompetidor2() {
		return objCompetidor2;
	}

	public void setObjCompetidor2(Competidor objCompetidor2) {
		this.objCompetidor2 = objCompetidor2;
	}

	public String getCompetidor() {
		return competidor;
	}

	public void setCompetidor(String competidor) {
		this.competidor = competidor;
	}

	public Animal getObjAnimal1() {
		return objAnimal1;
	}

	public void setObjAnimal1(Animal objAnimal1) {
		this.objAnimal1 = objAnimal1;
	}

	public Animal getObjAnimal2() {
		return objAnimal2;
	}

	public void setObjAnimal2(Animal objAnimal2) {
		this.objAnimal2 = objAnimal2;
	}

	public Divisao getObjDivisao() {
		return objDivisao;
	}

	public void setObjDivisao(Divisao objDivisao) {
		this.objDivisao = objDivisao;
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