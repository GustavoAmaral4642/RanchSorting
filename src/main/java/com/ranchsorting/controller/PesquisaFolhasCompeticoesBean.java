package com.ranchsorting.controller;

import java.io.Serializable;     
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.ranchsorting.model.FolhaCompeticao;
import com.ranchsorting.repository.FolhasCompeticoes;
import com.ranchsorting.repository.filter.FolhaCompeticaoFilter;

@Named
@ViewScoped
public class PesquisaFolhasCompeticoesBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private FolhasCompeticoes folhas;
	
	private FolhaCompeticaoFilter filtro;
	
	private List<FolhaCompeticao> folhasFiltradas;
	
	public PesquisaFolhasCompeticoesBean(){
		filtro = new FolhaCompeticaoFilter();
	}
	
	public void pesquisar(){
		folhasFiltradas = folhas.filtradas(filtro);		
	}

	public FolhaCompeticaoFilter getFiltro() {
		return filtro;
	}

	public void setFiltro(FolhaCompeticaoFilter filtro) {
		this.filtro = filtro;
	}

	public List<FolhaCompeticao> getFolhasFiltradas() {
		return folhasFiltradas;
	}
	
}
