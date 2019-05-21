package com.ranchsorting.controller;

import java.io.Serializable;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.ranchsorting.model.Animal;
import com.ranchsorting.repository.Animais;
import com.ranchsorting.repository.filter.AnimalFilter;
import com.ranchsorting.util.jsf.FacesUtil;

@Named
@ViewScoped
public class PesquisaAnimaisBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private Animais animais;

	private AnimalFilter filtro;

	private List<Animal> animaisFiltrados;

	public PesquisaAnimaisBean() {
		filtro = new AnimalFilter();
	}

	public void pesquisar() {
		animaisFiltrados = animais.filtrados(filtro);
	}

	public void excluir(Animal animalSelecionado) {

		animais.remover(animalSelecionado);

		animaisFiltrados.remove(animalSelecionado);

		FacesUtil.addInfoMessage("Animal " + animalSelecionado.getNome() + " excluído com sucesso.");
	}

	public AnimalFilter getFiltro() {
		return filtro;
	}

	public void setFiltro(AnimalFilter filtro) {
		this.filtro = filtro;
	}

	public List<Animal> getAnimaisFiltrados() {
		return animaisFiltrados;
	}

}
