package com.ranchsorting.controller;

import java.io.Serializable;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.ranchsorting.model.Competidor;
import com.ranchsorting.model.Etnia;
import com.ranchsorting.service.CadastroCompetidorService;
import com.ranchsorting.util.jsf.FacesUtil;

@Named
@RequestScoped
public class CadastroCompetidorBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private CadastroCompetidorService cadastroCompetidorService;
	
	private Competidor competidor;

	public CadastroCompetidorBean() {
		limpar();
	}

	public void inicializar() {
	
	}

	public void limpar() {
		competidor = new Competidor();
		competidor.setEtnia(Etnia.MASCULINO);
	}

	public void salvar() {
		
		this.competidor = cadastroCompetidorService.salvar(competidor);
		
		limpar();
		
		FacesUtil.addInfoMessage("Competidor salvo com sucesso!");
	}

	public Competidor getCompetidor() {
		return competidor;
	}

	public void setCompetidor(Competidor competidor) {
		this.competidor = competidor;
	}

}
