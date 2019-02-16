package com.ranchsorting.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "tb_folha_competicao")
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
	private String situacaoFicha1;
	private Competidor competidor2;
	private Animal animal2;
	private FichaInscricao fichaInscricao2;
	private String situacaoFicha2;
	private Long numeroDupla;
	private OrdemEntrada ordemEntrada;
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

	@NotNull
	@Column(name = "fc_codigo_folha", nullable = false)
	public Long getCodigoFolha() {
		return codigoFolha;
	}

	public void setCodigoFolha(Long codigoFolha) {
		this.codigoFolha = codigoFolha;
	}

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "fc_campeonato")
	public Campeonato getCampeonato() {
		return campeonato;
	}

	public void setCampeonato(Campeonato campeonato) {
		this.campeonato = campeonato;
	}

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "fc_etapa")
	public Etapa getEtapa() {
		return etapa;
	}

	public void setEtapa(Etapa etapa) {
		this.etapa = etapa;
	}

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "fc_divisao")
	public Divisao getDivisao() {
		return divisao;
	}

	public void setDivisao(Divisao divisao) {
		this.divisao = divisao;
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
	@JoinColumn(name = "fc_competidor1")
	public Competidor getCompetidor1() {
		return competidor1;
	}

	public void setCompetidor1(Competidor competidor1) {
		this.competidor1 = competidor1;
	}

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "fc_animal1")
	public Animal getAnimal1() {
		return animal1;
	}

	public void setAnimal1(Animal animal1) {
		this.animal1 = animal1;
	}

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "fc_ficha_inscricao1")
	public FichaInscricao getFichaInscricao1() {
		return fichaInscricao1;
	}

	public void setFichaInscricao1(FichaInscricao fichaInscricao1) {
		this.fichaInscricao1 = fichaInscricao1;
	}

	@Size(max=10)
	@Column(name = "fc_situ_ficha1", length = 10)
	public String getSituacaoFicha1() {
		return situacaoFicha1;
	}

	public void setSituacaoFicha1(String situacaoFicha1) {
		this.situacaoFicha1 = situacaoFicha1;
	}

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "fc_competidor2")
	public Competidor getCompetidor2() {
		return competidor2;
	}

	public void setCompetidor2(Competidor competidor2) {
		this.competidor2 = competidor2;
	}

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "fc_animal2")
	public Animal getAnimal2() {
		return animal2;
	}

	public void setAnimal2(Animal animal2) {
		this.animal2 = animal2;
	}

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "fc_ficha_inscricao2")
	public FichaInscricao getFichaInscricao2() {
		return fichaInscricao2;
	}

	public void setFichaInscricao2(FichaInscricao fichaInscricao2) {
		this.fichaInscricao2 = fichaInscricao2;
	}

	@Size(max=10)
	@Column(name = "fc_situ_ficha2", nullable=false, length = 10)
	public String getSituacaoFicha2() {
		return situacaoFicha2;
	}

	public void setSituacaoFicha2(String situacaoFicha2) {
		this.situacaoFicha2 = situacaoFicha2;
	}

	@NotNull
	@Column(name = "fc_numero_dupla", nullable = false)
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
