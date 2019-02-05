package com.ranchsorting.controller;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.ranchsorting.model.Tarefa;
import com.ranchsorting.repository.Tarefas;
import com.ranchsorting.util.jsf.FacesUtil;

@Named
@ViewScoped
public class PermissoesUsuariosBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private Tarefas tarefas;

	private Tarefa tarefa;

	private List<Tarefa> todasTarefas;

	public PermissoesUsuariosBean() {
		tarefa = new Tarefa();
	}

	public void inicializar() {
		todasTarefas = tarefas.todasTarefas();

		System.out.println("Inicializando..."); 
	}

	public void salvar() {
		FacesUtil.addErrorMessage("Está aqui dentro");
	}

	public Tarefa getTarefa() {
		return tarefa;
	}

	public void setTarefa(Tarefa tarefa) {
		this.tarefa = tarefa;
	}

	public List<Tarefa> getTodasTarefas() {
		return todasTarefas;
	}

}
