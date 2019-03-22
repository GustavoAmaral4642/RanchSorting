package com.ranchsorting.controller;

import java.io.Serializable;   
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.ranchsorting.model.Etapa;
import com.ranchsorting.repository.Etapas;
import com.ranchsorting.repository.filter.EtapaFilter;

@Named
@ViewScoped
public class PesquisaEtapasBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private Etapas etapas;
	
	private EtapaFilter filtro;
	
	private List<Etapa> etapasFiltradas;
	
	public PesquisaEtapasBean(){
		filtro = new EtapaFilter();
	}
	
	public void pesquisar(){
		etapasFiltradas = etapas.filtradas(filtro);		
	}

	public EtapaFilter getFiltro() {
		return filtro;
	}

	public void setFiltro(EtapaFilter filtro) {
		this.filtro = filtro;
	}

	public List<Etapa> getEtapasFiltradas() {
		return etapasFiltradas;
	}
	
}