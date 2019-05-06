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
import com.ranchsorting.model.TipoFolha;
import com.ranchsorting.repository.Animais;
import com.ranchsorting.repository.Campeonatos;
import com.ranchsorting.repository.Competidores;
import com.ranchsorting.repository.Divisoes;
import com.ranchsorting.repository.Etapas;
import com.ranchsorting.repository.FichasInscricoes;
import com.ranchsorting.repository.filter.FichaInscricaoFilter;
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
	private FichaInscricaoFilter filtroFichaInscricao;

	private List<Competidor> todosCompetidores1;
	private List<Competidor> todosCompetidores2;
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
		todosCompetidores1 = competidores.todosCompetidores();
		todosCompetidores2 = competidores.todosCompetidores();
		todosAnimais = animais.todosAnimais();
		todosCampeonatos = campeonatos.todosCampeonatos();
		todasDivisoes = divisoes.todasDivisoes();

		// se não for atualização de página, carrega listagem
		if (FacesUtil.isNotPostback()) {
			if (isEditando()) {
				filtro.setObjCampeonato(folhaCompeticao.getFichaInscricao1().getCampeonato());
				carregarEtapas();
				filtro.setObjEtapa(folhaCompeticao.getFichaInscricao1().getEtapa());
				filtro.setObjDivisao(folhaCompeticao.getFichaInscricao1().getDivisao());
				carregarDataEtapa();
				filtro.setObjCompetidor1(folhaCompeticao.getFichaInscricao1().getCompetidor());
				filtro.setObjCompetidor2(folhaCompeticao.getFichaInscricao2().getCompetidor());
				carregarFichasCompetidor1();
				carregarFichasCompetidor2();
			}
		}
	}

	public void limpar() {
		folhaCompeticao = new FolhaCompeticao();
		filtro = new FolhaCompeticaoFilter();
		fichasCompetidores1 = new ArrayList<>();
		fichasCompetidores2 = new ArrayList<>();
		filtroFichaInscricao = new FichaInscricaoFilter();
	}

	public void salvar() {
		
		if (!isEditando()) {
			this.folhaCompeticao.setTipoFolha(TipoFolha.MANUAL);
		}
		this.folhaCompeticao = cadastroFolhaCompeticaoService.salvar(folhaCompeticao);

		limpar();

		FacesUtil.addInfoMessage("Folha de Competição registrada com sucesso!");
	}

	public void carregarEtapas() {
		etapasCampeonatos = etapas.etapasDoCampeonato(filtro.getObjCampeonato());
	}

	public void carregarDataEtapa() {
		if (filtro.getObjEtapa() != null) {
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

	public List<Competidor> getTodosCompetidores1() {
		return todosCompetidores1;
	}

	public void setTodosCompetidores1(List<Competidor> todosCompetidores1) {
		this.todosCompetidores1 = todosCompetidores1;
	}

	public List<Competidor> getTodosCompetidores2() {
		return todosCompetidores2;
	}

	public void setTodosCompetidores2(List<Competidor> todosCompetidores2) {
		this.todosCompetidores2 = todosCompetidores2;
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

	public FichaInscricaoFilter getFiltroFichaInscricao() {
		return filtroFichaInscricao;
	}

	public void setFiltroFichaInscricao(FichaInscricaoFilter filtroFichaInscricao) {
		this.filtroFichaInscricao = filtroFichaInscricao;
	}

	public boolean isEditando() {
		return this.folhaCompeticao.getId() != null;
	}
}
