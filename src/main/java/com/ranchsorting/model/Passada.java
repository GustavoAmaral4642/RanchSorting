package com.ranchsorting.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "tb_passada")
public class Passada implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	private List<Competidor> competidores = new ArrayList<>();
	private Long numeroDupla;
	private Campeonato campeonato;
	private Etapa etapa;
	private Divisao divisao;
	private String tempo;
	private Long qntBoi;
	private Long ranking;
	private String sat;
	private FichaInscricao fichaInscricao;
	private OrdemEntrada ordemEntrada;
	private Usuario usuarioAlteracao;
	private Date dataAlteracao;
	private Ocorrencia ocorrencia;

	@Id
	@GeneratedValue
	@Column(name = "pa_id")
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@ManyToMany
	@JoinTable(name = "tb_passada_competidor", joinColumns = @JoinColumn(name = "pa_passada"), inverseJoinColumns = @JoinColumn(name = "co_competidor"))
	public List<Competidor> getCompetidores() {
		return competidores;
	}

	public void setCompetidores(List<Competidor> competidores) {
		this.competidores = competidores;
	}

	@Column(name = "fi_numero_dupla")
	public Long getNumeroDupla() {
		return numeroDupla;
	}

	public void setNumeroDupla(Long numeroDupla) {
		this.numeroDupla = numeroDupla;
	}

	@NotNull
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "fi_campeonato")
	public Campeonato getCampeonato() {
		return campeonato;
	}

	public void setCampeonato(Campeonato campeonato) {
		this.campeonato = campeonato;
	}

	@NotNull
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "fi_etapa")
	public Etapa getEtapa() {
		return etapa;
	}

	public void setEtapa(Etapa etapa) {
		this.etapa = etapa;
	}

	@NotNull
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "fi_divisao")
	public Divisao getDivisao() {
		return divisao;
	}

	public void setDivisao(Divisao divisao) {
		this.divisao = divisao;
	}

	@Size(max = 20)
	@Column(name = "pa_tempo", length = 20)
	public String getTempo() {
		return tempo;
	}

	public void setTempo(String tempo) {
		this.tempo = tempo;
	}

	@Column(name = "pa_qnt_boi")
	public Long getQntBoi() {
		return qntBoi;
	}

	public void setQntBoi(Long qntBoi) {
		this.qntBoi = qntBoi;
	}

	@Column(name = "pa_ranking")
	public Long getRanking() {
		return ranking;
	}

	public void setRanking(Long ranking) {
		this.ranking = ranking;
	}

	@Size(max = 10)
	@Column(name = "pa_sat", length = 10)
	public String getSat() {
		return sat;
	}

	public void setSat(String sat) {
		this.sat = sat;
	}

	@ManyToOne
	@JoinColumn(name = "pa_ficha_inscricao_id", nullable = false)
	public FichaInscricao getFichaInscricao() {
		return fichaInscricao;
	}

	public void setFichaInscricao(FichaInscricao fichaInscricao) {
		this.fichaInscricao = fichaInscricao;
	}

	@ManyToOne
	@JoinColumn(name = "pa_ordem_entrada_id")
	public OrdemEntrada getOrdemEntrada() {
		return ordemEntrada;
	}

	public void setOrdemEntrada(OrdemEntrada ordemEntrada) {
		this.ordemEntrada = ordemEntrada;
	}

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "pa_us_alteracao")
	public Usuario getUsuarioAlteracao() {
		return usuarioAlteracao;
	}

	public void setUsuarioAlteracao(Usuario usuarioAlteracao) {
		this.usuarioAlteracao = usuarioAlteracao;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "pa_data_alteracao")
	public Date getDataAlteracao() {
		return dataAlteracao;
	}

	public void setDataAlteracao(Date dataAlteracao) {
		this.dataAlteracao = dataAlteracao;
	}

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "pa_ocorrencia")
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
