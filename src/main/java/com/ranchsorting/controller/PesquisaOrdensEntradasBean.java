package com.ranchsorting.controller;

import java.io.Serializable;      
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.ranchsorting.model.OrdemEntrada;
import com.ranchsorting.modellazy.LazyOrdemEntradaDataModel;
import com.ranchsorting.repository.OrdensEntradas;
import com.ranchsorting.repository.filter.OrdemEntradaFilter;

@Named
@ViewScoped
public class PesquisaOrdensEntradasBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private OrdensEntradas ordensEntradas;
	
	private OrdemEntradaFilter filtro;
	private String ordenar;
	private String tipoOrdenacao;
	
	private List<OrdemEntrada> ordensFiltradas;
	private LazyOrdemEntradaDataModel lazyOrdens;
	
	public PesquisaOrdensEntradasBean(){
		filtro = new OrdemEntradaFilter();
		ordenar = "ca.nome";
		tipoOrdenacao = "crescente";
	}
	
	public void pesquisar(){
		//ordensFiltradas = ordensEntradas.filtradas(filtro);	
		lazyOrdens = new LazyOrdemEntradaDataModel(ordensEntradas, filtro, ordenar, tipoOrdenacao);
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

	public LazyOrdemEntradaDataModel getLazyOrdens() {
		return lazyOrdens;
	}

}
