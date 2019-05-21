package com.ranchsorting.controller;

import java.io.Serializable; 
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.ranchsorting.model.Recebimento;
import com.ranchsorting.repository.Recebimentos;
import com.ranchsorting.repository.filter.RecebimentoFilter;
import com.ranchsorting.util.jsf.FacesUtil;

@Named
@ViewScoped
public class PesquisaRecebimentosBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private Recebimentos recebimentos;

	private RecebimentoFilter filtro;

	private List<Recebimento> recebimentosFiltrados;

	public PesquisaRecebimentosBean() {
		filtro = new RecebimentoFilter();
	}

	public void pesquisar() {
		recebimentosFiltrados = recebimentos.filtrados(filtro);
	}

	public void excluir(Recebimento recebimentoSelecionado) {

		recebimentos.remover(recebimentoSelecionado);

		recebimentosFiltrados.remove(recebimentoSelecionado);

		FacesUtil.addInfoMessage("Título do competidor " + recebimentoSelecionado.getCompetidor().getNome() + " excluído com sucesso.");
	}

	public RecebimentoFilter getFiltro() {
		return filtro;
	}

	public void setFiltro(RecebimentoFilter filtro) {
		this.filtro = filtro;
	}

	public List<Recebimento> getRecebimentosFiltrados() {
		return recebimentosFiltrados;
	}

	public void setRecebimentosFiltrados(List<Recebimento> recebimentosFiltrados) {
		this.recebimentosFiltrados = recebimentosFiltrados;
	}

}
