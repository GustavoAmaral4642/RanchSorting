package com.ranchsorting.controller;

import java.io.Serializable; 
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.ranchsorting.model.Anuidade;
import com.ranchsorting.model.Campeonato;
import com.ranchsorting.model.Competidor;
import com.ranchsorting.repository.Campeonatos;
import com.ranchsorting.repository.Competidores;
import com.ranchsorting.service.CadastroAnuidadeService;
import com.ranchsorting.util.jsf.FacesUtil;

@Named
@ViewScoped
public class CadastroAnuidadeBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private Competidores competidores;

	@Inject
	private Campeonatos campeonatos;
	
	@Inject
	private CadastroAnuidadeService cadastroAnuidadeService;

	private Anuidade anuidade;

	private List<Competidor> todosCompetidores;
	private List<Campeonato> todosCampeonatos;
	
	public CadastroAnuidadeBean() {
		limpar();
	}

	public void inicializar() {
		todosCompetidores = competidores.consultaCompetidoresParaCombobox();
		todosCampeonatos = campeonatos.todosCampeonatos();
	}

	public void limpar() {
		anuidade = new Anuidade();		
	}

	public void salvar() {
		
		this.anuidade = cadastroAnuidadeService.salvar(anuidade);
		
		limpar();
		
		FacesUtil.addInfoMessage("Anuidade registrada com sucesso!");
	}

	public Anuidade getAnuidade() {
		return anuidade;
	}

	public void setAnuidade(Anuidade anuidade) {
		this.anuidade = anuidade;
	}

	public List<Competidor> getTodosCompetidores() {
		return todosCompetidores;
	}

	public List<Campeonato> getTodosCampeonatos() {
		return todosCampeonatos;
	}

	public boolean isEditando(){
		return this.getAnuidade().getId() != null;
	}
	
}
