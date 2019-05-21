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
import com.ranchsorting.model.FormaPagamento;
import com.ranchsorting.model.StatusFicha;
import com.ranchsorting.repository.Animais;
import com.ranchsorting.repository.Anuidades;
import com.ranchsorting.repository.Campeonatos;
import com.ranchsorting.repository.Competidores;
import com.ranchsorting.repository.Divisoes;
import com.ranchsorting.repository.Etapas;
import com.ranchsorting.service.CadastroFichaInscricaoService;
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
	private Anuidades anuidades;
	
	@Inject
	private CadastroFichaInscricaoService cadastroFichaInscricaoService;

	private FichaInscricao fichaInscricao;

	private List<Competidor> todosCompetidores;
	private List<Animal> todosAnimais;
	private List<Campeonato> todosCampeonatos;
	private List<Etapa> etapasCampeonatos;
	private List<Divisao> todasDivisoes;
	private List<Anuidade> anuidadesCompetidor;
	
	public CadastroFichaInscricaoBean() {
		fichaInscricao = new FichaInscricao();
	}

	public void inicializar() {
		todosCompetidores = competidores.todosCompetidores();
		todosAnimais = animais.todosAnimais();
		todosCampeonatos = campeonatos.todosCampeonatos();
		todasDivisoes = divisoes.todasDivisoes();
		
		if (FacesUtil.isNotPostback()){
			if (isEditando()) {
				carregarAnuidadesCompetidor();
				carregarEtapas();
			} else if(fichaInscricao.getStatusFicha() == null){
				fichaInscricao.setStatusFicha(StatusFicha.CADASTRADA);
			}
		}
	}

	public void limpar() {
		fichaInscricao = new FichaInscricao();
		anuidadesCompetidor = new ArrayList<>();
	}

	public void salvar() {

		this.fichaInscricao.setStatusFicha(StatusFicha.CADASTRADA);
		this.fichaInscricao = cadastroFichaInscricaoService.salvar(this.fichaInscricao);

		limpar();

		FacesUtil.addInfoMessage("Ficha de Inscrição registrada com sucesso!");

	}

	public void carregarEtapas() {
		etapasCampeonatos = etapas.etapasDoCampeonato(fichaInscricao.getCampeonato());
	}

	public void carregarAnuidadesCompetidor() {
		//anuidadesCompetidor = anuidades.anuidadesCompetidor(this.fichaInscricao.getCompetidor());
	}


	// retornar a forma de pagamento em um array
	public FormaPagamento[] getFormasPagamento() {
		return FormaPagamento.values();
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

	public boolean isEditando() {
		return this.fichaInscricao.getId() != null;
	}
}
