package com.ranchsorting.repository.filter;

import java.io.Serializable; 

public class AnimalFilter implements Serializable {

	private static final long serialVersionUID = 1L;

	private String nome;
	private String cor;
	private String competidor;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCor() {
		return cor;
	}

	public void setCor(String cor) {
		this.cor = cor;
	}

	public String getCompetidor() {
		return competidor;
	}

	public void setCompetidor(String competidor) {
		this.competidor = competidor;
	}

}