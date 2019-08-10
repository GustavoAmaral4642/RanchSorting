package com.ranchsorting.controller;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotNull;

import org.hibernate.Session;

import com.ranchsorting.model.Campeonato;
import com.ranchsorting.model.Divisao;
import com.ranchsorting.model.Etapa;
import com.ranchsorting.repository.Campeonatos;
import com.ranchsorting.repository.Divisoes;
import com.ranchsorting.repository.Etapas;
import com.ranchsorting.util.jsf.FacesUtil;
import com.ranchsorting.util.report.ExecutorRelatorio;

@Named
@ViewScoped
public class RelatorioOrdemEntradaBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private Campeonatos campeonatos;

	@Inject
	private Etapas etapas;

	@Inject
	private Divisoes divisoes;

	@Inject
	private FacesContext facesContext;

	@Inject
	private HttpServletResponse response;

	@Inject
	private EntityManager manager;

	private List<Campeonato> todosCampeonatos;
	private List<Etapa> etapasCampeonatos;
	private List<Divisao> todasDivisoes;

	// parametros da tela
	private Campeonato campeonato;
	private Etapa etapa;
	private Divisao divisao;

	public void inicializar() {
		todosCampeonatos = campeonatos.todosCampeonatos();
		todasDivisoes = divisoes.todasDivisoes();

		if (FacesUtil.isNotPostback()) {
			carregarEtapas();
		}
	}

	public void emitir() {
		// definindo parametros
		Map<String, Object> parametros = new HashMap<>();
		parametros.put("campeonato", this.campeonato.getId());
		parametros.put("etapa", this.etapa.getId());
		parametros.put("divisao", this.divisao.getId());

		// chamo a classe para executar o relatorio
		// primeiro parametro é o caminho do arquivo do relatorio
		// segundo parametro é o response
		// terceiro são os parametros
		// quarto é o nome do arquivo
		ExecutorRelatorio executor = new ExecutorRelatorio("/relatorios/relatorios_ordem_entrada.jasper", this.response,
				parametros, "Ordem de Entrada.pdf");

		Session session = manager.unwrap(Session.class); // chamamos a sessão
		session.doWork(executor); //

		// teste para verificar se o relatorio contem informações
		if (executor.isRelatorioGerado()) {
			// depois que o relatorio foi executado, não podemos continuar o
			// ciclo de vida do jsf
			// se não colocar, da erro.
			facesContext.responseComplete();
		} else {
			FacesUtil.addErrorMessage("A execução do relatório não retornou dados.");
		}
	}

	public void carregarEtapas() {
		etapasCampeonatos = etapas.etapasDoCampeonato(campeonato);
	}

	@NotNull
	public Campeonato getCampeonato() {
		return campeonato;
	}

	public void setCampeonato(Campeonato campeonato) {
		this.campeonato = campeonato;
	}

	@NotNull
	public Etapa getEtapa() {
		return etapa;
	}

	public void setEtapa(Etapa etapa) {
		this.etapa = etapa;
	}

	@NotNull
	public Divisao getDivisao() {
		return divisao;
	}

	public void setDivisao(Divisao divisao) {
		this.divisao = divisao;
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

}
