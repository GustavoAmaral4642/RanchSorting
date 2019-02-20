package com.ranchsorting.repository.filter;

import java.io.Serializable;
import java.util.Date;

public class CompetidorFilter implements Serializable {

	private static final long serialVersionUID = 1L;

	private String nome;
	private Date dtNascimentoDe;
	private Date dtNascimentoAte;
	private String responsavel;
	private String contato;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Date getDtNascimentoDe() {
		return dtNascimentoDe;
	}

	public void setDtNascimentoDe(Date dtNascimentoDe) {
		this.dtNascimentoDe = dtNascimentoDe;
	}

	public Date getDtNascimentoAte() {
		return dtNascimentoAte;
	}

	public void setDtNascimentoAte(Date dtNascimentoAte) {
		this.dtNascimentoAte = dtNascimentoAte;
	}

	public String getResponsavel() {
		return responsavel;
	}

	public void setResponsavel(String responsavel) {
		this.responsavel = responsavel;
	}

	public String getContato() {
		return contato;
	}

	public void setContato(String contato) {
		this.contato = contato;
	}

}