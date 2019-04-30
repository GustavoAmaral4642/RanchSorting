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
import com.ranchsorting.model.FolhaCompeticao;
import com.ranchsorting.model.OrdemEntrada;
import com.ranchsorting.model.Passada;
import com.ranchsorting.repository.Campeonatos;
import com.ranchsorting.repository.Divisoes;
import com.ranchsorting.repository.Etapas;
import com.ranchsorting.repository.FolhasCompeticoes;
import com.ranchsorting.repository.filter.FolhaCompeticaoFilter;
import com.ranchsorting.repository.filter.OrdemEntradaFilter;
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
	private FolhasCompeticoes folhasCompeticoes;

	@Inject
	private CadastroOrdemEntradaService cadastroOrdemEntradaService;

	private List<Campeonato> todosCampeonatos;
	private List<Etapa> etapasCampeonatos;
	private List<Divisao> todasDivisoes;
	private List<Passada> passadasGeradas;
	private List<FolhaCompeticao> folhas;

	private OrdemEntrada ordemEntrada;
	private FolhaCompeticaoFilter filtroFolhaCompeticao;

	public CadastroOrdemEntradaBean() {
		limpar();
	}

	public void inicializar() {
		todosCampeonatos = campeonatos.todosCampeonatos();
		todasDivisoes = divisoes.todasDivisoes();
	}

	public void limpar() {
		ordemEntrada = new OrdemEntrada();
		filtroFolhaCompeticao = new FolhaCompeticaoFilter();
		folhas = new ArrayList<>();
	}

	public void salvar() {

		ordemEntrada.setFolhasCompeticoes(folhas);
		
		this.ordemEntrada = cadastroOrdemEntradaService.salvar(ordemEntrada);

		limpar();

		FacesUtil.addInfoMessage("Ordem de entrada registrada com sucesso!");

	}

	public void carregarCompetidores() {
		//filtroFolhaCompeticao.setCampeonato(this.ordemEntrada.getCampeonato().getNome());
		//filtroFolhaCompeticao.setEtapa(this.ordemEntrada.getEtapa().getNome());
		//filtroFolhaCompeticao.setDivisao(this.ordemEntrada.getDivisao().getNome());
		folhas = folhasCompeticoes.filtradas(filtroFolhaCompeticao);
	}

	public void carregarEtapas() {
		etapasCampeonatos = etapas.etapasDoCampeonato(this.ordemEntrada.getCampeonato());
	}

	public void carregarDataEtapa() {
		this.ordemEntrada.setData(this.ordemEntrada.getEtapa().getDataEvento());
	}

	public void gerarOrdemEntrada() {

		List<Integer> embaralhados = new ArrayList<Integer>();

		for (int i = 0; i < folhas.size(); i++) {
			embaralhados.add(i + 1);
		}

		Collections.shuffle(embaralhados);

		for (int i = 0; i < folhas.size(); i++) {
			folhas.get(i).setNumeroDupla(Long.valueOf(embaralhados.get(i)));
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

	public List<Passada> getPassadasGeradas() {
		return passadasGeradas;
	}

	public void setPassadasGeradas(List<Passada> passadasGeradas) {
		this.passadasGeradas = passadasGeradas;
	}

	public List<FolhaCompeticao> getFolhas() {
		return folhas;
	}

	public FolhaCompeticaoFilter getFiltroFolhaCompeticao() {
		return filtroFolhaCompeticao;
	}

	public void setFiltroFolhaCompeticao(FolhaCompeticaoFilter filtroFolhaCompeticao) {
		this.filtroFolhaCompeticao = filtroFolhaCompeticao;
	}

	public boolean isEditando() {
		return this.ordemEntrada.getId() != null;
	}

}
