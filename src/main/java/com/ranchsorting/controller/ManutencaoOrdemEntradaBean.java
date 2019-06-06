package com.ranchsorting.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.ranchsorting.model.Campeonato;
import com.ranchsorting.model.Divisao;
import com.ranchsorting.model.Etapa;
import com.ranchsorting.model.FichaInscricao;
import com.ranchsorting.model.OrdemEntrada;
import com.ranchsorting.repository.Campeonatos;
import com.ranchsorting.repository.Divisoes;
import com.ranchsorting.repository.Etapas;
import com.ranchsorting.repository.FichasInscricoes;
import com.ranchsorting.repository.filter.FichaInscricaoFilter;
import com.ranchsorting.repository.filter.FolhaCompeticaoFilter;
import com.ranchsorting.service.CadastroOrdemEntradaService;
import com.ranchsorting.util.jsf.FacesUtil;

@Named
@ViewScoped
public class ManutencaoOrdemEntradaBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private CadastroOrdemEntradaService cadastroOrdemEntradaService;

	private OrdemEntrada ordemEntrada;

	public ManutencaoOrdemEntradaBean() {
		limpar();
	}

	public void inicializar() {
		
	}

	public void limpar() {
		ordemEntrada = new OrdemEntrada();
	}

	public void salvar() {

	
		FacesUtil.addInfoMessage("Ordem de entrada registrada com sucesso!");

	}

	public OrdemEntrada getOrdemEntrada() {
		return ordemEntrada;
	}

	public void setOrdemEntrada(OrdemEntrada ordemEntrada) {
		this.ordemEntrada = ordemEntrada;
	}

	public boolean isEditando() {
		return this.ordemEntrada.getId() != null;
	}

}
