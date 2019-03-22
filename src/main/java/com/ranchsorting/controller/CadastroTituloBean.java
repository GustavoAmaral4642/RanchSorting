package com.ranchsorting.controller;

import java.io.Serializable;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.ranchsorting.model.Titulo;
import com.ranchsorting.repository.Campeonatos;
import com.ranchsorting.repository.Competidores;
import com.ranchsorting.repository.Divisoes;
import com.ranchsorting.repository.Etapas;
import com.ranchsorting.service.CadastroTituloService;
import com.ranchsorting.util.jsf.FacesUtil;
import com.ranchsorting.model.Campeonato;
import com.ranchsorting.model.Competidor;
import com.ranchsorting.model.Divisao;
import com.ranchsorting.model.Etapa;
import com.ranchsorting.model.Recebimento;

@Named
@ViewScoped
public class CadastroTituloBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private Campeonatos campeonatos;

	@Inject
	private Etapas etapas;

	@Inject
	private Divisoes divisoes;

	@Inject
	private Competidores competidores;

	@Inject
	private CadastroTituloService cadastroTituloService;

	private Titulo titulo;
	private Recebimento recebimento;

	private List<Competidor> todosCompetidores;
	private List<Campeonato> todosCampeonatos;
	private List<Etapa> etapasCampeonatos;
	private List<Divisao> todasDivisoes;

	public CadastroTituloBean() {
		limpar();
	}

	public void inicializar() {
		todosCompetidores = competidores.todosCompetidores();
		todosCampeonatos = campeonatos.todosCampeonatos();
		todasDivisoes = divisoes.todasDivisoes();
	}

	public void limpar() {
		titulo = new Titulo();
		recebimento = new Recebimento();
	}

	public void salvar() {
		this.titulo = cadastroTituloService.salvar(titulo);

		limpar();

		FacesUtil.addInfoMessage("Título salvo com sucesso!");

	}

	public void carregarEtapas() {
		etapasCampeonatos = etapas.etapasDoCampeonato(titulo.getCampeonato());
	}

	public Titulo getTitulo() {
		return titulo;
	}

	public void setTitulo(Titulo titulo) {
		this.titulo = titulo;
	}

	public Recebimento getRecebimento() {
		return recebimento;
	}

	public void setRecebimento(Recebimento recebimento) {
		this.recebimento = recebimento;
	}
	
	public List<Competidor> getTodosCompetidores() {
		return todosCompetidores;
	}
	
	public List<Campeonato> getTodosCampeonatos() {
		return todosCampeonatos;
	}

	public List<Etapa> getEtapasCampeonatos() {
		return etapasCampeonatos;
	}

	public List<Divisao> getTodasDivisoes() {
		return todasDivisoes;
	}

	public boolean isEditando() {
		return this.titulo.getId() != null;
	}

}
