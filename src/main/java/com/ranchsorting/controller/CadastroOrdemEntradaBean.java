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
import com.ranchsorting.model.Passada;
import com.ranchsorting.model.TipoFicha;
import com.ranchsorting.repository.Campeonatos;
import com.ranchsorting.repository.Divisoes;
import com.ranchsorting.repository.Etapas;
import com.ranchsorting.repository.FichasInscricoes;
import com.ranchsorting.repository.Passadas;
import com.ranchsorting.repository.filter.FichaInscricaoFilter;
import com.ranchsorting.repository.filter.FolhaCompeticaoFilter;
import com.ranchsorting.repository.filter.PassadaFilter;
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
	private FichasInscricoes fichasInscricoes;

	@Inject
	private CadastroOrdemEntradaService cadastroOrdemEntradaService;

	private List<Campeonato> todosCampeonatos;
	private List<Etapa> etapasCampeonatos;
	private List<Divisao> todasDivisoes;
	private List<FichaInscricao> fichasFiltradas;
	private List<FichaInscricao> fichasSelecionadas;
	private List<Passada> passadasCompetidores;

	private OrdemEntrada ordemEntrada;
	private FichaInscricaoFilter fichaInscricaoFilter;

	private FichaInscricao fichaInscricaoLinhaEditavel;

	private Long qntCompetidores;

	public CadastroOrdemEntradaBean() {
		limpar();
	}

	public void inicializar() {
		todosCampeonatos = campeonatos.todosCampeonatos();
		todasDivisoes = divisoes.todasDivisoes();
	}

	public void limpar() {
		ordemEntrada = new OrdemEntrada();
		passadasCompetidores = new ArrayList<>();
		fichaInscricaoFilter = new FichaInscricaoFilter();
		fichasSelecionadas = new ArrayList<>();
	}

	public void salvar() {

		for (Passada p : passadasCompetidores) {

			p.setOrdemEntrada(ordemEntrada);

		}
		this.ordemEntrada.getPassadas().addAll(passadasCompetidores);
		this.ordemEntrada = cadastroOrdemEntradaService.salvar(ordemEntrada);

		limpar();

		FacesUtil.addInfoMessage("Ordem de entrada registrada com sucesso!");

	}

	public void carregarCompetidores() {
		fichasFiltradas = fichasInscricoes.filtradas(fichaInscricaoFilter);
		qntCompetidores = Long.valueOf(fichasFiltradas.size());

	}

	public void carregarEtapas() {
		etapasCampeonatos = etapas.etapasDoCampeonato(this.fichaInscricaoFilter.getObjCampeonato());
	}

	public void carregarDataEtapa() {
		// this.ordemEntrada.setData(this.passadaFilter.getObjEtapa().getDataEvento());
	}

	public void gerarOrdemEntrada() {

		List<Integer> embaralhados = new ArrayList<>();

		for (int i = 0; i < passadasCompetidores.size(); i++) {
			embaralhados.add(i + 1);
		}

		Collections.shuffle(embaralhados);

		for (int i = 0; i < passadasCompetidores.size(); i++) {
			passadasCompetidores.get(i).setNumeroDupla(Long.valueOf(embaralhados.get(i)));
		}

		/*
		 * embaralhar numero da dupla List<Integer> numeros = new
		 * ArrayList<Integer>(); for (int i = 0; i < 26; i++) { numeros.add(i);
		 * } Collections.shuffle(numeros); System.out.print("Sorteados: "); for
		 * (int i = 0; i < 15; i++) { System.out.print(numeros.get(i));
		 * System.out.print(" "); } System.out.println();
		 */
	}

	public List<FichaInscricao> completarCompetidores(String nomeCompetidor) {

		// busca informções para o autoComplete
		fichaInscricaoFilter.setCompetidor(nomeCompetidor);
		fichasFiltradas = fichasInscricoes.filtradas(fichaInscricaoFilter);

		return fichasFiltradas;
	}

	public void incluirFichasCompetidores() {

		// se a fichaInscricao linha editável não for nula
		if (this.fichaInscricaoLinhaEditavel != null) {

			if(fichaInscricaoLinhaEditavel.getDivisao().getTipoFicha().equals(TipoFicha.INDIVIDUAL) 
					&& fichasSelecionadas.size() < 1){
				fichasSelecionadas.add(fichaInscricaoLinhaEditavel);	
				
			} else if(fichaInscricaoLinhaEditavel.getDivisao().getTipoFicha().equals(TipoFicha.DUPLA) 
					&& fichasSelecionadas.size() < 2){
				fichasSelecionadas.add(fichaInscricaoLinhaEditavel);
				
			}  else if(fichaInscricaoLinhaEditavel.getDivisao().getTipoFicha().equals(TipoFicha.TRIO) 
					&& fichasSelecionadas.size() < 3){
				fichasSelecionadas.add(fichaInscricaoLinhaEditavel);
				
			} else {
				FacesUtil.addInfoMessage("Quantidade de fichas selecionadas já atingiu o limite da Divisão " + 
						fichaInscricaoLinhaEditavel.getDivisao().getNome());
			}						

			this.fichaInscricaoLinhaEditavel = null;
		}
	}
	
	public void limparListaFichasInscricoes(){
		if(fichasSelecionadas != null && fichasSelecionadas.size() != 0){
			fichasSelecionadas = new ArrayList<>();
		}
	}
	
	public void incluirPassada(){
		Passada passada = new Passada();
		
		// adiciona a ficha como item de passada.
		passada.getFichasInscricoes().addAll(fichasSelecionadas);
		
		passadasCompetidores.add(passada);	
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

	public List<Passada> getPassadasCompetidores() {
		return passadasCompetidores;
	}

	public FichaInscricaoFilter getFichaInscricaoFilter() {
		return fichaInscricaoFilter;
	}

	public void setFichaInscricaoFilter(FichaInscricaoFilter fichaInscricaoFilter) {
		this.fichaInscricaoFilter = fichaInscricaoFilter;
	}

	public List<FichaInscricao> getFichasFiltradas() {
		return fichasFiltradas;
	}

	public List<FichaInscricao> getFichasSelecionadas() {
		return fichasSelecionadas;
	}

	public void setFichasSelecionadas(List<FichaInscricao> fichasSelecionadas) {
		this.fichasSelecionadas = fichasSelecionadas;
	}

	public Long getQntCompetidores() {
		return qntCompetidores;
	}

	public void setQntCompetidores(Long qntCompetidores) {
		this.qntCompetidores = qntCompetidores;
	}

	public FichaInscricao getFichaInscricaoLinhaEditavel() {
		return fichaInscricaoLinhaEditavel;
	}

	public void setFichaInscricaoLinhaEditavel(FichaInscricao fichaInscricaoLinhaEditavel) {
		this.fichaInscricaoLinhaEditavel = fichaInscricaoLinhaEditavel;
	}

	public boolean isEditando() {
		return this.ordemEntrada.getId() != null;
	}

}
