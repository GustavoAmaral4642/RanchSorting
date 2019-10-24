package com.ranchsorting.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;

import com.ranchsorting.model.Animal;
import com.ranchsorting.model.Anuidade;
import com.ranchsorting.model.Campeonato;
import com.ranchsorting.model.Competidor;
import com.ranchsorting.model.Divisao;
import com.ranchsorting.model.Etapa;
import com.ranchsorting.model.FichaInscricao;
import com.ranchsorting.model.StatusFicha;
import com.ranchsorting.model.TipoCampeonato;
import com.ranchsorting.model.TipoFicha;
import com.ranchsorting.repository.Campeonatos;
import com.ranchsorting.repository.Competidores;
import com.ranchsorting.repository.Divisoes;
import com.ranchsorting.repository.Etapas;
import com.ranchsorting.service.CadastroFichaInscricaoService;
import com.ranchsorting.service.NegocioException;
import com.ranchsorting.util.jsf.FacesUtil;
import com.ranchsorting.util.report.ExecutorRelatorio;

@Named
@ViewScoped
public class CadastroFichaInscricaoAgrupadaBean implements Serializable {

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
	private CadastroFichaInscricaoService cadastroFichaInscricaoService;

	@Inject
	private FacesContext facesContext; // para impressão da ficha

	@Inject
	private HttpServletResponse response; // para impressão da ficha

	@Inject
	private EntityManager manager; // para impressão da ficha

	private FichaInscricao fichaInscricao;
	private FichaInscricao fichaInscricaoParceiro;
	private boolean inclusaoSimultanea = false;
	private Anuidade anuidadeSelecionada;
	
	private List<Competidor> todosCompetidoresIndividual;
	private List<Competidor> todosCompetidoresDupla;
	private List<Animal> todosAnimais;
	private List<Campeonato> todosCampeonatos;
	private List<Divisao> todasDivisoes;
	private List<Anuidade> anuidadesCompetidor;

	public CadastroFichaInscricaoAgrupadaBean() {
		limpar();
	}

	public void inicializar() {

	}

	@PostConstruct
	public void init() {
		todosCampeonatos = campeonatos.todosCampeonatos();
		todosCompetidoresDupla = competidores.consultaCompetidoresParaCombobox();
		todosCompetidoresIndividual = todosCompetidoresDupla;
		todasDivisoes = divisoes.todasDivisoes();
		
	}

	public void limpar() {

		if (!isInclusaoSimultanea()) {
			fichaInscricao = new FichaInscricao();
			fichaInscricaoParceiro = new FichaInscricao();
			anuidadesCompetidor = new ArrayList<>();
		} else {

			// reiniciando varáveis
			Campeonato camp = fichaInscricao.getCampeonato();
			Etapa etp = fichaInscricao.getEtapa();
			Divisao div = fichaInscricao.getDivisao();
			Date dtInsc = fichaInscricao.getDataInscricao();
			List<Etapa> etapasCampeonatos = new ArrayList<>();

			fichaInscricao = new FichaInscricao();
			anuidadesCompetidor = new ArrayList<>();

			// setando parametros
			fichaInscricao.setCampeonato(camp);
			fichaInscricao.setEtapa(etp);
			fichaInscricao.setDivisao(div);
			fichaInscricao.setDataInscricao(dtInsc);

			// para não dar exceção de Lazy
			etapasCampeonatos = etapas.etapasDoCampeonato(camp);
			fichaInscricao.getCampeonato().setEtapas(etapasCampeonatos);

			// se o tipo de divisao for de dupla
			if (fichaInscricao.getDivisao().getTipoFicha().equals(TipoFicha.DUPLA)) {
				fichaInscricaoParceiro = new FichaInscricao();
				fichaInscricaoParceiro.setCampeonato(camp);
				fichaInscricaoParceiro.setEtapa(etp);
				fichaInscricaoParceiro.setDivisao(div);
				fichaInscricaoParceiro.setDataInscricao(dtInsc);
			}
		}

		if (!isEditando()) {
			fichaInscricao.setStatusFicha(StatusFicha.CADASTRADA);
			fichaInscricaoParceiro.setStatusFicha(StatusFicha.CADASTRADA);
		}

	}

	public void salvar() {

		// se o tipo de divisao for de dupla
		if (fichaInscricao.getDivisao().getTipoFicha().equals(TipoFicha.DUPLA) && fichaInscricao.getCompetidor() != null) {

			this.fichaInscricaoParceiro.setStatusFicha(StatusFicha.CADASTRADA);
			this.fichaInscricaoParceiro.setCampeonato(this.fichaInscricao.getCampeonato());
			this.fichaInscricaoParceiro.setEtapa(this.fichaInscricao.getEtapa());
			this.fichaInscricaoParceiro.setDivisao(this.fichaInscricao.getDivisao());
			this.fichaInscricaoParceiro.setDataInscricao(this.fichaInscricao.getDataInscricao());
			
			this.fichaInscricao.setStatusFicha(StatusFicha.CADASTRADA);
			this.fichaInscricao = cadastroFichaInscricaoService.salvarDupla(this.fichaInscricao, this.fichaInscricaoParceiro);

		} else {
			this.fichaInscricao.setStatusFicha(StatusFicha.CADASTRADA);
			this.fichaInscricao = cadastroFichaInscricaoService.salvar(this.fichaInscricao);
		}

		inclusaoSimultanea = true;

		limpar();

		FacesUtil.addInfoMessage("Ficha de Inscrição registrada com sucesso!");
	}

	public void carregarDataInscricaoEtapa() {
		if (fichaInscricao.getEtapa().getDataEvento() != null) {
			fichaInscricao.setDataInscricao(fichaInscricao.getEtapa().getDataEvento());
		}
	}

	public void carregarQuantidadeFichasPadrao() {
		if (!fichaInscricao.getDivisao().getQntFichasPadrao().equals(new Long(0))) {
			fichaInscricao.setQntFichas(fichaInscricao.getDivisao().getQntFichasPadrao());
		} else {
			fichaInscricao.setQntFichas(new Long(0));
		}
	}

	// imprime as fichas de inscricao
	public void impressaoFichasInscricao() {

		// se não for edição de página
		if (!isEditando()) {
			throw new NegocioException("Por favor salve a ficha de inscrição!");
		}

		if (fichaInscricao != null && fichaInscricao.getDivisao().getTipoFicha().equals(TipoFicha.INDIVIDUAL)
				&& fichaInscricao.getCampeonato().getTipoCampeonato().equals(TipoCampeonato.RANCHSORTING)) {
			throw new NegocioException("Campeonato e Divisão escolhidos não permitem impressão da ficha!");
		}

		// definindo parametros
		Map<String, Object> parametros = new HashMap<>();
		parametros.put("codigo_competidor", this.fichaInscricao.getCompetidor().getId());
		parametros.put("codigo_campeonato", this.fichaInscricao.getCampeonato().getId());
		parametros.put("codigo_etapa", this.fichaInscricao.getEtapa().getId());
		parametros.put("codigo_divisao", this.fichaInscricao.getDivisao().getId());

		// chamo a classe para executar o relatorio
		// primeiro parametro é o caminho do arquivo do relatorio
		// segundo parametro é o response
		// terceiro são os parametros
		// quarto é o nome do arquivo

		ExecutorRelatorio executor = new ExecutorRelatorio("/relatorios/ficha_inscricao.jasper", this.response,
				parametros, "Ficha de Inscrição.pdf");

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

	public FichaInscricao getFichaInscricaoParceiro() {
		return fichaInscricaoParceiro;
	}

	public void setFichaInscricaoParceiro(FichaInscricao fichaInscricaoParceiro) {
		this.fichaInscricaoParceiro = fichaInscricaoParceiro;
	}

	public List<Competidor> getTodosCompetidoresIndividual() {
		return todosCompetidoresIndividual;
	}

	public List<Competidor> getTodosCompetidoresDupla() {
		return todosCompetidoresDupla;
	}

	public List<Animal> getTodosAnimais() {
		return todosAnimais;
	}

	public List<Campeonato> getTodosCampeonatos() {
		return todosCampeonatos;
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

	public Anuidade getAnuidadeSelecionada() {
		return anuidadeSelecionada;
	}

	public void setAnuidadeSelecionada(Anuidade anuidadeSelecionada) {
		this.anuidadeSelecionada = anuidadeSelecionada;
	}

}
