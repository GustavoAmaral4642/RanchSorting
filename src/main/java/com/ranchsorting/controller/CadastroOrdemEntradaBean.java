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
import com.ranchsorting.model.OrdemEntrada;
import com.ranchsorting.repository.Campeonatos;
import com.ranchsorting.repository.Divisoes;
import com.ranchsorting.repository.Etapas;
import com.ranchsorting.repository.FichasInscricoes;
import com.ranchsorting.repository.filter.FichaInscricaoFilter;
import com.ranchsorting.repository.filter.FolhaCompeticaoFilter;
import com.ranchsorting.service.CadastroOrdemEntradaService;
import com.ranchsorting.util.jsf.FacesUtil;

@Named
@ViewScoped
public class CadastroOrdemEntradaBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private Campeonatos campeonatos;

	@Inject
	private Etapas etapas;

	@Inject
	private Divisoes divisoes;

	@Inject
	private FichasInscricoes fichas;

	@Inject
	private CadastroOrdemEntradaService cadastroOrdemEntradaService;

	private List<Campeonato> todosCampeonatos;
	private List<Etapa> etapasCampeonatos;
	private List<Divisao> todasDivisoes;
	private List<FichaInscricao> fichasCompetidores;

	private OrdemEntrada ordemEntrada;
	private FichaInscricaoFilter fichaInscricaoFilter;

	public CadastroOrdemEntradaBean() {
		limpar();
	}

	public void inicializar() {
		todosCampeonatos = campeonatos.todosCampeonatos();
		todasDivisoes = divisoes.todasDivisoes();
	}

	public void limpar() {
		ordemEntrada = new OrdemEntrada();
		fichasCompetidores = new ArrayList<>();
		fichaInscricaoFilter = new FichaInscricaoFilter();
	}

	public void salvar() {

		this.ordemEntrada = cadastroOrdemEntradaService.salvar(ordemEntrada);

		limpar();

		FacesUtil.addInfoMessage("Ordem de entrada registrada com sucesso!");

	}

	public void carregarCompetidores() {
		fichasCompetidores = fichas.filtradas(fichaInscricaoFilter);
	}

	public void carregarEtapas() {
		etapasCampeonatos = etapas.etapasDoCampeonato(this.fichaInscricaoFilter.getObjCampeonato());
	}

	public void carregarDataEtapa() {
		this.ordemEntrada.setData(this.fichaInscricaoFilter.getObjEtapa().getDataEvento());
	}

	public void gerarOrdemEntrada() {

		List<Integer> embaralhados = new ArrayList<>();

		for (int i = 0; i < fichasCompetidores.size(); i++) {
			embaralhados.add(i+1);
		}

		Collections.shuffle(embaralhados);

		for (int i = 0; i < fichasCompetidores.size(); i++) {
			fichasCompetidores.get(i).setNumeroDupla(Long.valueOf(embaralhados.get(i)));
		}

		/*
		 * embaralhar numero da dupla List<Integer> numeros = new
		 * ArrayList<Integer>(); for (int i = 0; i < 26; i++) { numeros.add(i);
		 * } Collections.shuffle(numeros); System.out.print("Sorteados: "); for
		 * (int i = 0; i < 15; i++) { System.out.print(numeros.get(i));
		 * System.out.print(" "); } System.out.println();
		 */
	}

	public OrdemEntrada getOrdemEntrada() {
		return ordemEntrada;
	}

	public void setOrdemEntrada(OrdemEntrada ordemEntrada) {
		this.ordemEntrada = ordemEntrada;
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

	public List<FichaInscricao> getFichasCompetidores() {
		return fichasCompetidores;
	}

	public FichaInscricaoFilter getFichaInscricaoFilter() {
		return fichaInscricaoFilter;
	}

	public void setFichaInscricaoFilter(FichaInscricaoFilter fichaInscricaoFilter) {
		this.fichaInscricaoFilter = fichaInscricaoFilter;
	}

	public boolean isEditando() {
		return this.ordemEntrada.getId() != null;
	}

}
