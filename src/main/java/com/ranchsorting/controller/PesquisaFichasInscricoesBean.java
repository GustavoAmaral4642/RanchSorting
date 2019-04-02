package com.ranchsorting.controller;

import java.io.Serializable;    
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.ranchsorting.model.FichaInscricao;
import com.ranchsorting.repository.FichasInscricoes;
import com.ranchsorting.repository.filter.FichaInscricaoFilter;


@Named
@ViewScoped
public class PesquisaFichasInscricoesBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private FichasInscricoes fichas;
	
	private FichaInscricaoFilter filtro;
	
	private List<FichaInscricao> fichasFiltradas;
	
	public PesquisaFichasInscricoesBean(){
		filtro = new FichaInscricaoFilter();
	}
	
	public void pesquisar(){
		fichasFiltradas = fichas.filtradas(filtro);		
	}

	public FichaInscricaoFilter getFiltro() {
		return filtro;
	}

	public void setFiltro(FichaInscricaoFilter filtro) {
		this.filtro = filtro;
	}

	public List<FichaInscricao> getFichasFiltradas() {
		return fichasFiltradas;
	}
	
}
