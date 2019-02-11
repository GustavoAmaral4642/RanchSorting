package com.ranchsorting.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "tb_contas_receber")
public class ContasReceber implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	private Competidor competidor;
	private Campeonato campeonato;
	private BigDecimal valorTitulo;
	private BigDecimal saldo;
	private Date dataTitulo;
	private String observacao;
	private List<ParcelaReceber> parcelas;
	private Usuario usuarioAlteracao;
	private Date dataAlteracao;
	private Ocorrencia ocorrencia;

	@Id
	@GeneratedValue
	@Column(name = "cr_id")
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "cr_competidor")
	public Competidor getCompetidor() {
		return competidor;
	}

	public void setCompetidor(Competidor competidor) {
		this.competidor = competidor;
	}

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "cr_campeonato")
	public Campeonato getCampeonato() {
		return campeonato;
	}

	public void setCampeonato(Campeonato campeonato) {
		this.campeonato = campeonato;
	}

	@Column(name = "cr_valor_titulo", nullable = false, precision = 10, scale = 2)
	public BigDecimal getValorTitulo() {
		return valorTitulo;
	}

	public void setValorTitulo(BigDecimal valorTitulo) {
		this.valorTitulo = valorTitulo;
	}

	@Column(name = "cr_saldo", precision = 10, scale = 2)
	public BigDecimal getSaldo() {
		return saldo;
	}

	public void setSaldo(BigDecimal saldo) {
		this.saldo = saldo;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "cr_data_titulo")
	public Date getDataTitulo() {
		return dataTitulo;
	}

	public void setDataTitulo(Date dataTitulo) {
		this.dataTitulo = dataTitulo;
	}

	@Column(name = "cr_observacao",columnDefinition = "text")
	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}
	
	@OneToMany(mappedBy="titulo", cascade=CascadeType.ALL)
	public List<ParcelaReceber> getParcelas() {
		return parcelas;
	}
	
	public void setParcelas(List<ParcelaReceber> parcelas) {
		this.parcelas = parcelas;
	}

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "cr_us_alteracao")
	public Usuario getUsuarioAlteracao() {
		return usuarioAlteracao;
	}

	public void setUsuarioAlteracao(Usuario usuarioAlteracao) {
		this.usuarioAlteracao = usuarioAlteracao;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "cr_data_alteracao")
	public Date getDataAlteracao() {
		return dataAlteracao;
	}

	public void setDataAlteracao(Date dataAlteracao) {
		this.dataAlteracao = dataAlteracao;
	}

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "cr_ocorrencia")
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
		ContasReceber other = (ContasReceber) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}