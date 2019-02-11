package com.ranchsorting.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "tb_parcela_receber")
public class ParcelaReceber implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	private BigDecimal valorPago;
	private BigDecimal valorDevido;
	private Date dataVencimento;
	private Date dataPagamento;
	private String observacao;
	private ContasReceber titulo;
	private String Banco;

	@Id
	@GeneratedValue
	@Column(name = "pr_id")
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "pr_valor_pago", precision = 10, scale = 2)
	public BigDecimal getValorPago() {
		return valorPago;
	}

	public void setValorPago(BigDecimal valorPago) {
		this.valorPago = valorPago;
	}

	@Column(name = "pr_valor_devido", nullable = false, precision = 10, scale = 2)
	public BigDecimal getValorDevido() {
		return valorDevido;
	}

	public void setValorDevido(BigDecimal valorDevido) {
		this.valorDevido = valorDevido;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "pr_data_vencimento")
	public Date getDataVencimento() {
		return dataVencimento;
	}

	public void setDataVencimento(Date dataVencimento) {
		this.dataVencimento = dataVencimento;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "pr_data_pagamento")
	public Date getDataPagamento() {
		return dataPagamento;
	}

	public void setDataPagamento(Date dataPagamento) {
		this.dataPagamento = dataPagamento;
	}

	@Column(name = "pr_observacao", columnDefinition = "text")
	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	@ManyToOne
	@JoinColumn(name = "pr_contas_receber_id", nullable = false)
	public ContasReceber getTitulo() {
		return titulo;
	}

	public void setTitulo(ContasReceber titulo) {
		this.titulo = titulo;
	}

	@Column(name = "pr_banco", nullable = false, length = 60)
	public String getBanco() {
		return Banco;
	}

	public void setBanco(String banco) {
		Banco = banco;
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
		ParcelaReceber other = (ParcelaReceber) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}