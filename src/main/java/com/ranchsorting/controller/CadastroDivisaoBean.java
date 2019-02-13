package com.ranchsorting.controller;

import java.io.Serializable;

import javax.faces.bean.ViewScoped;
import javax.inject.Named;

import com.ranchsorting.model.Divisao;

@Named
@ViewScoped
public class CadastroDivisaoBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private Divisao divisao;

	public CadastroDivisaoBean() {
		divisao = new Divisao();
	}

	public void inicializar() {
	
	}

	public void limpar() {
		divisao = new Divisao();
	}

	public void salvar() {

	}
	
	public Divisao getDivisao() {
		return divisao;
	}

	public void setDivisao(Divisao divisao) {
		this.divisao = divisao;
	}

}
