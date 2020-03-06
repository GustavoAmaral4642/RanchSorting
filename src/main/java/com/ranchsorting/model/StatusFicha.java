package com.ranchsorting.model;

public enum StatusFicha {

	EMORDEM("Em Ordem"), 
	EMORDEMPRONTA("Em Ordem Pronta"),
	CADASTRADA("Cadastrada"),
	CANCELADA("Cancelada");
	
	private String descricao;
	
	StatusFicha(String descricao){
		this.descricao = descricao;
	}
	
	public String getDescricao(){
		return descricao;
	}
}
