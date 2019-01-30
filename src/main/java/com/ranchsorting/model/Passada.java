package com.ranchsorting.model;

import java.io.Serializable;
import java.util.Date;

public class Passada implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	private Long numeroDupla;
	private Competidor competidor1;
	private Competidor competidor2;
	private String tempo;
	private Long qntBoi;
	private Long ranking;
	private Boolean sat;
	private Usuario usuarioAlteracao;
	private Date dataAlteracao;
	private Ocorrencia ocorrencia;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getNumeroDupla() {
		return numeroDupla;
	}

	public void setNumeroDupla(Long numeroDupla) {
		this.numeroDupla = numeroDupla;
	}

	public Competidor getCompetidor1() {
		return competidor1;
	}

	public void setCompetidor1(Competidor competidor1) {
		this.competidor1 = competidor1;
	}

	public Competidor getCompetidor2() {
		return competidor2;
	}

	public void setCompetidor2(Competidor competidor2) {
		this.competidor2 = competidor2;
	}

	public String getTempo() {
		return tempo;
	}

	public void setTempo(String tempo) {
		this.tempo = tempo;
	}

	public Long getQntBoi() {
		return qntBoi;
	}

	public void setQntBoi(Long qntBoi) {
		this.qntBoi = qntBoi;
	}

	public Long getRanking() {
		return ranking;
	}

	public void setRanking(Long ranking) {
		this.ranking = ranking;
	}

	public Boolean getSat() {
		return sat;
	}

	public void setSat(Boolean sat) {
		this.sat = sat;
	}

	public Usuario getUsuarioAlteracao() {
		return usuarioAlteracao;
	}

	public void setUsuarioAlteracao(Usuario usuarioAlteracao) {
		this.usuarioAlteracao = usuarioAlteracao;
	}

	public Date getDataAlteracao() {
		return dataAlteracao;
	}

	public void setDataAlteracao(Date dataAlteracao) {
		this.dataAlteracao = dataAlteracao;
	}

	public Ocorrencia getOcorrencia() {
		return ocorrencia;
	}

	public void setOcorrencia(Ocorrencia ocorrencia) {
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
		Passada other = (Passada) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
