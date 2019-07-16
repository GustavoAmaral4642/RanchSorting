package com.ranchsorting.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.ranchsorting.model.Campeonato;
import com.ranchsorting.model.Competidor;
import com.ranchsorting.model.Divisao;
import com.ranchsorting.model.Etapa;
import com.ranchsorting.model.FichaInscricao;
import com.ranchsorting.model.OrdemEntrada;
import com.ranchsorting.model.Passada;
import com.ranchsorting.model.StatusFicha;
import com.ranchsorting.model.TipoFicha;
import com.ranchsorting.repository.Campeonatos;
import com.ranchsorting.repository.Divisoes;
import com.ranchsorting.repository.Etapas;
import com.ranchsorting.repository.FichasInscricoes;
import com.ranchsorting.repository.filter.FichaInscricaoFilter;
import com.ranchsorting.service.CadastroOrdemEntradaService;
import com.ranchsorting.service.CadastroPassadaService;
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
				fichaInscricaoFilter.setObjCampeonato(ordemEntrada.getCampeonato());

				etapasCampeonatos = etapas.etapasDoCampeonato(this.fichaInscricaoFilter.getObjCampeonato());
				fichaInscricaoFilter.setObjEtapa(ordemEntrada.getEtapa());

				fichaInscricaoFilter.setObjDivisao(ordemEntrada.getDivisao());

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
		this.ordemEntrada = cadastroOrdemEntradaService.salvar(ordemEntrada);

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

		List<Integer> numerosOrdem = new ArrayList<>();

		for (int i = 0; i < passadasCompetidores.size(); i++) {
			numerosOrdem.add(i + 1);
		}

		// embaralha
		Collections.shuffle(passadasCompetidores);

		List<Passada> listaAuxiliar = new ArrayList<>();

		boolean ver1 = false, ver2 = false, ver3 = false;

		int posicao1 = 0;
		int comparador = 1;
		int tamanhoArrayPrincipal = passadasCompetidores.size();

		do {

			Competidor comp1 = new Competidor();
			Competidor comp2 = new Competidor();
			Competidor comp3 = new Competidor();
			Competidor comp4 = new Competidor();

			Passada pas = new Passada();

			if (listaAuxiliar.size() == 0) {
				// se a lista auxiliar estiver vazia, alimenta com o primeiro
				// registro da passadasCompetidores
				pas = passadasCompetidores.get(posicao1);
				listaAuxiliar.add(pas);
				passadasCompetidores.remove(pas);
			} else {
				// se nao, pega o ultimo registro da listaAuxiliar
				pas = listaAuxiliar.get((listaAuxiliar.size() - 1));
				ver2 = false;				
			}

			if (posicao1 == 0 && ver2 == false) {

				// pega os dois competidores de uma das fichas que serão
				// comparadas
				comp1 = pas.getFichasInscricoes().get(0).getCompetidor();
				comp2 = pas.getFichasInscricoes().get(1).getCompetidor();

				// este loop verifica se os registros se repetem
				do {
					// pega os dois competidores da ficha a ser comparada
					comp3 = passadasCompetidores.get(comparador).getFichasInscricoes().get(0).getCompetidor();
					comp4 = passadasCompetidores.get(comparador).getFichasInscricoes().get(1).getCompetidor();

					// 4 IFs comparam os competidores se são iguais
					if (comp1.getNome().equals(comp3.getNome())) {
						comparador++;
						posicao1++;

					} else if (comp1.getNome().equals(comp4.getNome())) {
						comparador++;
						posicao1++;

					} else if (comp2.getNome().equals(comp3.getNome())) {
						comparador++;
						posicao1++;

					} else if (comp2.getNome().equals(comp4.getNome())) {
						comparador++;
						posicao1++;

					} else {
						// se os competidores não forem iguais
						// adiciona a passada não inseria a uma lista auxiliar e
						// remove da lista que está sendo lida
						listaAuxiliar.add(passadasCompetidores.get(comparador));
						passadasCompetidores.remove(passadasCompetidores.get(comparador));

						// reseta as variáveis de comparaçao.
						posicao1 = 0;
						comparador = 0;

						// muda variável para sair do loop secundário
						ver2 = true;

						// se o tamanho da lista auxiliar for do mesmo tamanho
						// que a lista pricipal, torna a ver3 true para sair do
						// loop principal
						if (listaAuxiliar.size() >= tamanhoArrayPrincipal) {
							ver3 = true;
						}
					}

					// se o tamanho de passadas competidores for 0 e comparador
					// tiver ser o mesmo que o tamanho do array
					if (passadasCompetidores.size() != 0 && comparador == passadasCompetidores.size()) {
						posicao1 = 0;
						comparador = 0;

						// se os competidores não forem iguais
						// adiciona a passada não inseria a uma lista auxiliar e
						// remove da lista que está sendo lida
						listaAuxiliar.add(passadasCompetidores.get(comparador));
						passadasCompetidores.remove(passadasCompetidores.get(comparador));

						// muda variável para sair do loop secundário
						ver2 = true;

						// se o tamanho da lista auxiliar for do mesmo tamanho
						// que a lista pricipal, torna a ver3 true para sair do
						// loop principal
						if (listaAuxiliar.size() >= tamanhoArrayPrincipal) {
							ver3 = true;
						}
					}

				} while (!ver2);

				// ajusta tamanho da posicao 1.
				posicao1 = 0;
			}

			// sai do loop quando posicao1 for mesmo tamanho que o array
			if (ver2 == true && ver3 == true) {
				ver1 = true;
			}

		} while (!ver1);

		passadasCompetidores = listaAuxiliar;
		
		for (int i = 0; i < passadasCompetidores.size(); i++) {
			passadasCompetidores.get(i).setNumeroDupla(Long.valueOf(numerosOrdem.get(i)));
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
		return this.fichasFiltradas;
	}

	public void incluirFichasCompetidores() {

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
