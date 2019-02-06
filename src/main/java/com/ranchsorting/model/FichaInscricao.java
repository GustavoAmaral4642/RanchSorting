package com.ranchsorting.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "tb_ficha_inscricao")
public class FichaInscricao implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	private Long qntInscricoes;
	private Date dataInscricao;
	private Competidor competidor;
	private Animal animal;
	private Campeonato campeonato;
	private Etapa etapa;
	private Divisao divisao;
	private BigDecimal valorComprado;
	private BigDecimal valorPago;
	private Date dataPagamento;
	private FormaPagamento formaPagamento;
	private String bancoPagamento;
	private String anuidadePaga;
	private TipoAnuidade tipoAnuidade;
	private Usuario usuarioAlteracao;
	private Date dataAlteracao;
	private Ocorrencia ocorrencia;

	@Id
	@GeneratedValue
	@Column(name = "fi_id")
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "fi_qnt_inscricoes", nullable = false)
	public Long getQntInscricoes() {
		return qntInscricoes;
	}

	public void setQntInscricoes(Long qntInscricoes) {
		this.qntInscricoes = qntInscricoes;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "fi_data_inscricao")
	public Date getDataInscricao() {
		return dataInscricao;
	}

	public void setDataInscricao(Date dataInscricao) {
		this.dataInscricao = dataInscricao;
	}

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "fi_competidor")
	Competidor getCompetidor() {
		return competidor;
	}

	public void setCompetidor(Competidor competidor) {
		this.competidor = competidor;
	}

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "fi_animal")
	public Animal getAnimal() {
		return animal;
	}

	public void setAnimal(Animal animal) {
		this.animal = animal;
	}

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "fi_campeonato")
	public Campeonato getCampeonato() {
		return campeonato;
	}

	public void setCampeonato(Campeonato campeonato) {
		this.campeonato = campeonato;
	}

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "fi_etapa")
	public Etapa getEtapa() {
		return etapa;
	}

	public void setEtapa(Etapa etapa) {
		this.etapa = etapa;
	}

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "fi_divisao")
	public Divisao getDivisao() {
		return divisao;
	}

	public void setDivisao(Divisao divisao) {
		this.divisao = divisao;
	}

	@Column(name = "fi_valor_comprado", nullable = false, precision = 10, scale = 2)
	public BigDecimal getValorComprado() {
		return valorComprado;
	}

	public void setValorComprado(BigDecimal valorComprado) {
		this.valorComprado = valorComprado;
	}

	@Column(name = "fi_valor_pago", precision = 10, scale = 2)
	public BigDecimal getValorPago() {
		return valorPago;
	}

	public void setValorPago(BigDecimal valorPago) {
		this.valorPago = valorPago;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "fi_data_pagamento")
	public Date getDataPagamento() {
		return dataPagamento;
	}

	public void setDataPagamento(Date dataPagamento) {
		this.dataPagamento = dataPagamento;
	}

	@Enumerated(EnumType.STRING)
	@Column(name = "fi_frm_pagamento", nullable = false, length = 15)
	public FormaPagamento getFormaPagamento() {
		return formaPagamento;
	}

	public void setFormaPagamento(FormaPagamento formaPagamento) {
		this.formaPagamento = formaPagamento;
	}

	@Column(name = "fi_banco", length = 50)
	public String getBancoPagamento() {
		return bancoPagamento;
	}

	public void setBancoPagamento(String bancoPagamento) {
		this.bancoPagamento = bancoPagamento;
	}

	@Column(name = "fi_anuidade_paga", nullable = false, length = 2)
	public String getAnuidadePaga() {
		return anuidadePaga;
	}

	public void setAnuidadePaga(String anuidadePaga) {
		this.anuidadePaga = anuidadePaga;
	}

	@Enumerated(EnumType.STRING)
	@Column(name = "fi_tp_anuidade", nullable = false, length = 12)
	public TipoAnuidade getTipoAnuidade() {
		return tipoAnuidade;
	}

	public void setTipoAnuidade(TipoAnuidade tipoAnuidade) {
		this.tipoAnuidade = tipoAnuidade;
	}

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "fi_us_alteracao")
	public Usuario getUsuarioAlteracao() {
		return usuarioAlteracao;
	}

	public void setUsuarioAlteracao(Usuario usuarioAlteracao) {
		this.usuarioAlteracao = usuarioAlteracao;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "fi_data_alteracao")
	public Date getDataAlteracao() {
		return dataAlteracao;
	}

	public void setDataAlteracao(Date dataAlteracao) {
		this.dataAlteracao = dataAlteracao;
	}

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "fi_ocorrencia")
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
		FichaInscricao other = (FichaInscricao) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
