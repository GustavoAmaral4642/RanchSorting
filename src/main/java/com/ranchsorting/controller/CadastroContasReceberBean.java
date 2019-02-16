package com.ranchsorting.controller;

import java.io.Serializable;

import javax.faces.bean.ViewScoped;
import javax.inject.Named;

import com.ranchsorting.model.ContasReceber;

@Named
@ViewScoped
public class CadastroContasReceberBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private ContasReceber contasReceber;

	public CadastroContasReceberBean() {
		contasReceber = new ContasReceber();
	}

	public void inicializar() {
	
	}

	public void limpar() {
		contasReceber = new ContasReceber();
	}

	public void salvar() {
		
	}

	public ContasReceber getContasReceber() {
		return contasReceber;
	}

	public void setContasReceber(ContasReceber contasReceber) {
		this.contasReceber = contasReceber;
	}

}
