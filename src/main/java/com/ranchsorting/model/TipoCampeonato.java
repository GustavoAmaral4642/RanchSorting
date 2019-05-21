package com.ranchsorting.model;

public enum TipoCampeonato {

	TAMBOR("Tambor"), 
	RANCHSORTING("Ranch Sorting"),
	TEAMPENNING("Team Penning");
	
	private String descricao;
	
	TipoCampeonato(String descricao){
		this.descricao = descricao;
	}
	
	public String getDescricao(){
		return descricao;
	}
}
