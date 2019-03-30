package com.ranchsorting.controller;

import java.io.Serializable;    
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.ranchsorting.model.Titulo;
import com.ranchsorting.repository.Titulos;
import com.ranchsorting.repository.filter.TituloFilter;


@Named
@ViewScoped
public class PesquisaTitulosBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private Titulos titulos;
	
	private TituloFilter filtro;
	
	private List<Titulo> titulosFiltrados;
	
	public PesquisaTitulosBean(){
		filtro = new TituloFilter();
	}
	
	public void pesquisar(){
		titulosFiltrados = titulos.filtrados(filtro);
	}

	public TituloFilter getFiltro() {
		return filtro;
	}

	public void setFiltro(TituloFilter filtro) {
		this.filtro = filtro;
	}

	public List<Titulo> getTitulosFiltrados() {
		return titulosFiltrados;
	}
	
}
