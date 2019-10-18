package com.ranchsorting.controller;

import java.io.Serializable;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.ranchsorting.model.Etapa;
import com.ranchsorting.modellazy.LazyEtapaDataModel;
import com.ranchsorting.repository.Etapas;
import com.ranchsorting.repository.filter.EtapaFilter;
import com.ranchsorting.util.jsf.FacesUtil;

@Named
@ViewScoped
public class PesquisaEtapasBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private Etapas etapas;

	private EtapaFilter filtro;
	private String ordenar;
	private String tipoOrdenacao;
	
	private List<Etapa> etapasFiltradas;
	private LazyEtapaDataModel lazyEtapas;
	
	public PesquisaEtapasBean() {
		filtro = new EtapaFilter();
		ordenar = "nome";
		tipoOrdenacao = "crescente";
	}

	public void pesquisar() {
		//etapasFiltradas = etapas.filtradas(filtro);
		lazyEtapas = new LazyEtapaDataModel(etapas, filtro, ordenar, tipoOrdenacao);
	}

	public void excluir(Etapa etapaSelecionada) {

		etapas.remover(etapaSelecionada);

		etapasFiltradas.remove(etapaSelecionada);

		FacesUtil.addInfoMessage("Etapa " + etapaSelecionada.getNome() + " excluída com sucesso.");
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

	public LazyEtapaDataModel getLazyEtapas() {
		return lazyEtapas;
	}

}
