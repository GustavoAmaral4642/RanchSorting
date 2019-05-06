package com.ranchsorting.model;

import java.io.Serializable; 
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "tb_folha_competicao")
public class FolhaCompeticao implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	private Date data;
	private FichaInscricao fichaInscricao1;
	private FichaInscricao fichaInscricao2;
	private Long numeroDupla;
	private OrdemEntrada ordemEntrada;
	private Passada passada;
	private TipoFolha tipoFolha;
	private Usuario usuarioAlteracao;
	private Date dataAlteracao;
	private Ocorrencia ocorrencia;

	@Id
	@GeneratedValue
	@Column(name = "fc_id")
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "fc_data")
	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "fc_ficha_inscricao1")
	public FichaInscricao getFichaInscricao1() {
		return fichaInscricao1;
	}

	public void setFichaInscricao1(FichaInscricao fichaInscricao1) {
		this.fichaInscricao1 = fichaInscricao1;
	}

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "fc_ficha_inscricao2")
	public FichaInscricao getFichaInscricao2() {
		return fichaInscricao2;
	}

	public void setFichaInscricao2(FichaInscricao fichaInscricao2) {
		this.fichaInscricao2 = fichaInscricao2;
	}

	@Column(name = "fc_numero_dupla")
	public Long getNumeroDupla() {
		return numeroDupla;
	}

	public void setNumeroDupla(Long numeroDupla) {
		this.numeroDupla = numeroDupla;
	}

	@ManyToOne
	@JoinColumn(name = "fc_ordem_entrada_id")
	public OrdemEntrada getOrdemEntrada() {
		return ordemEntrada;
	}

	public void setOrdemEntrada(OrdemEntrada ordemEntrada) {
		this.ordemEntrada = ordemEntrada;
	}

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "fc_passada")
	public Passada getPassada() {
		return passada;
	}

	public void setPassada(Passada passada) {
		this.passada = passada;
	}

	@Enumerated(EnumType.STRING)
	@Column(name = "fc_tipo_folha", length = 15)
	public TipoFolha getTipoFolha() {
		return tipoFolha;
	}

	public void setTipoFolha(TipoFolha tipoFolha) {
		this.tipoFolha = tipoFolha;
	}

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "fc_us_alteracao")
	public Usuario getUsuarioAlteracao() {
		return usuarioAlteracao;
	}

	public void setUsuarioAlteracao(Usuario usuarioAlteracao) {
		this.usuarioAlteracao = usuarioAlteracao;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "fc_data_alteracao")
	public Date getDataAlteracao() {
		return dataAlteracao;
	}

	public void setDataAlteracao(Date dataAlteracao) {
		this.dataAlteracao = dataAlteracao;
	}

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "fc_ocorrencia")
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
