package com.ranchsorting.controller;

import java.io.Serializable;      
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.ranchsorting.model.Campeonato;
import com.ranchsorting.model.OrdemEntrada;
import com.ranchsorting.repository.OrdensEntradas;
import com.ranchsorting.repository.filter.OrdemEntradaFilter;

@Named
@ViewScoped
public class PesquisaOrdensEntradasBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private OrdensEntradas ordensEntradas;
	
	private OrdemEntradaFilter filtro;
	
	private List<OrdemEntrada> ordensFiltradas;
	
	public PesquisaOrdensEntradasBean(){
		filtro = new OrdemEntradaFilter();
	}
	
	public void pesquisar(){
		ordensFiltradas = ordensEntradas.filtradas(filtro);		
	}

	public OrdemEntradaFilter getFiltro() {
		return filtro;
	}

	public void setFiltro(OrdemEntradaFilter filtro) {
		this.filtro = filtro;
	}

	public List<OrdemEntrada> getOrdensFiltradas() {
		return ordensFiltradas;
	}

}
