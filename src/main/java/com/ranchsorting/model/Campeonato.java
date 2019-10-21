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
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.validator.constraints.NotBlank;

@Entity
@Table(name = "tb_campeonato")
// @Cache(usage = CacheConcurrencyStrategy.TRANSACTIONAL)
@NamedQueries({
	@NamedQuery(name="Campeonato.buscarCampeonatoPorId", query="select c "
														+ "from Campeonato c "
															+ "JOIN c.etapas e "
															+ "where c.id=:id"),
	@NamedQuery(name="Campeonato.buscarTodosCampeonatos", query="select c "
														+ "from Campeonato c ")
})
public class Campeonato implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	private String nome;
	private Date dataAbertura;
	private Date dataTermino;
	private String observacao;
	private BigDecimal valorAnuidade;
	private TipoCampeonato tipoCampeonato;
	private List<Etapa> etapas;
	private Long qntBoiada;
	private Usuario usuarioAlteracao;
	private Date dataAlteracao;
	private Ocorrencia ocorrencia;

	@Id
	@GeneratedValue
	@Column(name = "cp_id")
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@NotBlank
	@Size(max = 120)
	@Column(name = "cp_nome", nullable = false, length = 120)
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "cp_data_abertura")
	public Date getDataAbertura() {
		return dataAbertura;
	}

	public void setDataAbertura(Date dataAbertura) {
		this.dataAbertura = dataAbertura;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "cp_data_termino")
	public Date getDataTermino() {
		return dataTermino;
	}

	public void setDataTermino(Date dataTermino) {
		this.dataTermino = dataTermino;
	}

	@Column(name = "cp_observacao", columnDefinition = "text")
	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	@Column(name = "cp_valor_anuidade", precision = 10, scale = 2)
	public BigDecimal getValorAnuidade() {
		return valorAnuidade;
	}

	public void setValorAnuidade(BigDecimal valorAnuidade) {
		this.valorAnuidade = valorAnuidade;
	}

	@NotNull
	@Enumerated(EnumType.STRING)
	@Column(name = "cp_tipo_campeonato", nullable = false, length = 15)
	public TipoCampeonato getTipoCampeonato() {
		return tipoCampeonato;
	}

	public void setTipoCampeonato(TipoCampeonato tipoCampeonato) {
		this.tipoCampeonato = tipoCampeonato;
	}

	@OneToMany(fetch=FetchType.LAZY,
			mappedBy = "campeonato", 
			cascade = CascadeType.ALL)
	public List<Etapa> getEtapas() {
		return etapas;
	}

	public void setEtapas(List<Etapa> etapas) {
		this.etapas = etapas;
	}

	@Column(name = "cp_qnt_boiada")
	public Long getQntBoiada() {
		return qntBoiada;
	}

	public void setQntBoiada(Long qntBoiada) {
		this.qntBoiada = qntBoiada;
	}

	@OneToOne(fetch=FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "cp_us_alteracao")
	public Usuario getUsuarioAlteracao() {
		return usuarioAlteracao;
	}

	public void setUsuarioAlteracao(Usuario usuarioAlteracao) {
		this.usuarioAlteracao = usuarioAlteracao;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "cp_data_alteracao")
	public Date getDataAlteracao() {
		return dataAlteracao;
	}

	public void setDataAlteracao(Date dataAlteracao) {
		this.dataAlteracao = dataAlteracao;
	}

	@OneToOne(fetch=FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "cp_ocorrencia")
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
		Campeonato other = (Campeonato) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
