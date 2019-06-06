package com.ranchsorting.model;

import java.io.Serializable;
import java.util.ArrayList;
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
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

@Entity
@Table(name = "tb_competidor")
public class Competidor implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	private String nome; // ok
	private Date dataNascimento; // OK
	private Long idade; // OK
	private String responsavel; // OK
	private String docResponsavel;// OK
	private List<Animal> animais = new ArrayList<>(); // OK
	private String contato;// OK
	private Etnia etnia;// OK
	private Usuario usuarioAlteracao;// OK
	private Date dataAlteracao;// OK
	private Ocorrencia ocorrencia;// Falta implementar

	@Id
	@GeneratedValue
	@Column(name = "cp_id")
	public final Long getId() {
		return id;
	}

	public final void setId(Long id) {
		this.id = id;
	}

	@NotBlank
	@Size(max = 160)
	@Column(name = "cp_nome", nullable = false, length = 160)
	public final String getNome() {
		return nome;
	}

	public final void setNome(String nome) {
		this.nome = nome;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "cp_dt_nascimento")
	public final Date getDataNascimento() {
		return dataNascimento;
	}

	public final void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	@NotNull
	@Column(name = "cp_idade", nullable = false)
	public final Long getIdade() {
		return idade;
	}

	public final void setIdade(Long idade) {
		this.idade = idade;
	}

	@Size(max = 160)
	@Column(name = "cp_responsavel", length = 160)
	public final String getResponsavel() {
		return responsavel;
	}

	public final void setResponsavel(String responsavel) {
		this.responsavel = responsavel;
	}

	@Size(max = 50)
	@Column(name = "cp_doc_responsavel", length = 50)
	public final String getDocResponsavel() {
		return docResponsavel;
	}

	public final void setDocResponsavel(String docResponsavel) {
		this.docResponsavel = docResponsavel;
	}

	@OneToMany(mappedBy = "competidor", cascade = CascadeType.ALL)
	public List<Animal> getAnimais() {
		return animais;
	}

	public void setAnimais(List<Animal> animais) {
		this.animais = animais;
	}

	@Size(max = 160)
	@Column(name = "cp_contato", length = 160)
	public final String getContato() {
		return contato;
	}

	public final void setContato(String contato) {
		this.contato = contato;
	}

	@NotNull
	@Enumerated(EnumType.STRING)
	@Column(name = "cp_etnia", nullable = false, length = 15)
	public final Etnia getEtnia() {
		return etnia;
	}

	public final void setEtnia(Etnia etnia) {
		this.etnia = etnia;
	}

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "cp_us_alteracao")
	public final Usuario getUsuarioAlteracao() {
		return usuarioAlteracao;
	}

	public final void setUsuarioAlteracao(Usuario usuarioAlteracao) {
		this.usuarioAlteracao = usuarioAlteracao;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "cp_data_alteracao")
	public final Date getDataAlteracao() {
		return dataAlteracao;
	}

	public final void setDataAlteracao(Date dataAlteracao) {
		this.dataAlteracao = dataAlteracao;
	}

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "cp_ocorrencia")
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
		Competidor other = (Competidor) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
