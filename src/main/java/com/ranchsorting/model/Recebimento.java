package com.ranchsorting.model;

import java.io.Serializable;
import java.math.BigDecimal;
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
@Table(name = "tb_recebimentos")
public class Recebimento implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	private Titulo titulo;
	private BigDecimal valorRecebimento;
	private Date dataRecebimento;
	private Usuario usuarioAlteracao;
	private Date dataAlteracao;
	private Ocorrencia ocorrencia;

	@Id
	@GeneratedValue
	@Column(name = "re_id")
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@NotNull
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "re_titulo")
	public Titulo getTitulo() {
		return titulo;
	}

	public void setTitulo(Titulo titulo) {
		this.titulo = titulo;
	}
	
	@Column(name = "cr_valor_recebimento", precision = 10, scale = 2)
	public BigDecimal getValorRecebimento() {
		return valorRecebimento;
	}

	public void setValorRecebimento(BigDecimal valorRecebimento) {
		this.valorRecebimento = valorRecebimento;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "re_data_recebimento")
	public Date getDataRecebimento() {
		return dataRecebimento;
	}

	public void setDataRecebimento(Date dataRecebimento) {
		this.dataRecebimento = dataRecebimento;
	}

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "re_us_alteracao")
	public Usuario getUsuarioAlteracao() {
		return usuarioAlteracao;
	}

	public void setUsuarioAlteracao(Usuario usuarioAlteracao) {
		this.usuarioAlteracao = usuarioAlteracao;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "re_data_alteracao")
	public Date getDataAlteracao() {
		return dataAlteracao;
	}

	public void setDataAlteracao(Date dataAlteracao) {
		this.dataAlteracao = dataAlteracao;
	}

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "re_ocorrencia")
	public Ocorrencia getOcorrencia() {
		return ocorrencia;
	}

	public void setOcorrencia(Ocorrencia ocorrencia) {
		this.ocorrencia = ocorrencia;
	}

}