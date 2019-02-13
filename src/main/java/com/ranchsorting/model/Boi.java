package com.ranchsorting.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "tb_boi")
public class Boi implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	private Long numero;
	private Usuario usuarioAlteracao;
	private Date dataAlteracao;
	private Ocorrencia ocorrencia;

	@Id
	@GeneratedValue
	@Column(name = "bo_id")
	public final Long getId() {
		return id;
	}

	public final void setId(Long id) {
		this.id = id;
	}

	@NotNull
	@Column(name = "bo_numero", nullable = false)
	public final Long getNumero() {
		return numero;
	}

	public final void setNumero(Long numero) {
		this.numero = numero;
	}

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "bo_us_alteracao")
	public final Usuario getUsuarioAlteracao() {
		return usuarioAlteracao;
	}

	public final void setUsuarioAlteracao(Usuario usuarioAlteracao) {
		this.usuarioAlteracao = usuarioAlteracao;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "bo_data_alteracao")
	public final Date getDataAlteracao() {
		return dataAlteracao;
	}

	public final void setDataAlteracao(Date dataAlteracao) {
		this.dataAlteracao = dataAlteracao;
	}

  	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "bo_ocorrencia")
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
		Boi other = (Boi) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}
