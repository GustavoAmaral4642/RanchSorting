package com.ranchsorting.controller;

import java.io.Serializable;

import javax.faces.bean.ViewScoped;
import javax.inject.Named;

import com.ranchsorting.model.FolhaCompeticao;

@Named
@ViewScoped
public class CadastroFolhaCompeticaoBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private FolhaCompeticao folhaCompeticao;

	public CadastroFolhaCompeticaoBean() {
		folhaCompeticao = new FolhaCompeticao();
	}

	public void inicializar() {
	
	}

	public void limpar() {
		folhaCompeticao = new FolhaCompeticao();
	}

	public void salvar() {
		
	}

	public FolhaCompeticao getFolhaCompeticao() {
		return folhaCompeticao;
	}

	public void setFolhaCompeticao(FolhaCompeticao folhaCompeticao) {
		this.folhaCompeticao = folhaCompeticao;
	}

}
