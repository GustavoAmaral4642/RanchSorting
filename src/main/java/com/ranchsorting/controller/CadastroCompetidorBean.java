package com.ranchsorting.controller;

import java.io.Serializable;

import javax.faces.bean.ViewScoped;
import javax.inject.Named;

import com.ranchsorting.model.Competidor;

@Named
@ViewScoped
public class CadastroCompetidorBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private Competidor competidor;

	public CadastroCompetidorBean() {

	}

	public void inicializar() {

	}

	public void limpar() {
		competidor = new Competidor();
	}

	public void salvar() {

	}

	public Competidor getCompetidor() {
		return competidor;
	}

	public void setCompetidor(Competidor competidor) {
		this.competidor = competidor;
	}

}
