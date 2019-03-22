package com.ranchsorting.controller;

import java.io.Serializable;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.ranchsorting.model.Divisao;
import com.ranchsorting.service.CadastroDivisaoService;
import com.ranchsorting.util.jsf.FacesUtil;

@Named
@ViewScoped
public class CadastroDivisaoBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private CadastroDivisaoService cadastroDivisaoService;

	private Divisao divisao;

	public CadastroDivisaoBean() {
		limpar();
	}

	public void inicializar() {

	}

	public void limpar() {
		divisao = new Divisao();
	}

	public void salvar() {
		this.divisao = cadastroDivisaoService.salvar(divisao);

		limpar();

		FacesUtil.addInfoMessage("Divisão salva com sucesso!");

	}

	public Divisao getDivisao() {
		return divisao;
	}

	public void setDivisao(Divisao divisao) {
		this.divisao = divisao;
	}

	public boolean isEditando() {
		return this.divisao.getId() != null;
	}

}
