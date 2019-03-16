package com.ranchsorting.controller;

import java.io.Serializable;  

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.ranchsorting.model.Campeonato;
import com.ranchsorting.service.CadastroCampeonatoService;
import com.ranchsorting.util.jsf.FacesUtil;

@Named
@ViewScoped
public class CadastroCampeonatoBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private CadastroCampeonatoService cadastroCampeonatoService;

	private Campeonato campeonato;

	public CadastroCampeonatoBean() {
		limpar();
	}

	public void inicializar() {

	}

	public void limpar() {
		campeonato = new Campeonato();
	}

	public void salvar() {
		this.campeonato = cadastroCampeonatoService.salvar(campeonato);

		limpar();

		FacesUtil.addInfoMessage("Campeonato salvo com sucesso!");
	}

	public Campeonato getCampeonato() {
		return campeonato;
	}

	public void setCampeonato(Campeonato campeonato) {
		this.campeonato = campeonato;
	}

	public boolean isEditando() {
		return this.getCampeonato().getId() != null;
	}

}
