package com.ranchsorting.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "tb_ficha_inscricao")
public class FichaInscricao implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	private Date dataInscricao;
	private List<Passada> passadas = new ArrayList<>();
	private List<Competidor> competidores = new ArrayList<>();
	private Campeonato campeonato;
	private Etapa etapa;
	private Divisao divisao;
	private BigDecimal valorComprado;
	private BigDecimal valorPago;
	private StatusFicha statusFicha;
	private TipoFicha tipoFicha;
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
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "fi_data_inscricao")
	public Date getDataInscricao() {
		return dataInscricao;
	}

	public void setDataInscricao(Date dataInscricao) {
		this.dataInscricao = dataInscricao;
	}

	@OneToMany(mappedBy = "fichaInscricao", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
	public List<Passada> getPassadas() {
		return passadas;
	}

	public void setPassadas(List<Passada> passadas) {
		this.passadas = passadas;
	}

	@ManyToMany
	@JoinTable(name="tb_ficha_competidor", 
			joinColumns=@JoinColumn(name="fi_ficha_inscricao"),
			inverseJoinColumns=@JoinColumn(name="co_competidor"))
	public List<Competidor> getCompetidores() {
		return competidores;
	}

	public void setCompetidores(List<Competidor> competidores) {
		this.competidores = competidores;
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

	@Enumerated(EnumType.STRING)
	@Column(name = "fi_status_ficha", length = 15)
	public StatusFicha getStatusFicha() {
		return statusFicha;
	}

	public void setStatusFicha(StatusFicha statusFicha) {
		this.statusFicha = statusFicha;
	}

	@NotNull
	@Enumerated(EnumType.STRING)
	@Column(name = "fi_tipo_ficha", length = 15)
	public TipoFicha getTipoFicha() {
		return tipoFicha;
	}

	public void setTipoFicha(TipoFicha tipoFicha) {
		this.tipoFicha = tipoFicha;
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
