package com.ranchsorting.controller;

import java.io.Serializable;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.ranchsorting.model.Campeonato;
import com.ranchsorting.modellazy.LazyCampeonatoDataModel;
import com.ranchsorting.repository.Campeonatos;
import com.ranchsorting.repository.filter.CampeonatoFilter;
import com.ranchsorting.util.jsf.FacesUtil;

@Named
@ViewScoped
public class PesquisaCampeonatosBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private Campeonatos campeonatos;

	LazyCampeonatoDataModel lazyCampeonatos;

	private CampeonatoFilter filtro;
	private String ordenar;
	private String tipoOrdenacao;

	private List<Campeonato> campeonatosFiltrados;

	public PesquisaCampeonatosBean() {
		filtro = new CampeonatoFilter();
		ordenar = "nome";
		tipoOrdenacao = "crescente";
	}

	public void pesquisar() {
		lazyCampeonatos = new LazyCampeonatoDataModel(campeonatos, filtro, ordenar, tipoOrdenacao);
	}

	public void excluir(Campeonato campeonatoSelecionado) {

		campeonatos.remover(campeonatoSelecionado);

		campeonatosFiltrados.remove(campeonatoSelecionado);

		FacesUtil.addInfoMessage("Campeonato " + campeonatoSelecionado.getNome() + " excluído com sucesso.");
	}

	public CampeonatoFilter getFiltro() {
		return filtro;
	}

	public void setFiltro(CampeonatoFilter filtro) {
		this.filtro = filtro;
	}

	public List<Campeonato> getCampeonatosFiltrados() {
		return campeonatosFiltrados;
	}

	public LazyCampeonatoDataModel getLazyCampeonatos() {
		return lazyCampeonatos;
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
