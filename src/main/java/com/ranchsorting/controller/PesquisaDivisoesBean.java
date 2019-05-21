package com.ranchsorting.controller;

import java.io.Serializable;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.ranchsorting.model.Divisao;
import com.ranchsorting.repository.Divisoes;
import com.ranchsorting.repository.filter.DivisaoFilter;
import com.ranchsorting.util.jsf.FacesUtil;

@Named
@ViewScoped
public class PesquisaDivisoesBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private Divisoes divisoes;

	private DivisaoFilter filtro;

	private List<Divisao> divisoesFiltrados;

	public PesquisaDivisoesBean() {
		filtro = new DivisaoFilter();
	}

	public void pesquisar() {
		divisoesFiltrados = divisoes.filtrados(filtro);
	}

	public void excluir(Divisao divisaoSelecionada) {

		divisoes.remover(divisaoSelecionada);

		divisoesFiltrados.remove(divisaoSelecionada);

		FacesUtil.addInfoMessage("Divisão " + divisaoSelecionada.getNome() + " excluída com sucesso.");
	}

	public DivisaoFilter getFiltro() {
		return filtro;
	}

	public void setFiltro(DivisaoFilter filtro) {
		this.filtro = filtro;
	}

	public List<Divisao> getDivisoesFiltrados() {
		return divisoesFiltrados;
	}

}
