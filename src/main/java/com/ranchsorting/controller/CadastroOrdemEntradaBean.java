package com.ranchsorting.controller;

import java.io.Serializable;
import java.util.ArrayList;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.validation.ConstraintViolationException;

import org.primefaces.event.RowEditEvent;

import com.ranchsorting.model.Campeonato;
import com.ranchsorting.model.Divisao;
import com.ranchsorting.model.Etapa;
import com.ranchsorting.model.FichaInscricao;
import com.ranchsorting.model.OrdemEntrada;
import com.ranchsorting.model.Passada;
import com.ranchsorting.model.StatusFicha;
import com.ranchsorting.model.TipoCampeonato;
import com.ranchsorting.model.TipoFicha;
import com.ranchsorting.repository.Campeonatos;
import com.ranchsorting.repository.Divisoes;
import com.ranchsorting.repository.Etapas;
import com.ranchsorting.repository.FichasInscricoes;
import com.ranchsorting.repository.filter.FichaInscricaoFilter;
import com.ranchsorting.service.CadastroOrdemEntradaService;
import com.ranchsorting.service.CadastroPassadaService;
import com.ranchsorting.service.EmbaralhaPassadasService;
import com.ranchsorting.service.MontaOrdemEntradaService;
import com.ranchsorting.service.NegocioException;
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

	@Inject
	private CadastroPassadaService cadastroPassadaService;

	@Inject
	private EmbaralhaPassadasService embaralharService;
	
	@Inject 
	private MontaOrdemEntradaService montaOrdemService;
	
	private List<Campeonato> todosCampeonatos;
	private List<Etapa> etapasCampeonatos;
	private List<Divisao> todasDivisoes;
	private List<FichaInscricao> fichasFiltradas;
	private List<FichaInscricao> fichasSelecionadas;
	private List<Passada> passadasCompetidores;

	private OrdemEntrada ordemEntrada;
	private FichaInscricaoFilter fichaInscricaoFilter;

	private FichaInscricao fichaInscricaoLinhaEditavel;

	public CadastroOrdemEntradaBean() {
		limpar();
		
	}

	public void inicializar() {
		todosCampeonatos = campeonatos.todosCampeonatos();
		todasDivisoes = divisoes.todasDivisoes();

		if (FacesUtil.isNotPostback()) {
			
			if (isEditando()) {
				
				fichaInscricaoFilter.setObjCampeonato(this.ordemEntrada.getCampeonato());
				etapasCampeonatos = etapas.etapasDoCampeonato(this.fichaInscricaoFilter.getObjCampeonato());
				fichaInscricaoFilter.setObjEtapa(this.ordemEntrada.getEtapa());
				fichaInscricaoFilter.setObjDivisao(this.ordemEntrada.getDivisao());

				
				
				passadasCompetidores.addAll(ordemEntrada.getPassadas());
			}
		}
	}

	public void limpar() {
		ordemEntrada = new OrdemEntrada();
		passadasCompetidores = new ArrayList<>();
		fichaInscricaoFilter = new FichaInscricaoFilter();
		fichasSelecionadas = new ArrayList<>();
	}

	public void salvar() {
		
		this.ordemEntrada.getPassadas().addAll(passadasCompetidores);
		this.ordemEntrada = cadastroOrdemEntradaService.salvar(this.ordemEntrada);

		limpar();

		FacesUtil.addInfoMessage("Ordem de entrada registrada com sucesso!");

	}

	public void carregarCompetidores() {

		fichasFiltradas = new ArrayList<>();

		if (fichaInscricaoFilter.getObjCampeonato() != null
				&& !fichaInscricaoFilter.getObjCampeonato().getNome().equals("")) {
			if (fichaInscricaoFilter.getObjEtapa() != null
					&& !fichaInscricaoFilter.getObjEtapa().getNome().equals("")) {
				if (fichaInscricaoFilter.getObjDivisao() != null
						&& !fichaInscricaoFilter.getObjDivisao().getNome().equals("")) {

					// só busca fichas que estão com status CADASTRADA
					fichaInscricaoFilter.setStatusFicha(StatusFicha.CADASTRADA);

					fichasFiltradas = fichasInscricoes.filtradas(fichaInscricaoFilter);

					ordemEntrada.setCampeonato(fichaInscricaoFilter.getObjCampeonato());
					ordemEntrada.setEtapa(fichaInscricaoFilter.getObjEtapa());
					ordemEntrada.setDivisao(fichaInscricaoFilter.getObjDivisao());

					this.ordemEntrada = cadastroOrdemEntradaService.salvar(ordemEntrada);

					FacesUtil.addInfoMessage("Ordem de Entrada registrada com sucesso!");
				}
			}
		}
	}

	public void carregarEtapas() {
		etapasCampeonatos = etapas.etapasDoCampeonato(this.fichaInscricaoFilter.getObjCampeonato());
	}

	public void carregarDataEtapa() {
		// this.ordemEntrada.setData(this.passadaFilter.getObjEtapa().getDataEvento());
	}

	public void gerarOrdemEntrada() {

		// Este teste é necessario pois em carregarCompetidores ele alimenta
		// esses obj.
		if (this.ordemEntrada.getCampeonato() == null && this.ordemEntrada.getEtapa() == null
				&& this.ordemEntrada.getDivisao() == null) {
			throw new NegocioException("Por favor, carregue os competidores!");
		}

		// sorteio para prova de ranch sorting amador
		if (this.ordemEntrada.getCampeonato().getTipoCampeonato().equals(TipoCampeonato.RANCHSORTING)
				&& this.ordemEntrada.getDivisao().getTipoFicha().equals(TipoFicha.INDIVIDUAL)) {

			// se tentar rodar 2 vezes este procedimento
			if (fichasFiltradas == null || fichasFiltradas.size() == 0) {
				throw new NegocioException(
						"Atenção, certifique-se se esse procedimento já foi feito ou se os competidores foram carregados!");
			}

			/*try{
			    //Método antigo
				// chama método para embaralhar as passadas do amador
				//passadasCompetidores = cadastroOrdemEntradaService.geraPassadaAmador(this.ordemEntrada, fichasFiltradas, fichaInscricaoFilter);
				*/
				
				passadasCompetidores = embaralharService.embaralharPassadas(fichasFiltradas);
				passadasCompetidores = montaOrdemService.montarOrdemEntrada(passadasCompetidores);
				
			/*} catch (NullPointerException ex) {
				throw new NegocioException("Ocorreu algum promblema ao gerar a Ordem de Entrada."
						+ "Entre em contato com o administrador do Sistema. (NullPointerException)");
			} catch (ConstraintViolationException ex) {
				throw new NegocioException("Ocorreu algum promblema ao gerar a Ordem de Entrada."
						+ "Entre em contato com o administrador do Sistema. (ConstraintViolationException)");
			} catch (ArithmeticException ex) {
				throw new NegocioException("Ocorreu algum promblema ao gerar a Ordem de Entrada."
						+ "Entre em contato com o administrador do Sistema. (ArithmeticException)");
			} catch (IndexOutOfBoundsException e){
				throw new NegocioException("Ocorreu algum promblema ao gerar a Ordem de Entrada."
						+ "Entre em contato com o administrador do Sistema. (IndexOutOfBoundsException)");
			} catch (RuntimeException ex) {
				throw new NegocioException("Ocorreu algum promblema ao gerar a Ordem de Entrada."
						+ "Entre em contato com o administrador do Sistema. (RuntimeException)");
			} catch (Exception ex) {
				throw new NegocioException("Ocorreu algum promblema ao gerar a Ordem de Entrada."
						+ "Entre em contato com o administrador do Sistema. (Exception)");
			} 
			*/

			//passadasCompetidores = cadastroOrdemEntradaService.embaralhaPassadas2(passadasCompetidores);
			
			if (passadasCompetidores == null || passadasCompetidores.size() == 0) {
				throw new NegocioException("Os competidores não foram carregados. Ordem não será gerada!");
			}

		} else {
			
			passadasCompetidores = cadastroOrdemEntradaService.trataOutrasPassadas2(passadasCompetidores, fichaInscricaoFilter);
			try{
				
			}catch (NullPointerException ex) {
				throw new NegocioException("Ocorreu algum promblema ao gerar a Ordem de Entrada."
						+ "Entre em contato com o administrador do Sistema. (NullPointerException)");
			} catch (ConstraintViolationException ex) {
				throw new NegocioException("Ocorreu algum promblema ao gerar a Ordem de Entrada."
						+ "Entre em contato com o administrador do Sistema. (ConstraintViolationException)");
			} catch (ArithmeticException ex) {
				throw new NegocioException("Ocorreu algum promblema ao gerar a Ordem de Entrada."
						+ "Entre em contato com o administrador do Sistema. (ArithmeticException)");
			} catch (IndexOutOfBoundsException e){
				throw new NegocioException("Ocorreu algum promblema ao gerar a Ordem de Entrada."
						+ "Entre em contato com o administrador do Sistema. (IndexOutOfBoundsException)");
			} catch (RuntimeException ex) {
				throw new NegocioException("Ocorreu algum promblema ao gerar a Ordem de Entrada."
						+ "Entre em contato com o administrador do Sistema. (RuntimeException)");
			} catch (Exception ex) {
				throw new NegocioException("Ocorreu algum promblema ao gerar a Ordem de Entrada."
						+ "Entre em contato com o administrador do Sistema. (Exception)");
			} 
			
		}
		
		this.ordemEntrada.setCampeonato(fichaInscricaoFilter.getObjCampeonato());
		this.ordemEntrada.setEtapa(fichaInscricaoFilter.getObjEtapa());
		this.ordemEntrada.setDivisao(fichaInscricaoFilter.getObjDivisao());

		/*
		 * embaralhar numero da dupla List<Integer> numeros = new
		 * ArrayList<Integer>(); for (int i = 0; i < 26; i++) { numeros.add(i);
		 * } Collections.shuffle(numeros); System.out.print("Sorteados: "); for
		 * (int i = 0; i < 15; i++) { System.out.print(numeros.get(i));
		 * System.out.print(" "); } System.out.println();
		 */
	}

	public void sortearCompetidores() {

		if (fichasFiltradas == null || fichasFiltradas.size() == 0) {
			throw new NegocioException("Por favor, carregue os competidores antes deste procedimento!");
		}

		carregarCompetidores();
	}

	public void incluirFichasCompetidores() {

		// sorteio para prova de ranch sorting amador
		if (ordemEntrada.getCampeonato().getTipoCampeonato().equals(TipoCampeonato.RANCHSORTING)
				&& ordemEntrada.getDivisao().getTipoFicha().equals(TipoFicha.INDIVIDUAL)) {

			throw new NegocioException(
					"Para este campeonato, só é permitido gerar ordem automaticamente. Clique no botão 'Gerar Ordem'!");
		}

		// se a fichaInscricao linha editável não for nula
		if (this.fichaInscricaoLinhaEditavel != null) {

			if (fichaInscricaoLinhaEditavel.getDivisao().getTipoFicha().equals(TipoFicha.INDIVIDUAL)
					&& fichasSelecionadas.size() < 1) {

				// adiciona no dataList
				fichasSelecionadas.add(fichaInscricaoLinhaEditavel);

				// remove do combobox
				fichasFiltradas.remove(fichaInscricaoLinhaEditavel);

			} else if (fichaInscricaoLinhaEditavel.getDivisao().getTipoFicha().equals(TipoFicha.DUPLA)
					&& fichasSelecionadas.size() < 2) {

				// adiciona no dataList
				fichasSelecionadas.add(fichaInscricaoLinhaEditavel);

				// remove do combobox
				fichasFiltradas.remove(fichaInscricaoLinhaEditavel);

			} else if (fichaInscricaoLinhaEditavel.getDivisao().getTipoFicha().equals(TipoFicha.TRIO)
					&& fichasSelecionadas.size() < 3) {

				// adiciona no dataList
				fichasSelecionadas.add(fichaInscricaoLinhaEditavel);

				// remove do combobox
				fichasFiltradas.remove(fichaInscricaoLinhaEditavel);

			} else {
				FacesUtil.addInfoMessage("Quantidade de fichas selecionadas já atingiu o limite da Divisão "
						+ fichaInscricaoLinhaEditavel.getDivisao().getNome());
			}
			this.fichaInscricaoLinhaEditavel = null;
		}
	}

	public void limparListaFichasInscricoes() {
		if (fichasSelecionadas != null && fichasSelecionadas.size() != 0) {
			fichasSelecionadas = new ArrayList<>();
		}
	}

	public void incluirPassada() {

		if (fichasSelecionadas != null && fichasSelecionadas.size() != 0) {

			Passada passada = new Passada();

			// adiciona a ficha como item de passada.
			for (FichaInscricao f : fichasSelecionadas) {
				f.setPassada(passada);
				f.setCampeonato(ordemEntrada.getCampeonato());
				f.setEtapa(ordemEntrada.getEtapa());
				f.setDivisao(ordemEntrada.getDivisao());
				f.setStatusFicha(StatusFicha.EMORDEM);
				passada.getFichasInscricoes().add(f);
			}

			// incluindo passada no DataTable
			passadasCompetidores.add(passada);

			// salvando passada
			passada.setOrdemEntrada(this.ordemEntrada);
			passada = cadastroPassadaService.salvar(passada);

			FacesUtil.addInfoMessage("Passadas Registradas com Sucesso!");

			// resetando dataList
			fichasSelecionadas = new ArrayList<>();

		}
	}
	
	public void onRowEdit(RowEditEvent event) {
		FacesMessage msg = new FacesMessage("Passada editada", ((Passada) event.getObject()).getId().toString());
		FacesContext.getCurrentInstance().addMessage(null, msg);
		this.ordemEntrada.getPassadas().addAll(passadasCompetidores);
		cadastroOrdemEntradaService.salvar(ordemEntrada);
	}

	public void onRowCancel(RowEditEvent event) {
		FacesMessage msg = new FacesMessage("Edição cancelada", ((Passada) event.getObject()).getId().toString());
		FacesContext.getCurrentInstance().addMessage(null, msg);
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
