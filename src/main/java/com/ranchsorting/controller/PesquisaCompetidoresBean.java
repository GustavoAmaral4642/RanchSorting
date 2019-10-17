package com.ranchsorting.controller;

import java.io.Serializable;  
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.ranchsorting.model.Competidor;
import com.ranchsorting.modellazy.LazyCompetidorDataModel;
import com.ranchsorting.repository.Competidores;
import com.ranchsorting.repository.filter.CompetidorFilter;
import com.ranchsorting.util.jsf.FacesUtil;

@Named
@ViewScoped
public class PesquisaCompetidoresBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private Competidores competidores;
	
	LazyCompetidorDataModel lazyCompetidores;
	
	private CompetidorFilter filtro;
	
	private List<Competidor> competidoresFiltrados;
	
	public PesquisaCompetidoresBean(){
		filtro = new CompetidorFilter();
	}
	
	public void pesquisar(){
		lazyCompetidores = new LazyCompetidorDataModel(competidores, filtro);
		
	}

	public void excluir(Competidor competidorSelecionado) {

		competidores.remover(competidorSelecionado);

		competidoresFiltrados.remove(competidorSelecionado);

		FacesUtil.addInfoMessage("Competidor " + competidorSelecionado.getNome() + " excluído com sucesso.");
	}

	public CompetidorFilter getFiltro() {
		return filtro;
	}

	public void setFiltro(CompetidorFilter filtro) {
		this.filtro = filtro;
	}

	public List<Competidor> getCompetidoresFiltrados() {
		return competidoresFiltrados;
	}

	public LazyCompetidorDataModel getLazyCompetidores() {
		return lazyCompetidores;
	}
	
}
