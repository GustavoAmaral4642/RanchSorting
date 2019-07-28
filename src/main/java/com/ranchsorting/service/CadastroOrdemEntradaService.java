package com.ranchsorting.service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import javax.inject.Inject;

import com.ranchsorting.model.Competidor;
import com.ranchsorting.model.FichaInscricao;
import com.ranchsorting.model.OrdemEntrada;
import com.ranchsorting.model.Passada;
import com.ranchsorting.repository.OrdensEntradas;
import com.ranchsorting.util.jpa.Transactional;

public class CadastroOrdemEntradaService implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private OrdensEntradas ordens;

	@Transactional
	public OrdemEntrada salvar(OrdemEntrada ordem) {

		return ordens.guardar(ordem);

		/*
		 * try {
		 * 
		 * } catch (NullPointerException ex) { throw new NegocioException(
		 * "Ocorreu algum promblema na gravação da Ordem de Entrada." +
		 * "Entre em contato com o administrador do Sistema. (NullPointerException)"
		 * ); } catch (ConstraintViolationException ex) { throw new
		 * NegocioException(
		 * "Ocorreu algum promblema na gravação da Ordem de Entrada." +
		 * "Entre em contato com o administrador do Sistema. (ConstraintViolationException)"
		 * ); } catch (ArithmeticException ex) { throw new NegocioException(
		 * "Ocorreu algum promblema na gravação da Ordem de Entrada." +
		 * "Entre em contato com o administrador do Sistema. (ArithmeticException)"
		 * ); } catch (RuntimeException ex) { throw new NegocioException(
		 * "Ocorreu algum promblema na gravação da Ordem de Entrada." +
		 * "Entre em contato com o administrador do Sistema. (RuntimeException)"
		 * ); } catch (Exception ex) { throw new NegocioException(
		 * "Ocorreu algum promblema na gravação da Ordem de Entrada." +
		 * "Entre em contato com o administrador do Sistema. (Exception)"); }
		 */}

	public List<Passada> geraPassadaAmador(OrdemEntrada ordem, List<FichaInscricao> fichasFiltradas) {
		List<Passada> passadasCompetidores = new ArrayList<>();

		boolean verifica1 = false;
		boolean verifica2 = false;
		boolean verifica3 = false;

		int num1;
		int num2;

		Random gerador = new Random();
		Random gerador2 = new Random();

		List<FichaInscricao> listaInscricaoAuxiliar1;
		List<FichaInscricao> listaInscricaoAuxiliar2 = new ArrayList<>();

		FichaInscricao fic1;
		FichaInscricao fic2;
		Passada pas1;

		listaInscricaoAuxiliar2.addAll(fichasFiltradas);

		do {
			num1 = 0;
			num2 = 0;

			fic1 = new FichaInscricao();
			fic2 = new FichaInscricao();
			pas1 = new Passada();
			listaInscricaoAuxiliar1 = new ArrayList<>();

			// chega uma hora que os numeros gerados são iguais
			if (fichasFiltradas.size() == 2) {
				num1 = 0;
				num2 = 1;
			} else {
				// gerar numeros aleatórios
				num1 = gerador.nextInt(fichasFiltradas.size() - 1);
				num2 = gerador.nextInt(fichasFiltradas.size() - 1);
			}

			// se os dois numeros forem iguais, loop
			if (num1 == num2) {
				continue;
			}

			// pega as fichas com os numeros correspondentes
			fic1 = fichasFiltradas.get(num1);
			fic2 = fichasFiltradas.get(num2);

			// se os nomes forem iguais, loop
			if (fic1.getCompetidor().getNome().equals(fic2.getCompetidor().getNome())) {
				continue;
			}

			// verifica se tem dupla repetida
			if (passadasCompetidores != null || passadasCompetidores.size() != 0) {
				verifica3 = true;

				// percorre todo o array principal
				for (Passada p : passadasCompetidores) {

					// se achar competidor igual, verifica3 fica false
					if (p.getFichasInscricoes().get(0).getCompetidor().getNome().equals(fic1.getCompetidor().getNome())
							&& p.getFichasInscricoes().get(1).getCompetidor().getNome()
									.equals(fic2.getCompetidor().getNome())) {
						verifica3 = false;

					}

					// teste invertido se achar competidor igual, verifica3
					// fica false
					if (p.getFichasInscricoes().get(1).getCompetidor().getNome().equals(fic1.getCompetidor().getNome())
							&& p.getFichasInscricoes().get(0).getCompetidor().getNome()
									.equals(fic2.getCompetidor().getNome())) {
						verifica3 = false;

					}

				}

				// se encontrou repetido
				if (verifica3 == false) {
					continue;
				}
			}

			fic1.setPassada(pas1);
			fic2.setPassada(pas1);

			// adiciona as fichas em uma lista secundária
			listaInscricaoAuxiliar1.add(fic1);
			listaInscricaoAuxiliar1.add(fic2);

			// adiciona as fichas em uma passada
			pas1.getFichasInscricoes().addAll(listaInscricaoAuxiliar1);
			pas1.setOrdemEntrada(ordem);

			// adiciona a passada na lista principal
			passadasCompetidores.add(pas1);

			// remove as fichas da lista de fichas primária
			fichasFiltradas.remove(fic1);
			fichasFiltradas.remove(fic2);

			// se sobrar 1 ficha, entra aqui. caso de fichas impares
			if (fichasFiltradas.size() == 1) {
				// enquanto as duplas não forem diferentes fica aqui
				do {
					num1 = 0;
					num2 = 0;

					fic1 = new FichaInscricao();
					fic2 = new FichaInscricao();
					pas1 = new Passada();
					listaInscricaoAuxiliar1 = new ArrayList<>();

					// pega a ultima ficha da lista.
					fic1 = fichasFiltradas.get(0);
					// pega uma ficha qualquer da lista terciária
					fic2 = listaInscricaoAuxiliar2.get(num1);

					// se competidores iguais, loop
					if (fic1.getCompetidor().getNome().equals(fic2.getCompetidor().getNome())) {
						continue;
					}

					// para testar duplas repetidas
					verifica3 = true;

					for (Passada p : passadasCompetidores) {

						if (p.getFichasInscricoes().get(0).getCompetidor().getNome()
								.equals(fic1.getCompetidor().getNome())
								&& p.getFichasInscricoes().get(1).getCompetidor().getNome()
										.equals(fic2.getCompetidor().getNome())) {
							verifica3 = false;

						}
						if (p.getFichasInscricoes().get(1).getCompetidor().getNome()
								.equals(fic1.getCompetidor().getNome())
								&& p.getFichasInscricoes().get(0).getCompetidor().getNome()
										.equals(fic2.getCompetidor().getNome())) {
							verifica3 = false;

						}

					}

					if (verifica3 == false) {
						continue;
					}

					verifica2 = true;

					fic1.setPassada(pas1);
					fic2.setPassada(pas1);
					listaInscricaoAuxiliar1.add(fic1);
					listaInscricaoAuxiliar1.add(fic2);

					pas1.getFichasInscricoes().addAll(listaInscricaoAuxiliar1);
					passadasCompetidores.add(pas1);
					pas1.setOrdemEntrada(ordem);

					fichasFiltradas.remove(fic1);

				} while (!verifica2);
			}

			if (fichasFiltradas.size() == 0) {
				verifica1 = true;
			}
		} while (!verifica1);

		return passadasCompetidores;
	}

	public List<Passada> embaralhaPassadas(List<Passada> passadasCompetidores) {

		System.out.println("entra aqui");

		List<Integer> numerosOrdem = new ArrayList<>();
		List<Passada> listaAuxiliar = new ArrayList<>();
		List<Passada> listaAuxiliar2 = new ArrayList<>();

		for (int i = 0; i < passadasCompetidores.size(); i++) {
			numerosOrdem.add(i + 1);
		}

		// embaralha
		Collections.shuffle(passadasCompetidores);

		boolean ver1 = false;
		boolean ver2 = false;
		boolean ver3 = false;

		int posicao1 = 0;
		int comparador = 1;
		int tamanhoArrayPrincipal = passadasCompetidores.size();
		
		do {

			Competidor comp1 = new Competidor();
			Competidor comp2 = new Competidor();
			Competidor comp3 = new Competidor();
			Competidor comp4 = new Competidor();

			Passada pas = new Passada();
			Passada pas2 = new Passada();
			
			if (listaAuxiliar.size() == 0) {
				// se a lista auxiliar estiver vazia, alimenta com o
				// primeiro
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
					
					comp3 = new Competidor();
					comp4 = new Competidor();
					pas2 = passadasCompetidores.get(comparador);
					
					// pega os dois competidores da ficha a ser comparada
					comp3 = pas2.getFichasInscricoes().get(0).getCompetidor();
					comp4 = pas2.getFichasInscricoes().get(1).getCompetidor();

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
						System.out.println("if 1");
						System.out.println("nome competidor1: " + comp1.getNome());
						System.out.println("nome competidor2: " + comp2.getNome());
						System.out.println("nome competidor3: " + comp3.getNome());
						System.out.println("nome competidor4: " + comp4.getNome());
						System.out.println("comparador: " + comparador);
						System.out.println("Posicao1: " + posicao1);
						System.out.println("tamanho: " + passadasCompetidores.size());
						// se os competidores não forem iguais
						// adiciona a passada não inseria a uma lista
						// auxiliar e
						// remove da lista que está sendo lida
						listaAuxiliar.add(passadasCompetidores.get(comparador));
						passadasCompetidores.remove(pas2);

						// reseta as variáveis de comparaçao.
						posicao1 = 0;
						comparador = 0;
						
						
						// muda variável para sair do loop secundário
						ver2 = true;

						// se o tamanho da lista auxiliar for do mesmo
						// tamanho
						// que a lista pricipal, torna a ver3 true para sair
						// do
						// loop principal
						if (listaAuxiliar.size() >= tamanhoArrayPrincipal) {
							ver3 = true;
						}
					}

					// se o tamanho de passadas competidores for 0 e
					// comparador
					// tiver ser o mesmo que o tamanho do array
					if (passadasCompetidores.size() != 0 && comparador == passadasCompetidores.size()) {
						posicao1 = 0;
						comparador = 0;

						System.out.println("if 2");
						System.out.println("nome competidor1: " + comp1.getNome());
						System.out.println("nome competidor1: " + comp2.getNome());
						System.out.println("nome competidor1: " + comp3.getNome());
						System.out.println("nome competidor1: " + comp4.getNome());

						
						// se os competidores não forem iguais
						// adiciona a passada não inseria a uma lista
						// auxiliar e
						// remove da lista que está sendo lida
						listaAuxiliar.add(passadasCompetidores.get(comparador));
						passadasCompetidores.remove(passadasCompetidores.get(comparador));

						// muda variável para sair do loop secundário
						ver2 = true;

						// se o tamanho da lista auxiliar for do mesmo
						// tamanho
						// que a lista pricipal, torna a ver3 true para sair
						// do
						// loop principal
						if (listaAuxiliar.size() >= tamanhoArrayPrincipal) {
							ver3 = true;
						}
					}

				} while (!ver2);
				System.out.println("saiu do loop2");
				// ajusta tamanho da posicao 1.
				posicao1 = 0;
			}

			// sai do loop quando posicao1 for mesmo tamanho que o array
			if (ver2 == true && ver3 == true) {
				ver1 = true;
			}

		} while (!ver1);
		
		passadasCompetidores = new ArrayList<>();
		passadasCompetidores.addAll(listaAuxiliar);
		
		System.out.println(passadasCompetidores.size());
		
		return passadasCompetidores;
	}

}
