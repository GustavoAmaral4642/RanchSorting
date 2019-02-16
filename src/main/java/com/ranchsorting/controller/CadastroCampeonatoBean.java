package com.ranchsorting.controller;

import java.io.Serializable;

import javax.faces.bean.ViewScoped;
import javax.inject.Named;

import com.ranchsorting.model.Campeonato;

@Named
@ViewScoped
public class CadastroCampeonatoBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private Campeonato campeonato;

	public CadastroCampeonatoBean() {
		campeonato = new Campeonato();
	}

	public void inicializar() {
	
	}

	public void limpar() {
		campeonato = new Campeonato();
	}

	public void salvar() {
		
	}

	public Campeonato getCampeonato() {
		return campeonato;
	}

	public void setCampeonato(Campeonato campeonato) {
		this.campeonato = campeonato;
	}

}
