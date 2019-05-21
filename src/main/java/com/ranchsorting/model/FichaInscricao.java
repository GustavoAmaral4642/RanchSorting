package com.ranchsorting.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "tb_ficha_inscricao")
public class FichaInscricao implements Serializable {

	private static final long serialVersionUID = 1L;

	/* parei verificando esta classe*/
	/* ajustar o requisito e depois a classe*/
	
	private Long id;
	private Long qntInscricoes;
	private Date dataInscricao;
	private List<Passada> passada;
	private Campeonato campeonato;
	private Etapa etapa;
	private Divisao divisao;
	private BigDecimal valorComprado;
	private BigDecimal valorPago;
	private String anuidadePaga;
	private TipoAnuidade tipoAnuidade;
	private StatusFicha statusFicha;
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

	@NotNull
	@Column(name = "fi_qnt_inscricoes")
	public Long getQntInscricoes() {
		return qntInscricoes;
	}

	public void setQntInscricoes(Long qntInscricoes) {
		this.qntInscricoes = qntInscricoes;
	}

	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "fi_data_inscricao")
	public Date getDataInscricao() {
		return dataInscricao;
	}

	public void setDataInscricao(Date dataInscricao) {
		this.dataInscricao = dataInscricao;
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

	@NotNull
	@Column(name = "fi_valor_comprado", precision = 10, scale = 2)
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


	@Size(max = 2)
	@Column(name = "fi_anuidade_paga", length = 2)
	public String getAnuidadePaga() {
		return anuidadePaga;
	}

	public void setAnuidadePaga(String anuidadePaga) {
		this.anuidadePaga = anuidadePaga;
	}

	@Enumerated(EnumType.STRING)
	@Column(name = "fi_tp_anuidade", length = 12)
	public TipoAnuidade getTipoAnuidade() {
		return tipoAnuidade;
	}

	public void setTipoAnuidade(TipoAnuidade tipoAnuidade) {
		this.tipoAnuidade = tipoAnuidade;
	}

	@Enumerated(EnumType.STRING)
	@Column(name = "fi_status_ficha", length = 15)
	public StatusFicha getStatusFicha() {
		return statusFicha;
	}

	public void setStatusFicha(StatusFicha statusFicha) {
		this.statusFicha = statusFicha;
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
