package com.ranchsorting.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

@Entity
@Table(name = "tb_etapa")
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
	
	@Id
	@GeneratedValue
	@Column(name = "ep_id")
	public final Long getId() {
		return id;
	}

	public final void setId(Long id) {
		this.id = id;
	}

	@NotBlank
	@Size(max=100)
	@Column(name = "ep_nome", nullable = false, length = 100)
	public final String getNome() {
		return nome;
	}

	public final void setNome(String nome) {
		this.nome = nome;
	}

	@ManyToOne
	@JoinColumn(name = "ep_campeonato_id")
	public final Campeonato getCampeonato() {
		return campeonato;
	}

	public final void setCampeonato(Campeonato campeonato) {
		this.campeonato = campeonato;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "ep_data_evento")
	public final Date getDataEvento() {
		return dataEvento;
	}

	public final void setDataEvento(Date dataEvento) {
		this.dataEvento = dataEvento;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "ep_data_inicio_inscricoes")
	public final Date getDataInicioInscricoes() {
		return dataInicioInscricoes;
	}

	public final void setDataInicioInscricoes(Date dataInicioInscricoes) {
		this.dataInicioInscricoes = dataInicioInscricoes;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "ep_data_fim_inscricoes")
	public final Date getDataFimInscricoes() {
		return dataFimInscricoes;
	}

	public final void setDataFimInscricoes(Date dataFimInscricoes) {
		this.dataFimInscricoes = dataFimInscricoes;
	}

	@Size(max=100)
	@Column(name = "ep_organizador", length = 100)
	public final String getOrganizador() {
		return organizador;
	}

	public final void setOrganizador(String organizador) {
		this.organizador = organizador;
	}

	@Size(max=120)
	@Column(name = "ep_contato_organizador", length = 120)
	public final String getContatoOrganizador() {
		return contatoOrganizador;
	}

	public final void setContatoOrganizador(String contatoOrganizador) {
		this.contatoOrganizador = contatoOrganizador;
	}

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "ep_us_alteracao")
	public final Usuario getUsuarioAlteracao() {
		return usuarioAlteracao;
	}

	public final void setUsuarioAlteracao(Usuario usuarioAlteracao) {
		this.usuarioAlteracao = usuarioAlteracao;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "ep_data_alteracao")
	public final Date getDataAlteracao() {
		return dataAlteracao;
	}

	public final void setDataAlteracao(Date dataAlteracao) {
		this.dataAlteracao = dataAlteracao;
	}

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "ep_ocorrencia")
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
