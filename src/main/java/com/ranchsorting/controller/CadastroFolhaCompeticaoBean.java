package com.ranchsorting.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.ranchsorting.model.Animal;
import com.ranchsorting.model.Campeonato;
import com.ranchsorting.model.Competidor;
import com.ranchsorting.model.Divisao;
import com.ranchsorting.model.Etapa;
import com.ranchsorting.model.FichaInscricao;
import com.ranchsorting.model.FolhaCompeticao;
import com.ranchsorting.repository.Animais;
import com.ranchsorting.repository.Campeonatos;
import com.ranchsorting.repository.Competidores;
import com.ranchsorting.repository.Divisoes;
import com.ranchsorting.repository.Etapas;
import com.ranchsorting.repository.FichasInscricoes;
import com.ranchsorting.repository.filter.FolhaCompeticaoFilter;
import com.ranchsorting.service.CadastroFolhaCompeticaoService;
import com.ranchsorting.util.jsf.FacesUtil;

@Named
@ViewScoped
public class CadastroFolhaCompeticaoBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private CadastroFolhaCompeticaoService cadastroFolhaCompeticaoService;

	@Inject
	private Competidores competidores;

	@Inject
	private Animais animais;

	@Inject
	private Campeonatos campeonatos;

	@Inject
	private Etapas etapas;

	@Inject
	private Divisoes divisoes;

	@Inject
	private FichasInscricoes fichas;

	private FolhaCompeticao folhaCompeticao;
	private FolhaCompeticaoFilter filtro;

	private List<Competidor> todosCompetidores;
	private List<Animal> todosAnimais;
	private List<Campeonato> todosCampeonatos;
	private List<Etapa> etapasCampeonatos;
	private List<Divisao> todasDivisoes;
	private List<FichaInscricao> fichasCompetidores1;
	private List<FichaInscricao> fichasCompetidores2;

	public CadastroFolhaCompeticaoBean() {
		limpar();
	}

	public void inicializar() {
		todosCompetidores = competidores.todosCompetidores();
		todosAnimais = animais.todosAnimais();
		todosCampeonatos = campeonatos.todosCampeonatos();
		todasDivisoes = divisoes.todasDivisoes();
	}

	public void limpar() {
		folhaCompeticao = new FolhaCompeticao();
		filtro = new FolhaCompeticaoFilter();
		fichasCompetidores1 = new ArrayList<>();
		fichasCompetidores2 = new ArrayList<>();
	}

	public void salvar() {
		this.folhaCompeticao = cadastroFolhaCompeticaoService.salvar(folhaCompeticao);

		limpar();

		FacesUtil.addInfoMessage("Folha de Competição registrada com sucesso!");
	}

	public void carregarEtapas() {
		etapasCampeonatos = etapas.etapasDoCampeonato(filtro.getObjCampeonato());		
	}
	
	public void carregarDataEtapa(){
		if(filtro.getObjEtapa() != null){
			folhaCompeticao.setData(filtro.getObjEtapa().getDataEvento());
		}
	}

	public void carregarFichasCompetidor1() {
		fichasCompetidores1 = fichas.carregarFichasCompetidores(filtro.getObjCompetidor1(), filtro.getObjCampeonato(),
				filtro.getObjEtapa(), filtro.getObjDivisao());
	}

	public void carregarFichasCompetidor2() {
		fichasCompetidores2 = fichas.carregarFichasCompetidores(filtro.getObjCompetidor2(), filtro.getObjCampeonato(),
				filtro.getObjEtapa(), filtro.getObjDivisao());
	}

	public FolhaCompeticao getFolhaCompeticao() {
		return folhaCompeticao;
	}

	public void setFolhaCompeticao(FolhaCompeticao folhaCompeticao) {
		this.folhaCompeticao = folhaCompeticao;
	}

	public FolhaCompeticaoFilter getFiltro() {
		return filtro;
	}

	public void setFiltro(FolhaCompeticaoFilter filtro) {
		this.filtro = filtro;
	}

	public List<Campeonato> getTodosCampeonatos() {
		return todosCampeonatos;
	}

	public List<Etapa> getEtapasCampeonatos() {
		return etapasCampeonatos;
	}

	public List<Divisao> getTodasDivisoes() {
		return todasDivisoes;
	}

	public List<Competidor> getTodosCompetidores() {
		return todosCompetidores;
	}

	public List<Animal> getTodosAnimais() {
		return todosAnimais;
	}

	public List<FichaInscricao> getFichasCompetidores1() {
		return fichasCompetidores1;
	}

	public List<FichaInscricao> getFichasCompetidores2() {
		return fichasCompetidores2;
	}

	public boolean isEditando() {
		return this.folhaCompeticao.getId() != null;
	}
}
