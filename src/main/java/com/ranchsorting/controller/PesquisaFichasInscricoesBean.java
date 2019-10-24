package com.ranchsorting.controller;

import java.io.Serializable;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.ranchsorting.model.FichaInscricao;
import com.ranchsorting.modellazy.LazyFichaInscricaoDataModel;
import com.ranchsorting.repository.FichasInscricoes;
import com.ranchsorting.repository.filter.FichaInscricaoFilter;

@Named
@ViewScoped
public class PesquisaFichasInscricoesBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private FichasInscricoes fichas;

	LazyFichaInscricaoDataModel lazyFichasInscricoes;

	private FichaInscricaoFilter filtro;
	private String ordenar;
	private String tipoOrdenacao;

	private List<FichaInscricao> fichasFiltradas;

	public PesquisaFichasInscricoesBean() {
		filtro = new FichaInscricaoFilter();
		ordenar = "id";
		tipoOrdenacao = "crescente";
	}

	public void pesquisar() {
		lazyFichasInscricoes = new LazyFichaInscricaoDataModel(fichas, filtro, ordenar, tipoOrdenacao);
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

	public LazyFichaInscricaoDataModel getLazyFichasInscricoes() {
		return lazyFichasInscricoes;
	}

	public String getOrdenar() {
		return ordenar;
	}

	public void setOrdenar(String ordenar) {
		this.ordenar = ordenar;
	}

	public String getTipoOrdenacao() {
		return tipoOrdenacao;
	}

	public void setTipoOrdenacao(String tipoOrdenacao) {
		this.tipoOrdenacao = tipoOrdenacao;
	}

}
