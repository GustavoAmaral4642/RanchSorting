package com.ranchsorting.model;

import java.io.Serializable;
import java.util.Date;

public class FolhaCompeticao implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	private Long codigoFolha;
	private Campeonato campeonato;
	private Etapa etapa;
	private Divisao divisao;
	private Date data;
	private Competidor competidor1;
	private Animal animal1;
	private FichaInscricao fichaInscricao1;
	private Boolean situacaoFicha1;
	private Competidor competidor2;
	private Animal animal2;
	private FichaInscricao fichaInscricao2;
	private Boolean situacaoFicha2;
	private Long numeroDupla;
	private Usuario usuarioAlteracao;
	private Date dataAlteracao;
	private Ocorrencia ocorrencia;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getCodigoFolha() {
		return codigoFolha;
	}

	public void setCodigoFolha(Long codigoFolha) {
		this.codigoFolha = codigoFolha;
	}

	public Campeonato getCampeonato() {
		return campeonato;
	}

	public void setCampeonato(Campeonato campeonato) {
		this.campeonato = campeonato;
	}

	public Etapa getEtapa() {
		return etapa;
	}

	public void setEtapa(Etapa etapa) {
		this.etapa = etapa;
	}

	public Divisao getDivisao() {
		return divisao;
	}

	public void setDivisao(Divisao divisao) {
		this.divisao = divisao;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public Competidor getCompetidor1() {
		return competidor1;
	}

	public void setCompetidor1(Competidor competidor1) {
		this.competidor1 = competidor1;
	}

	public Animal getAnimal1() {
		return animal1;
	}

	public void setAnimal1(Animal animal1) {
		this.animal1 = animal1;
	}

	public FichaInscricao getFichaInscricao1() {
		return fichaInscricao1;
	}

	public void setFichaInscricao1(FichaInscricao fichaInscricao1) {
		this.fichaInscricao1 = fichaInscricao1;
	}

	public Boolean getSituacaoFicha1() {
		return situacaoFicha1;
	}

	public void setSituacaoFicha1(Boolean situacaoFicha1) {
		this.situacaoFicha1 = situacaoFicha1;
	}

	public Competidor getCompetidor2() {
		return competidor2;
	}

	public void setCompetidor2(Competidor competidor2) {
		this.competidor2 = competidor2;
	}

	public Animal getAnimal2() {
		return animal2;
	}

	public void setAnimal2(Animal animal2) {
		this.animal2 = animal2;
	}

	public FichaInscricao getFichaInscricao2() {
		return fichaInscricao2;
	}

	public void setFichaInscricao2(FichaInscricao fichaInscricao2) {
		this.fichaInscricao2 = fichaInscricao2;
	}

	public Boolean getSituacaoFicha2() {
		return situacaoFicha2;
	}

	public void setSituacaoFicha2(Boolean situacaoFicha2) {
		this.situacaoFicha2 = situacaoFicha2;
	}

	public Long getNumeroDupla() {
		return numeroDupla;
	}

	public void setNumeroDupla(Long numeroDupla) {
		this.numeroDupla = numeroDupla;
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
		FolhaCompeticao other = (FolhaCompeticao) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
