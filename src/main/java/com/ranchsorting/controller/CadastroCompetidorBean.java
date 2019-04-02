package com.ranchsorting.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.ranchsorting.model.Animal;
import com.ranchsorting.model.Competidor;
import com.ranchsorting.model.Etnia;
import com.ranchsorting.repository.Animais;
import com.ranchsorting.service.CadastroCompetidorService;
import com.ranchsorting.util.jsf.FacesUtil;

@Named
@ViewScoped
public class CadastroCompetidorBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private CadastroCompetidorService cadastroCompetidorService;
	
	@Inject
	private Animais animais;
	
	private List<Animal> animaisCompetidor;
	
	private Competidor competidor;

	
	public CadastroCompetidorBean() {
		limpar();
	}

	public void inicializar() {
		if (FacesUtil.isNotPostback()){
			if (isEditando()) {
				carregarAnimaisCompetidor();
			}
		}
	}
	
	public void limpar() {
		competidor = new Competidor();
		competidor.setEtnia(Etnia.MASCULINO);
		animaisCompetidor = new ArrayList<>();		
	}

	public void carregarAnimaisCompetidor() {
		animaisCompetidor = animais.animaisCompetidor(competidor);
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
	
	public List<Animal> getAnimaisCompetidor() {
		return animaisCompetidor;
	}

	public boolean isEditando() {
		return this.competidor.getId() != null;
	}
}
