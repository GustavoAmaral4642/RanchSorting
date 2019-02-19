package com.ranchsorting.controller;

import java.io.Serializable; 

import javax.faces.bean.ViewScoped;
import javax.inject.Named;

import com.ranchsorting.model.Etapa;

@Named
@ViewScoped
public class CadastroEtapaBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private Etapa etapa;

	public CadastroEtapaBean() {
		etapa = new Etapa();
	}

	public void inicializar() {
	
	}

	public void limpar() {
		etapa = new Etapa();
	}

	public void salvar() {

	}

	public Etapa getEtapa() {
		return etapa;
	}

	public void setEtapa(Etapa etapa) {
		this.etapa = etapa;
	}

}
