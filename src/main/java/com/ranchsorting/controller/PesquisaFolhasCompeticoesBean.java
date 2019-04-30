package com.ranchsorting.controller;

import java.io.Serializable;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.ranchsorting.model.Campeonato;
import com.ranchsorting.model.Divisao;
import com.ranchsorting.model.Etapa;
import com.ranchsorting.model.FolhaCompeticao;
import com.ranchsorting.repository.Campeonatos;
import com.ranchsorting.repository.Divisoes;
import com.ranchsorting.repository.Etapas;
import com.ranchsorting.repository.FolhasCompeticoes;
import com.ranchsorting.repository.filter.FolhaCompeticaoFilter;

@Named
@ViewScoped
public class PesquisaFolhasCompeticoesBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private FolhasCompeticoes folhas;

	@Inject
	private Campeonatos campeonatos;

	@Inject
	private Etapas etapas;

	@Inject
	private Divisoes divisoes;

	private FolhaCompeticaoFilter filtro;

	private List<Campeonato> todosCampeonatos;
	private List<Etapa> todasEtapas;
	private List<Divisao> todasDivisoes;
	private List<FolhaCompeticao> folhasFiltradas;

	public PesquisaFolhasCompeticoesBean() {
		filtro = new FolhaCompeticaoFilter();
	}

	public void inicializar(){
		todosCampeonatos = campeonatos.todosCampeonatos();
		todasEtapas = etapas.todasEtapas();
		todasDivisoes = divisoes.todasDivisoes();
	}
	
	public void pesquisar() {
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

	public List<Campeonato> getTodosCampeonatos() {
		return todosCampeonatos;
	}

	public List<Etapa> getTodasEtapas() {
		return todasEtapas;
	}

	public List<Divisao> getTodasDivisoes() {
		return todasDivisoes;
	}

}
