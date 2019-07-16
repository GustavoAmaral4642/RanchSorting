package com.ranchsorting.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.event.RowEditEvent;

import com.ranchsorting.model.OrdemEntrada;
import com.ranchsorting.model.Passada;
import com.ranchsorting.service.CadastroOrdemEntradaService;
import com.ranchsorting.util.jsf.FacesUtil;

import javassist.expr.NewArray;

@Named
@ViewScoped
public class ExecutarOrdemEntradaBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private CadastroOrdemEntradaService cadastroOrdemEntradaService;
	
	private List<Passada> passadasCompetidores;
	
	private OrdemEntrada ordemEntrada;
	
	public ExecutarOrdemEntradaBean() {
		limpar();
	}

	public void inicializar() {
		if (FacesUtil.isNotPostback()) {
			if (isEditando()) {
				passadasCompetidores.addAll(ordemEntrada.getPassadas());
			}
		}
	}

	public void limpar() {
		ordemEntrada = new OrdemEntrada();
		passadasCompetidores = new ArrayList<>();
	}

	public void salvar() {
		
		this.ordemEntrada.getPassadas().addAll(passadasCompetidores);
		this.ordemEntrada = cadastroOrdemEntradaService.salvar(ordemEntrada);

		FacesUtil.addInfoMessage("Ordem de entrada registrada com sucesso!");
		
		//testar save que está zicando a listagem
		passadasCompetidores = ordemEntrada.getPassadas();
	}

	public void onRowEdit(RowEditEvent event) {
		FacesMessage msg = new FacesMessage("Passada editada", ((Passada) event.getObject()).getId().toString());
		FacesContext.getCurrentInstance().addMessage(null, msg);
		salvar();
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

	public List<Passada> getPassadasCompetidores() {
		return passadasCompetidores;
	}

	public void setPassadasCompetidores(List<Passada> passadasCompetidores) {
		this.passadasCompetidores = passadasCompetidores;
	}

	public boolean isEditando() {
		return this.ordemEntrada.getId() != null;
	}

}
