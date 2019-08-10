package com.ranchsorting.model;

import java.io.Serializable; 
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;

@Entity
@Table(name = "tb_passada")
public class Passada implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	private List<FichaInscricao> fichasInscricoes = new ArrayList<>();
	private OrdemEntrada ordemEntrada;
	private Long numeroDupla;	
	private Long numeroBoi;
	private String tempo;
	private Long qntBoi;
	private Long ranking;
	private String sat;
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

	@OneToMany(mappedBy = "passada", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
	public List<FichaInscricao> getFichasInscricoes() {
		return fichasInscricoes;
	}

	public void setFichasInscricoes(List<FichaInscricao> fichasInscricoes) {		
		this.fichasInscricoes = fichasInscricoes;
	}

	@ManyToOne
	@JoinColumn(name = "pa_ordem_entrada_id")
	public OrdemEntrada getOrdemEntrada() {
		return ordemEntrada;
	}

	public void setOrdemEntrada(OrdemEntrada ordemEntrada) {
		this.ordemEntrada = ordemEntrada;
	}

	@Column(name = "pa_numero_dupla")
	public Long getNumeroDupla() {
		return numeroDupla;
	}

	public void setNumeroDupla(Long numeroDupla) {
		this.numeroDupla = numeroDupla;
	}

	@Column(name = "pa_numero_boi")
	public Long getNumeroBoi() {
		return numeroBoi;
	}

	public void setNumeroBoi(Long numeroBoi) {
		this.numeroBoi = numeroBoi;
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
