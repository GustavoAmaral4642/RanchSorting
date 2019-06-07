package com.ranchsorting.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.ranchsorting.model.Animal;
import com.ranchsorting.model.Anuidade;
import com.ranchsorting.model.Campeonato;
import com.ranchsorting.model.Competidor;
import com.ranchsorting.model.Divisao;
import com.ranchsorting.model.Etapa;
import com.ranchsorting.model.FichaInscricao;
import com.ranchsorting.model.Passada;
import com.ranchsorting.model.StatusFicha;
import com.ranchsorting.model.TipoFicha;
import com.ranchsorting.repository.Animais;
import com.ranchsorting.repository.Campeonatos;
import com.ranchsorting.repository.Competidores;
import com.ranchsorting.repository.Divisoes;
import com.ranchsorting.repository.Etapas;
import com.ranchsorting.service.CadastroFichaInscricaoService;
import com.ranchsorting.service.NegocioException;
import com.ranchsorting.util.jsf.FacesUtil;

@Named
@ViewScoped
public class CadastroFichaInscricaoBean implements Serializable {

	private static final long serialVersionUID = 1L;

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
	private CadastroFichaInscricaoService cadastroFichaInscricaoService;

	private FichaInscricao fichaInscricao;
	private Competidor competidor1;
	private Competidor competidor2;
	private Competidor competidor3;

	private List<Competidor> todosCompetidores;
	private List<Animal> todosAnimais;
	private List<Campeonato> todosCampeonatos;
	private List<Etapa> etapasCampeonatos;
	private List<Divisao> todasDivisoes;
	private List<Anuidade> anuidadesCompetidor;

	public CadastroFichaInscricaoBean() {
		limpar();
	}

	public void inicializar() {
		todosCampeonatos = campeonatos.todosCampeonatos();
		todasDivisoes = divisoes.todasDivisoes();

		if (FacesUtil.isNotPostback()) {
			if (isEditando()) {
				// carregarAnuidadesCompetidor();
				carregarEtapas();
			}
		}

		System.out.println(competidor1.getClass());

	}

	public void limpar() {
		fichaInscricao = new FichaInscricao();
		anuidadesCompetidor = new ArrayList<>();
		competidor1 = new Competidor();// tentar usar destroy
		competidor2 = new Competidor(); // tentar usar destroy
		competidor3 = new Competidor();// tentar usar destroy
		if (!isEditando()) {
			fichaInscricao.setStatusFicha(StatusFicha.CADASTRADA);
		}
	}

	public void salvar() {

		trataPassadasInclusao();

		this.fichaInscricao.setStatusFicha(StatusFicha.CADASTRADA);

		this.fichaInscricao = cadastroFichaInscricaoService.salvar(this.fichaInscricao);
		limpar();

		FacesUtil.addInfoMessage("Ficha de Inscrição registrada com sucesso!");

	}

	public void carregarEtapas() {
		etapasCampeonatos = etapas.etapasDoCampeonato(fichaInscricao.getCampeonato());
	}

	public void carregarCompetidores() {

		todosCompetidores = competidores.todosCompetidores();
	}

	public void carregarAnuidadesCompetidor() {
		// anuidadesCompetidor =
		// anuidades.anuidadesCompetidor(this.fichaInscricao.getCompetidor());
	}

	/**
	 * Método abaixo para adicionar os competidores nas passadas e o recebimento
	 **/
	private void trataPassadasInclusao() {
		
		if (this.fichaInscricao.getTipoFicha().equals(TipoFicha.INDIVIDUAL) && competidor1 != null) {

			this.fichaInscricao = cadastroFichaInscricaoService.preparaPassadas1(this.fichaInscricao, competidor1);
			
		} else if (this.fichaInscricao.getTipoFicha().equals(TipoFicha.DUPLA) && competidor1 != null
				&& competidor2 != null) {

			this.fichaInscricao = cadastroFichaInscricaoService.preparaPassadas2(this.fichaInscricao, competidor1,
					competidor2);

		} else if (this.fichaInscricao.getTipoFicha().equals(TipoFicha.TRIO) && competidor1 != null
				&& competidor2 != null && competidor3 != null) {

			this.fichaInscricao = cadastroFichaInscricaoService.preparaPassadas3(this.fichaInscricao, competidor1,
					competidor2, competidor3);

		} else {
			throw new NegocioException("Por favor, selecione o tipo da ficha e/ou competidores!");
		}

	}

	// retornar o tipo de ficha em um array
	public TipoFicha[] getTiposFichas() {
		return TipoFicha.values();
	}

	public FichaInscricao getFichaInscricao() {
		return fichaInscricao;
	}

	public void setFichaInscricao(FichaInscricao fichaInscricao) {
		this.fichaInscricao = fichaInscricao;

	}

	public List<Competidor> getTodosCompetidores() {
		return todosCompetidores;
	}

	public List<Animal> getTodosAnimais() {
		return todosAnimais;
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

	public List<Anuidade> getAnuidadesCompetidor() {
		return anuidadesCompetidor;
	}

	public Competidor getCompetidor1() {
		return competidor1;
	}

	public void setCompetidor1(Competidor competidor1) {
		this.competidor1 = competidor1;
	}

	public Competidor getCompetidor2() {
		return competidor2;
	}

	public void setCompetidor2(Competidor competidor2) {
		this.competidor2 = competidor2;
	}

	public Competidor getCompetidor3() {
		return competidor3;
	}

	public void setCompetidor3(Competidor competidor3) {
		this.competidor3 = competidor3;
	}

	public boolean isEditando() {
		return this.fichaInscricao.getId() != null;
	}
}
