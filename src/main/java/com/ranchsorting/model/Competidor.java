package com.ranchsorting.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class Competidor implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	private String nome;
	private Date dataNascimento;
	private Long idade;
	private String responsavel;
	private String docResponsavel;
	private Animal animal;
	private String contato;
	private Etnia etnia;
	private BigDecimal valorPagoAnuidade;
	private Date dataPagamentoAnuidade;
	private Usuario usuarioAlteracao;
	private Date dataAlteracao;
	private Ocorrencia ocorrencia;

	public final Long getId() {
		return id;
	}

	public final void setId(Long id) {
		this.id = id;
	}

	public final String getNome() {
		return nome;
	}

	public final void setNome(String nome) {
		this.nome = nome;
	}

	public final Date getDataNascimento() {
		return dataNascimento;
	}

	public final void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public final Long getIdade() {
		return idade;
	}

	public final void setIdade(Long idade) {
		this.idade = idade;
	}

	public final String getResponsavel() {
		return responsavel;
	}

	public final void setResponsavel(String responsavel) {
		this.responsavel = responsavel;
	}

	public final String getDocResponsavel() {
		return docResponsavel;
	}

	public final void setDocResponsavel(String docResponsavel) {
		this.docResponsavel = docResponsavel;
	}

	public final Animal getAnimal() {
		return animal;
	}

	public final void setAnimal(Animal animal) {
		this.animal = animal;
	}

	public final String getContato() {
		return contato;
	}

	public final void setContato(String contato) {
		this.contato = contato;
	}

	public final Etnia getEtnia() {
		return etnia;
	}

	public final void setEtnia(Etnia etnia) {
		this.etnia = etnia;
	}

	public final BigDecimal getValorPagoAnuidade() {
		return valorPagoAnuidade;
	}

	public final void setValorPagoAnuidade(BigDecimal valorPagoAnuidade) {
		this.valorPagoAnuidade = valorPagoAnuidade;
	}

	public final Date getDataPagamentoAnuidade() {
		return dataPagamentoAnuidade;
	}

	public final void setDataPagamentoAnuidade(Date dataPagamentoAnuidade) {
		this.dataPagamentoAnuidade = dataPagamentoAnuidade;
	}

	public final Usuario getUsuarioAlteracao() {
		return usuarioAlteracao;
	}

	public final void setUsuarioAlteracao(Usuario usuarioAlteracao) {
		this.usuarioAlteracao = usuarioAlteracao;
	}

	public final Date getDataAlteracao() {
		return dataAlteracao;
	}

	public final void setDataAlteracao(Date dataAlteracao) {
		this.dataAlteracao = dataAlteracao;
	}

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
