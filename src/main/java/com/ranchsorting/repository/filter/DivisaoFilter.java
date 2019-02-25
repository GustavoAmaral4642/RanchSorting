package com.ranchsorting.repository.filter;

import java.io.Serializable;

public class DivisaoFilter implements Serializable {

	private static final long serialVersionUID = 1L;

	private String nome;
	private String idadeInicial;
	private String idadeFinal;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getIdadeInicial() {
		return idadeInicial;
	}

	public void setIdadeInicial(String idadeInicial) {
		this.idadeInicial = idadeInicial;

	}
	
	public String getIdadeFinal() {
		return idadeFinal;
	}

	public void setIdadeFinal(String idadeFinal) {
		this.idadeFinal = idadeFinal;
	}

}
