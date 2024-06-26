package com.ranchsorting.model;

import java.io.Serializable;  
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

@Entity
@Table(name = "tb_animal")
//@Cache(usage = CacheConcurrencyStrategy.TRANSACTIONAL)
@NamedQueries({
	/*@NamedQuery(name="Animal.buscarAnimaisPaginacao", query="select a "
																+ "from Animal a "),*/
	@NamedQuery(name="Animal.buscarAnimaisPorId", query="select a, a.competidor "
														+ "from Animal a "
																+ "where a.id=:id")
})
public class Animal implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	private String nome; // OK
	private Long idade; // OK
	private String cor; // OK
	private Etnia etnia; // OK
	private String raca; // OK
	private Competidor competidor; // OK
	private Usuario usuarioAlteracao; // OK
	private Date dataAlteracao; // OK
	private Ocorrencia ocorrencia; // Falta implementar

	@Id
	@GeneratedValue
	@Column(name = "an_id")
	public final Long getId() {
		return id;
	}

	public final void setId(Long id) {
		this.id = id;
	}

	@NotBlank
	@Size(max=120)
	@Column(name = "an_nome", nullable = false, length = 120)
	public final String getNome() {
		return nome;
	}

	public final void setNome(String nome) {
		this.nome = nome;
	}

	@Column(name = "an_idade")
	public final Long getIdade() {
		return idade;
	}

	public final void setIdade(Long idade) {
		this.idade = idade;
	}

	@Size(max=50)
	@Column(name = "an_cor", length = 50)
	public final String getCor() {
		return cor;
	}

	public final void setCor(String cor) {
		this.cor = cor;
	}

	@Enumerated(EnumType.STRING)// armazena String no banco de dados ao invés de indice
	@Column(name = "an_etnia", length = 15)
	public final Etnia getEtnia() {
		return etnia;
	}

	public final void setEtnia(Etnia etnia) {
		this.etnia = etnia;
	}

	@Size(max=80)
	@Column(name = "an_raca", length = 80)
	public final String getRaca() {
		return raca;
	}

	public final void setRaca(String raca) {
		this.raca = raca;
	}

	@ManyToOne(fetch=FetchType.LAZY) // tratar com Lazy
	@JoinColumn(name = "an_competidor_id")
	public final Competidor getCompetidor() {
		return competidor;
	}

	public final void setCompetidor(Competidor competidor) {
		this.competidor = competidor;
	}

	@OneToOne(fetch=FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "an_us_alteracao")
	public final Usuario getUsuarioAlteracao() {
		return usuarioAlteracao;
	}

	public final void setUsuarioAlteracao(Usuario usuarioAlteracao) {
		this.usuarioAlteracao = usuarioAlteracao;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "an_data_alteracao")
	public final Date getDataAlteracao() {
		return dataAlteracao;
	}

	public final void setDataAlteracao(Date dataAlteracao) {
		this.dataAlteracao = dataAlteracao;
	}

  	@OneToOne(fetch=FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "an_ocorrencia")
	public final Ocorrencia getOcorrencia() {
		return ocorrencia;
	}

	public final void setOcorrencia(Ocorrencia ocorrencia) {
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
		Animal other = (Animal) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
