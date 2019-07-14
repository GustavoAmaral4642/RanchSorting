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

		for (Passada p : passadasCompetidores) {

			p.setOrdemEntrada(ordemEntrada);

		}
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
		List<Passada> listaAuxiliar2 = passadasCompetidores;
		
		Passada pas = new Passada();
		
		boolean ver1 = false, ver2 = false;

		
		int posicao1 = 0;
		int comparador = 1;
		
		do {
			
			Competidor comp1 = new Competidor();
			Competidor comp2 = new Competidor();
			Competidor comp3 = new Competidor();
			Competidor comp4 = new Competidor();

			pas = passadasCompetidores.get(posicao1);
			
			if(posicao1 == 0){
				
				System.out.println("if 1 posicao==0 ");
				comp1 = pas.getFichasInscricoes().get(0).getCompetidor();
				comp2 = pas.getFichasInscricoes().get(1).getCompetidor();
				listaAuxiliar.add(pas);
			
				// este loop vi
				do{	
					System.out.println("entrada do");
					System.out.println(pas.getFichasInscricoes().get(0).getCompetidor().getNome());
					System.out.println(pas.getFichasInscricoes().get(1).getCompetidor().getNome());
					System.out.println(passadasCompetidores.get(comparador).getFichasInscricoes().get(0).getCompetidor().getNome());
					System.out.println(passadasCompetidores.get(comparador).getFichasInscricoes().get(1).getCompetidor().getNome());
					
					comp3 = passadasCompetidores.get(comparador).getFichasInscricoes().get(0).getCompetidor();
					comp4 = passadasCompetidores.get(comparador).getFichasInscricoes().get(1).getCompetidor();
					
					if(comp1.getNome().equals(comp3.getNome())){
						comparador++;
						System.out.println("Entrou competidor 1 e 3");
					}
					else if(comp1.getNome().equals(comp4.getNome())){
						comparador++;
						System.out.println("Entrou competidor 1 e 4");
					}
					else if(comp2.getNome().equals(comp3.getNome())){
						comparador++;
						System.out.println("Entrou competidor 2 e 3");
					}
					else if(comp2.getNome().equals(comp4.getNome())){
						comparador++;
						System.out.println("Entrou competidor 2 e 4");
					} else {
						System.out.println("saiu do loop");
						
						System.out.println("removendo ");
						System.out.println(pas.getId());
						System.out.println(passadasCompetidores.get(comparador).getId());
						
						listaAuxiliar.add(passadasCompetidores.get(comparador));
						passadasCompetidores.remove(passadasCompetidores.get(comparador));
						passadasCompetidores.remove(pas);
						
						comparador=1;
						ver2=true;
					}
					
					posicao1++;
					System.out.println("saída do");
				} while (!ver2);

				// ajusta tamanho da posicao 1.
				posicao1 = passadasCompetidores.size();
			}
			
			//sai do loop quando posicao1 for mesmo tamanho que o array
			if((posicao1 + 1) <= passadasCompetidores.size()){
				ver1=true;
			
			}
			
		} while (!ver1);
		
		System.out.println("passadas competidores");
		for(Passada p: passadasCompetidores){
			System.out.println(p.getId());
		}
		System.out.println();
		System.out.println();
		System.out.println("lista auxiliar");
		for(Passada p : listaAuxiliar){
			System.out.println(p.getId());
		}
		System.out.println();
		System.out.println();
		System.out.println("lista auxiliar 2");
		for(Passada p : listaAuxiliar2){
			System.out.println(p.getId());
		}
		
		for (int i = 0; i < passadasCompetidores.size(); i++) {
			passadasCompetidores.get(i).setNumeroDupla(Long.valueOf(numerosOrdem.get(i)));
		}
		

		/*
		 * boolean teste = false; int contador = 0;
		 * 
		 * do { if (contador < passadasCompetidores.size()) {
		 * System.out.println(" primeiro loop "); System.out.println();
		 * 
		 * if ((contador + 1) == passadasCompetidores.size()) {
		 * System.out.println(); teste = true; System.out.println(
		 * " ultimo loop ");
		 * System.out.print(passadasCompetidores.get(contador).
		 * getFichasInscricoes().get(0).getId()); System.out.println(" - " +
		 * passadasCompetidores.get(contador).getFichasInscricoes().get(0).
		 * getCompetidor().getNome());
		 * System.out.print(passadasCompetidores.get(contador).
		 * getFichasInscricoes().get(1).getId()); System.out.println(" - " +
		 * passadasCompetidores.get(contador).getFichasInscricoes().get(1).
		 * getCompetidor().getNome());
		 * 
		 * } else {
		 * 
		 * // testa a ficha da passada atual com a próxima
		 * passadasCompetidores.get(contador).getFichasInscricoes();
		 * 
		 * for(int i=0; i <
		 * passadasCompetidores.get(contador).getFichasInscricoes().size();
		 * i++){ for(int k=0; k <
		 * passadasCompetidores.get(contador).getFichasInscricoes().size();
		 * k++){
		 * if(passadasCompetidores.get(contador).getFichasInscricoes().get(i).
		 * getCompetidor().getNome(). equals(passadasCompetidores.get(contador +
		 * 1).getFichasInscricoes().get(k).getCompetidor().getNome())){
		 * System.out.println("Entrou"); } } }
		 * 
		 * System.out.print(passadasCompetidores.get(contador).
		 * getFichasInscricoes().get(0).getId()); System.out.println(" - " +
		 * passadasCompetidores.get(contador).getFichasInscricoes().get(0).
		 * getCompetidor().getNome());
		 * System.out.print(passadasCompetidores.get(contador).
		 * getFichasInscricoes().get(1).getId()); System.out.println(" - " +
		 * passadasCompetidores.get(contador).getFichasInscricoes().get(1).
		 * getCompetidor().getNome());
		 * 
		 * System.out.println(" --------- ");
		 * 
		 * System.out.print(passadasCompetidores.get(contador +
		 * 1).getFichasInscricoes().get(0).getId()); System.out.println(" - " +
		 * passadasCompetidores.get(contador +
		 * 1).getFichasInscricoes().get(0).getCompetidor().getNome());
		 * System.out.print(passadasCompetidores.get(contador +
		 * 1).getFichasInscricoes().get(1).getId()); System.out.println(" - " +
		 * passadasCompetidores.get(contador +
		 * 1).getFichasInscricoes().get(1).getCompetidor().getNome());
		 * System.out.println(" --------- "); System.out.println(" --------- ");
		 * }
		 * 
		 * contador++; } } while (!teste);
		 */

		/*
		 * for (int i = 0; i < passadasCompetidores.size(); i++) {
		 * passadasCompetidores.get(i).setNumeroDupla(Long.valueOf(embaralhados.
		 * get(i))); }
		 * 
		 * for (Passada p : passadasCompetidores) {
		 * System.out.print(p.getNumeroDupla() + " - ");
		 * System.out.println(p.getId()); }
		 * 
		 * Collections.sort(passadasCompetidores, new Comparator<Passada>() {
		 * 
		 * @Override public int compare(Passada p1, Passada p2) { return
		 * p1.getNumeroDupla().compareTo(p2.getNumeroDupla()); }
		 * 
		 * });
		 * 
		 * for (Passada p : passadasCompetidores) {
		 * System.out.print(p.getNumeroDupla() + " - ");
		 * System.out.println(p.getId()); }
		 */
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
