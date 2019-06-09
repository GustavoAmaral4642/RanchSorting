package com.ranchsorting.controller;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import org.primefaces.event.RowEditEvent;

import com.ranchsorting.model.OrdemEntrada;
import com.ranchsorting.model.Passada;
import com.ranchsorting.util.jsf.FacesUtil;

@Named
@ViewScoped
public class ManutencaoOrdemEntradaBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private OrdemEntrada ordemEntrada;

	public ManutencaoOrdemEntradaBean() {
		limpar();
	}

	public void inicializar() {

	}

	public void limpar() {
		ordemEntrada = new OrdemEntrada();
	}

	public void salvar() {

		
		for(Passada p : ordemEntrada.getPassadas()){
			System.out.println(p.getId());
			System.out.println(p.getTempo());
			System.out.println(p.getQntBoi());
			System.out.println(p.getSat());
			
		}
		
		FacesUtil.addInfoMessage("Ordem de entrada registrada com sucesso!");

	}

	public void onRowEdit(RowEditEvent event) {
		FacesMessage msg = new FacesMessage("Passada editada", ((Passada) event.getObject()).getId().toString());
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	public void onRowCancel(RowEditEvent event) {
		FacesMessage msg = new FacesMessage("Edição cancelada", ((Passada) event.getObject()).getId().toString());
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	public OrdemEntrada getOrdemEntrada() {
		return ordemEntrada;
	}

	public void setOrdemEntrada(OrdemEntrada ordemEntrada) {
		this.ordemEntrada = ordemEntrada;
	}

	public boolean isEditando() {
		return this.ordemEntrada.getId() != null;
	}

}
