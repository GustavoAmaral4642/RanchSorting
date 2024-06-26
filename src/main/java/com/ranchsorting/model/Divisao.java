package com.ranchsorting.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.validator.constraints.NotBlank;

@Entity
@Table(name = "tb_divisao")
//@Cache(usage = CacheConcurrencyStrategy.TRANSACTIONAL)
public class Divisao implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	private String nome;
	private String observacoes;
	private String idadeInicial;
	private String idadeFinal;
	private TipoFicha tipoFicha;
	private Long qntFichasPadrao;
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

	@NotBlank
	@Size(max = 60)
	@Column(name = "dv_nome", nullable = false, length = 60)
	public final String getNome() {
		return nome;
	}

	public final void setNome(String nome) {
		this.nome = nome;
	}

	@Column(name = "dv_observacao", columnDefinition = "text")
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

	@NotNull
	@Enumerated(EnumType.STRING)
	@Column(name = "dv_tipo_ficha", length = 15)
	public TipoFicha getTipoFicha() {
		return tipoFicha;
	}

	public void setTipoFicha(TipoFicha tipoFicha) {
		this.tipoFicha = tipoFicha;
	}

	@Column(name = "dv_qt_fichas_padrao")
	public Long getQntFichasPadrao() {
		return qntFichasPadrao;
	}

	public void setQntFichasPadrao(Long qntFichasPadrao) {
		this.qntFichasPadrao = qntFichasPadrao;
	}

	@OneToOne(fetch=FetchType.LAZY, cascade = CascadeType.ALL)
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
