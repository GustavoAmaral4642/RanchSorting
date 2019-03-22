package com.ranchsorting.controller;

import java.io.Serializable;  
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.ranchsorting.model.Anuidade;
import com.ranchsorting.repository.Anuidades;
import com.ranchsorting.repository.filter.AnuidadeFilter;

@Named
@ViewScoped
public class PesquisaAnuidadesBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private Anuidades anuidades;
	
	private AnuidadeFilter filtro;
	
	private List<Anuidade> anuidadesFiltradas;
	
	public PesquisaAnuidadesBean(){
		filtro = new AnuidadeFilter();
	}
	
	public void pesquisar(){
		anuidadesFiltradas = anuidades.filtrados(filtro);		
	}

	public AnuidadeFilter getFiltro() {
		return filtro;
	}

	public void setFiltro(AnuidadeFilter filtro) {
		this.filtro = filtro;
	}

	public List<Anuidade> getAnuidadesFiltradas() {
		return anuidadesFiltradas;
	}
	
}
