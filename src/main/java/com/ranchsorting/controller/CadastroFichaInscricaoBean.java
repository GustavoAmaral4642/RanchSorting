package com.ranchsorting.controller;

import java.io.Serializable;

import javax.faces.bean.ViewScoped;
import javax.inject.Named;

import com.ranchsorting.model.FichaInscricao;

@Named
@ViewScoped
public class CadastroFichaInscricaoBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private FichaInscricao fichaInscricao;

	public CadastroFichaInscricaoBean() {
		fichaInscricao = new FichaInscricao();
	}

	public void inicializar() {
	
	}

	public void limpar() {
		fichaInscricao = new FichaInscricao();
	}

	public void salvar() {
		
	}

	public FichaInscricao getFichaInscricao() {
		return fichaInscricao;
	}

	public void setFichaInscricao(FichaInscricao fichaInscricao) {
		this.fichaInscricao = fichaInscricao;
	}

}
