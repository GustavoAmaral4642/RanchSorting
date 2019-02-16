package com.ranchsorting.controller;

import java.io.Serializable;

import javax.faces.bean.ViewScoped;
import javax.inject.Named;

import com.ranchsorting.model.OrdemEntrada;

@Named
@ViewScoped
public class CadastroOrdemEntradaBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private OrdemEntrada ordemEntrada;

	public CadastroOrdemEntradaBean() {
		ordemEntrada = new OrdemEntrada();
	}

	public void inicializar() {
	
	}

	public void limpar() {
		ordemEntrada = new OrdemEntrada();
	}

	public void salvar() {
		
	}

	public OrdemEntrada getOrdemEntrada() {
		return ordemEntrada;
	}

	public void setOrdemEntrada(OrdemEntrada ordemEntrada) {
		this.ordemEntrada = ordemEntrada;
	}

}
