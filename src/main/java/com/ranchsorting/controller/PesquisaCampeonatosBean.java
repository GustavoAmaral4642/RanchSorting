package com.ranchsorting.controller;

import java.io.Serializable;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.ranchsorting.model.Campeonato;
import com.ranchsorting.repository.Campeonatos;
import com.ranchsorting.repository.filter.CampeonatoFilter;
import com.ranchsorting.util.jsf.FacesUtil;

@Named
@ViewScoped
public class PesquisaCampeonatosBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private Campeonatos campeonatos;

	private CampeonatoFilter filtro;

	private List<Campeonato> campeonatosFiltrados;

	public PesquisaCampeonatosBean() {
		filtro = new CampeonatoFilter();
	}

	public void pesquisar() {
		campeonatosFiltrados = campeonatos.filtrados(filtro);
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

}
