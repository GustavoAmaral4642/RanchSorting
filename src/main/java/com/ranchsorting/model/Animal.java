package com.ranchsorting.model;

import java.io.Serializable;
import java.util.Date;

public class Animal implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	private String nome;
	private Long idade;
	private String cor;
	private Etnia etnia;
	private String raca;
	private Competidor proprietario;
	private Usuario usuarioAlteracao;
	private Date dataAlteracao;
	private Ocorrencia ocorrencia;

	public final Long getId() {
		return id;
	}

	public final void setId(Long id) {
		this.id = id;
	}

	public final String getNome() {
		return nome;
	}

	public final void setNome(String nome) {
		this.nome = nome;
	}

	public final Long getIdade() {
		return idade;
	}

	public final void setIdade(Long idade) {
		this.idade = idade;
	}

	public final String getCor() {
		return cor;
	}

	public final void setCor(String cor) {
		this.cor = cor;
	}

	public final Etnia getEtnia() {
		return etnia;
	}

	public final void setEtnia(Etnia etnia) {
		this.etnia = etnia;
	}

	public final String getRaca() {
		return raca;
	}

	public final void setRaca(String raca) {
		this.raca = raca;
	}

	public final Competidor getProprietario() {
		return proprietario;
	}

	public final void setProprietario(Competidor proprietario) {
		this.proprietario = proprietario;
	}

	public final Usuario getUsuarioAlteracao() {
		return usuarioAlteracao;
	}

	public final void setUsuarioAlteracao(Usuario usuarioAlteracao) {
		this.usuarioAlteracao = usuarioAlteracao;
	}

	public final Date getDataAlteracao() {
		return dataAlteracao;
	}

	public final void setDataAlteracao(Date dataAlteracao) {
		this.dataAlteracao = dataAlteracao;
	}

	public final Ocorrencia getOcorrencia() {
		return ocorrencia;
	}

	public final void setOcorrencia(Ocorrencia ocorrencia) {
		this.ocorrencia = ocorrencia;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Animal other = (Animal) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
