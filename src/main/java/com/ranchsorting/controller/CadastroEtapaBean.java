package com.ranchsorting.controller;

import java.io.Serializable;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.ranchsorting.model.Campeonato;
import com.ranchsorting.model.Etapa;
import com.ranchsorting.repository.Campeonatos;
import com.ranchsorting.service.CadastroEtapaService;
import com.ranchsorting.util.jsf.FacesUtil;

@Named
@ViewScoped
public class CadastroEtapaBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private CadastroEtapaService cadastroEtapaService;

	@Inject
	private Campeonatos campeonatos;
	
	private List<Campeonato> todosCampeonatos;
	
	private Etapa etapa;

	public CadastroEtapaBean() {
		limpar();
	}

	public void inicializar() {
		todosCampeonatos = campeonatos.todosCampeonatos();
	}

	public void limpar() {
		etapa = new Etapa();
	}

	public void salvar() {
		this.etapa = cadastroEtapaService.salvar(etapa);
		
		limpar();
		
		FacesUtil.addInfoMessage("Etapa salva com sucesso!");
	}

	public Etapa getEtapa() {
		return etapa;
	}

	public void setEtapa(Etapa etapa) {
		this.etapa = etapa;
	}
	
	public List<Campeonato> getTodosCampeonatos() {
		return todosCampeonatos;
	}

	public boolean isEditando(){
		return this.getEtapa().getId() != null;
	}

}
