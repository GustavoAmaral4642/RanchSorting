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

@Entity
@Table(name = "tb_divisao")
public class Divisao implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	private String nome;
	private Campeonato campeonato;
	private String observacoes;
	private String idadeInicial;
	private String idadeFinal;
	private Usuario usuarioAlteracao;
	private Date dataAlteracao;
	private Ocorrencia ocorrencia;

	@Id
	@GeneratedValue
	@Column(name = "dv_id")
	public final Long getId() {
		return id;
	}

	public final void setId(Long id) {
		this.id = id;
	}

	@Column(name = "dv_nome", nullable = false, length = 60)
	public final String getNome() {
		return nome;
	}

	public final void setNome(String nome) {
		this.nome = nome;
	}

	@ManyToOne
	@JoinColumn(name = "dv_campeonato_id", nullable = false)
	public Campeonato getCampeonato() {
		return campeonato;
	}

	public void setCampeonato(Campeonato campeonato) {
		this.campeonato = campeonato;
	}

	@Column(columnDefinition = "text")
	public final String getObservacoes() {
		return observacoes;
	}

	public final void setObservacoes(String observacoes) {
		this.observacoes = observacoes;
	}

	@Column(name = "dv_idade_inicial", length = 2)
	public final String getIdadeInicial() {
		return idadeInicial;
	}

	public final void setIdadeInicial(String idadeInicial) {
		this.idadeInicial = idadeInicial;
	}

	@Column(name = "dv_idade_final", length = 2)
	public final String getIdadeFinal() {
		return idadeFinal;
	}

	public final void setIdadeFinal(String idadeFinal) {
		this.idadeFinal = idadeFinal;
	}

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "dv_us_alteracao")
	public final Usuario getUsuarioAlteracao() {
		return usuarioAlteracao;
	}

	public final void setUsuarioAlteracao(Usuario usuarioAlteracao) {
		this.usuarioAlteracao = usuarioAlteracao;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "dv_data_alteracao")
	public final Date getDataAlteracao() {
		return dataAlteracao;
	}

	public final void setDataAlteracao(Date dataAlteracao) {
		this.dataAlteracao = dataAlteracao;
	}

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "dv_ocorrencia")
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
		Divisao other = (Divisao) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
