package com.ranchsorting.model;

import java.io.Serializable;
import java.util.Date;

public class Etapa implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	private String nome;
	private Campeonato campeonato;
	private Date dataEvento;
	private Date dataInicioInscricoes;
	private Date dataFimInscricoes;
	private String organizador;
	private String contatoOrganizador;
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

	public final Campeonato getCampeonato() {
		return campeonato;
	}

	public final void setCampeonato(Campeonato campeonato) {
		this.campeonato = campeonato;
	}

	public final Date getDataEvento() {
		return dataEvento;
	}

	public final void setDataEvento(Date dataEvento) {
		this.dataEvento = dataEvento;
	}

	public final Date getDataInicioInscricoes() {
		return dataInicioInscricoes;
	}

	public final void setDataInicioInscricoes(Date dataInicioInscricoes) {
		this.dataInicioInscricoes = dataInicioInscricoes;
	}

	public final Date getDataFimInscricoes() {
		return dataFimInscricoes;
	}

	public final void setDataFimInscricoes(Date dataFimInscricoes) {
		this.dataFimInscricoes = dataFimInscricoes;
	}

	public final String getOrganizador() {
		return organizador;
	}

	public final void setOrganizador(String organizador) {
		this.organizador = organizador;
	}

	public final String getContatoOrganizador() {
		return contatoOrganizador;
	}

	public final void setContatoOrganizador(String contatoOrganizador) {
		this.contatoOrganizador = contatoOrganizador;
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
		Etapa other = (Etapa) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}
