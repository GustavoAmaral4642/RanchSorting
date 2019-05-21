package com.ranchsorting.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.ranchsorting.model.Campeonato;
import com.ranchsorting.model.Divisao;
import com.ranchsorting.model.Etapa;
import com.ranchsorting.model.FichaInscricao;
import com.ranchsorting.model.FolhaCompeticao;
import com.ranchsorting.model.StatusFicha;
import com.ranchsorting.model.TipoFolha;
import com.ranchsorting.repository.Campeonatos;
import com.ranchsorting.repository.Divisoes;
import com.ranchsorting.repository.Etapas;
import com.ranchsorting.repository.FichasInscricoes;
import com.ranchsorting.repository.filter.FichaInscricaoFilter;
import com.ranchsorting.service.CadastroFolhaCompeticaoService;
import com.ranchsorting.util.jsf.FacesUtil;

@Named
@ViewScoped
public class CadastroFolhaAutomaticaBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private CadastroFolhaCompeticaoService cadastroFolhaCompeticaoService;

	@Inject
	private FichasInscricoes fichasIncricoes;

	@Inject
	private Campeonatos campeonatos;

	@Inject
	private Etapas etapas;

	@Inject
	private Divisoes divisoes;

	private FichaInscricaoFilter filtroFichaInscricao;

	private FolhaCompeticao folhaCompeticao;

	private List<Campeonato> todosCampeonatos;
	private List<Etapa> etapasCampeonatos;
	private List<Divisao> todasDivisoes;
	private List<FichaInscricao> fichasAutomaticas1;
	private List<FichaInscricao> fichasAutomaticas2;
	private List<FolhaCompeticao> folhas;

	public CadastroFolhaAutomaticaBean() {
		limpar();
	}

	public void inicializar() {
		todosCampeonatos = campeonatos.todosCampeonatos();
		todasDivisoes = divisoes.todasDivisoes();
	}

	public void limpar() {
		filtroFichaInscricao = new FichaInscricaoFilter();
		folhaCompeticao = new FolhaCompeticao();
		fichasAutomaticas2 = new ArrayList<>();

	}

	public void salvar() {
		folhas = cadastroFolhaCompeticaoService.salvarColecao(folhas);
		limpar();
		FacesUtil.addInfoMessage("Folha de Competição registrada com sucesso!");
	}

	public void carregarListagemFichas() {
		folhas = new ArrayList<>();

		fichasAutomaticas1 = fichasIncricoes.filtradas(filtroFichaInscricao);
		Collections.shuffle(fichasAutomaticas1);

		boolean verificador = true;

		int aux = 0;
		int contador = fichasAutomaticas1.size() / 2;
		System.out.println(contador);

		FolhaCompeticao fc = new FolhaCompeticao();

		while (verificador) {
			if (aux == fichasAutomaticas1.size()) {
				verificador = false;
				break;
			} else if (aux % 2 == 0) {
				fc.setFichaInscricao1(fichasAutomaticas1.get(aux));
				aux++;
				System.out.println(aux + "passo aqui");
			} else if (aux % 2 != 0) {
				fc.setFichaInscricao2(fichasAutomaticas1.get(aux));
				aux++;
				System.out.println(aux + "passo aqui");
			}
			fc.setTipoFolha(TipoFolha.AUTOMATICA);
			fc.setData(folhaCompeticao.getData());
			
			if (aux % 2 != 0) {
				folhas.add(fc);
			}
		}

		/*
		 * Collections.shuffle(fichasAutomaticas2);
		 * 
		 * for(int i=0; i<fichasAutomaticas1.size(); i++){ FolhaCompeticao fc =
		 * new FolhaCompeticao();
		 * fc.setFichaInscricao1(fichasAutomaticas1.get(i));
		 * fc.setFichaInscricao2(fichasAutomaticas2.get(i));
		 * fc.setTipoFolha(TipoFolha.AUTOMATICA);
		 * fc.setData(folhaCompeticao.getData()); folhas.add(fc); }
		 */
	}

	public void carregarEtapas() {
		etapasCampeonatos = etapas.etapasDoCampeonato(filtroFichaInscricao.getObjCampeonato());
	}

	public void carregarDataEtapa() {
		if (filtroFichaInscricao.getObjEtapa() != null) {
			folhaCompeticao.setData(filtroFichaInscricao.getObjEtapa().getDataEvento());
		}
	}

	public List<FichaInscricao> getFichasAutomaticas1() {
		return fichasAutomaticas1;
	}

	public List<FichaInscricao> getFichasAutomaticas2() {
		return fichasAutomaticas2;
	}

	public FolhaCompeticao getFolhaCompeticao() {
		return folhaCompeticao;
	}

	public void setFolhaCompeticao(FolhaCompeticao folhaCompeticao) {
		this.folhaCompeticao = folhaCompeticao;
	}

	public FichaInscricaoFilter getFiltroFichaInscricao() {
		return filtroFichaInscricao;
	}

	public void setFiltroFichaInscricao(FichaInscricaoFilter filtroFichaInscricao) {
		this.filtroFichaInscricao = filtroFichaInscricao;
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

	public List<FolhaCompeticao> getFolhas() {
		return folhas;
	}

	public void setFolhas(List<FolhaCompeticao> folhas) {
		this.folhas = folhas;
	}

	public boolean isEditando() {
		return this.folhaCompeticao.getId() != null;
	}
}
