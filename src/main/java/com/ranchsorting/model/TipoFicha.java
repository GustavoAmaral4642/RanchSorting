package com.ranchsorting.model;

public enum TipoFicha {

	INDIVIDUAL("Individual"), 
	DUPLA("Dupla"),
	TRIO("Trio");
	
	private String descricao;
	
	TipoFicha(String descricao){
		this.descricao = descricao;
	}
	
	public String getDescricao(){
		return descricao;
	}
}
