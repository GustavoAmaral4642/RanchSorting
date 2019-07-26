package com.ranchsorting.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
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
import com.ranchsorting.model.StatusFicha;
import com.ranchsorting.model.TipoFicha;
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
	private boolean inclusaoSimultanea = false;
	
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
		todosCompetidores = competidores.todosCompetidores();
		
		if (FacesUtil.isNotPostback()) {
			if (isEditando()) {
				carregarAnuidadesCompetidor();
				carregarEtapas();
			}
		}
	}

	public void limpar() {
		
		if(!isInclusaoSimultanea()){
			fichaInscricao = new FichaInscricao();		
			anuidadesCompetidor = new ArrayList<>();
			
		} else {
			Campeonato camp = fichaInscricao.getCampeonato();
			Etapa etp = fichaInscricao.getEtapa();
			Divisao div = fichaInscricao.getDivisao();
			Date dtInsc = fichaInscricao.getDataInscricao();
			
			fichaInscricao = new FichaInscricao();
			anuidadesCompetidor = new ArrayList<>();
			
			fichaInscricao.setCampeonato(camp);
			fichaInscricao.setEtapa(etp);
			fichaInscricao.setDivisao(div);
			fichaInscricao.setDataInscricao(dtInsc);
		}
		
		if (!isEditando()) {
			fichaInscricao.setStatusFicha(StatusFicha.CADASTRADA);
		}	
		
	}

	public void salvar() {
		
		this.fichaInscricao.setStatusFicha(StatusFicha.CADASTRADA);		
		this.fichaInscricao = cadastroFichaInscricaoService.salvar(this.fichaInscricao);
		
		inclusaoSimultanea = true;
		
		limpar();

		FacesUtil.addInfoMessage("Ficha de Inscrição registrada com sucesso!");

	}

	public void carregarEtapas() {
		etapasCampeonatos = etapas.etapasDoCampeonato(fichaInscricao.getCampeonato());
	}

	public void carregarAnuidadesCompetidor() {
		anuidadesCompetidor=anuidades.anuidadesCompetidor(this.fichaInscricao.getCompetidor());
	}

	public void carregarDataInscricaoEtapa(){
		if(fichaInscricao.getEtapa().getDataEvento() != null){
			fichaInscricao.setDataInscricao(fichaInscricao.getEtapa().getDataEvento() );	
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
	
	public boolean isEditando() {
		return this.fichaInscricao.getId() != null;
	}
	
	public boolean isInclusaoSimultanea() {
		return this.inclusaoSimultanea;
	}
}
