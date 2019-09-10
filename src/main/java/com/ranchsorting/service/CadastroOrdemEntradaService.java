package com.ranchsorting.service;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import javax.inject.Inject;
import javax.validation.ConstraintViolationException;

import com.ranchsorting.model.FichaInscricao;
import com.ranchsorting.model.OrdemEntrada;
import com.ranchsorting.model.Passada;
import com.ranchsorting.model.StatusFicha;
import com.ranchsorting.repository.OrdensEntradas;
import com.ranchsorting.repository.filter.FichaInscricaoFilter;
import com.ranchsorting.util.jpa.Transactional;

public class CadastroOrdemEntradaService implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private OrdensEntradas ordens;

	@Transactional
	public OrdemEntrada salvar(OrdemEntrada ordem) {
		// return ordens.guardar(ordem);

		try {
			return ordens.guardar(ordem);

		} catch (NullPointerException ex) {
			throw new NegocioException("Ocorreu algum promblema na gravação da Ordem de Entrada."
					+ "Entre em contato com o administrador do Sistema. (NullPointerException)");
		} catch (ConstraintViolationException ex) {
			throw new NegocioException("Ocorreu algum promblema na gravação da Ordem de Entrada."
					+ "Entre em contato com o administrador do Sistema. (ConstraintViolationException)");
		} catch (ArithmeticException ex) {
			throw new NegocioException("Ocorreu algum promblema na gravação da Ordem de Entrada."
					+ "Entre em contato com o administrador do Sistema. (ArithmeticException)");
		} catch (RuntimeException ex) {
			throw new NegocioException("Ocorreu algum promblema na gravação da Ordem de Entrada."
					+ "Entre em contato com o administrador do Sistema. (RuntimeException)");
		} catch (Exception ex) {
			throw new NegocioException("Ocorreu algum promblema na gravação da Ordem de Entrada."
					+ "Entre em contato com o administrador do Sistema. (Exception)");
		}
	}

	public List<Passada> geraPassadaAmador(OrdemEntrada ordem, List<FichaInscricao> fichasFiltradas,
			FichaInscricaoFilter filtro) {

		List<Passada> passadasCompetidores = new ArrayList<>();

		boolean verifica1 = false;
		boolean verifica2 = false;
		boolean verifica3 = false;

		int num1;
		int num2;
		int num3 = 0;
		int num4 = 0;

		Random gerador = new Random();
		Collections.shuffle(fichasFiltradas);

		List<FichaInscricao> listaInscricaoAuxiliar1;
		List<FichaInscricao> listaInscricaoAuxiliar2 = new ArrayList<>();

		FichaInscricao fic1;
		FichaInscricao fic2;
		Passada pas1;

		listaInscricaoAuxiliar2.addAll(fichasFiltradas);

		do {

			if (num4 == 1000000) {
				throw new NegocioException("Expirou o tempo de geração da ordem.");
			}

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

			// teste para tratar ordem dos competidores para não repetir
			// sequência
			if (passadasCompetidores.size() > 1) {

				if (passadasCompetidores.get(passadasCompetidores.size() - 1).getFichasInscricoes().get(0)
						.getCompetidor().getNome().equals(fic1.getCompetidor().getNome()) && num3 < 2) {
					num3++;
					num4++;
					continue;
				}
				if (passadasCompetidores.get(passadasCompetidores.size() - 1).getFichasInscricoes().get(0)
						.getCompetidor().getNome().equals(fic2.getCompetidor().getNome())) {
					num3++;
					num4++;
					continue;
				}
				if (passadasCompetidores.get(passadasCompetidores.size() - 1).getFichasInscricoes().get(1)
						.getCompetidor().getNome().equals(fic1.getCompetidor().getNome())) {
					num3++;
					num4++;
					continue;
				}
				if (passadasCompetidores.get(passadasCompetidores.size() - 1).getFichasInscricoes().get(1)
						.getCompetidor().getNome().equals(fic2.getCompetidor().getNome())) {
					num3++;
					num4++;
					continue;
				}

			}

			// se os nomes forem iguais, loop
			if (fic1.getCompetidor().getNome().equals(fic2.getCompetidor().getNome())) {
				num4++;
				continue;
			}

			// verifica se tem dupla repetida
			if (passadasCompetidores != null || passadasCompetidores.size() != 0) {
				verifica3 = true;

				// percorre todo o array principal
				for (Passada p : passadasCompetidores) {

					if (num4 == 1000000) {
						throw new NegocioException("Expirou o tempo de geração da ordem.");
					}

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
					// para expirar o tempo de geracao da ordem. se não fica em
					// loop infinito
					num4++;
				}

				// se encontrou repetido
				if (verifica3 == false) {
					num4++;
					continue;
				}
			}

			fic1.setCampeonato(filtro.getObjCampeonato());
			fic1.setEtapa(filtro.getObjEtapa());
			fic1.setDivisao(filtro.getObjDivisao());
			fic1.setPassada(pas1);

			fic2.setCampeonato(filtro.getObjCampeonato());
			fic2.setEtapa(filtro.getObjEtapa());
			fic2.setDivisao(filtro.getObjDivisao());
			fic2.setPassada(pas1);
			fic1.setStatusFicha(StatusFicha.EMORDEM);
			fic2.setStatusFicha(StatusFicha.EMORDEM);

			// adiciona as fichas em uma lista secundária
			listaInscricaoAuxiliar1.add(fic1);
			listaInscricaoAuxiliar1.add(fic2);

			// adiciona as fichas em uma passada
			pas1.getFichasInscricoes().addAll(listaInscricaoAuxiliar1);
			pas1.setOrdemEntrada(ordem);

			// adiciona a passada na lista principal
			passadasCompetidores.add(pas1);

			num3 = 0;

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

					num1 = gerador.nextInt(listaInscricaoAuxiliar2.size() - 1);
					fic2 = listaInscricaoAuxiliar2.get(num1);
					System.out.println(fic1.getCompetidor().getNome());
					// se competidores iguais, loop
					if (fic1.getCompetidor().getNome().equals(fic2.getCompetidor().getNome())) {
						continue;
					}

					// para testar duplas repetidas
					verifica3 = true;

					for (Passada p : passadasCompetidores) {

						if (num4 == 1000000) {
							throw new NegocioException("Expirou o tempo de geração da ordem.");
						}

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
						// para expirar o tempo de geracao da ordem. se não fica
						// em loop infinito
						num4++;

					}

					if (verifica3 == false) {
						continue;
					}

					verifica2 = true;

					fic1.setCampeonato(filtro.getObjCampeonato());
					fic1.setEtapa(filtro.getObjEtapa());
					fic1.setDivisao(filtro.getObjDivisao());
					fic1.setPassada(pas1);

					FichaInscricao ficAux = new FichaInscricao();
					ficAux = fic2;
					fic2 = new FichaInscricao();

					fic2.setCampeonato(filtro.getObjCampeonato());
					fic2.setEtapa(filtro.getObjEtapa());
					fic2.setDivisao(filtro.getObjDivisao());
					fic2.setPassada(pas1);
					fic2.setObs(" (Sorteada)");
					fic2.setCompetidor(ficAux.getCompetidor());
					fic2.setDataInscricao(ficAux.getDataInscricao());
					fic2.setDataAlteracao(ficAux.getDataAlteracao());
					fic2.setQntFichas(new Long(1));
					fic2.setValorComprado(BigDecimal.ONE);
					fic2.setValorPago(BigDecimal.ONE);

					fic1.setStatusFicha(StatusFicha.EMORDEM);
					fic2.setStatusFicha(StatusFicha.EMORDEM);

					listaInscricaoAuxiliar1.add(fic1);
					listaInscricaoAuxiliar1.add(fic2);

					pas1.getFichasInscricoes().addAll(listaInscricaoAuxiliar1);
					pas1.setOrdemEntrada(ordem);
					passadasCompetidores.add(pas1);

					fichasFiltradas.remove(fic1);
					System.out.println("passou");
				} while (!verifica2);
			}

			if (fichasFiltradas.size() == 0) {
				verifica1 = true;
			}
		} while (!verifica1);

		int numero = 1;

		for (Passada p : passadasCompetidores) {
			p.setNumeroDupla(new Long(numero));
			numero++;
		}

		return passadasCompetidores;
	}

	public List<Passada> trataOutrasPassadas(List<Passada> passadasCompetidores, FichaInscricaoFilter filtro) {

		List<Passada> listaAuxiliar1 = new ArrayList<>();
		List<Passada> listaAuxiliar2 = new ArrayList<>();

		boolean teste = true;
		int pos1 = 0;
		int pos2 = 0;

		// embaralha
		Collections.shuffle(passadasCompetidores);

		// aqui eu tenho duas listas que recebem a informação principal
		for (Passada p : passadasCompetidores) {
			if (teste) {
				listaAuxiliar1.add(p);
				teste = false;
			} else {
				listaAuxiliar2.add(p);
				teste = true;
			}
		}

		// reinicio a lista principal
		passadasCompetidores = new ArrayList<>();

		FichaInscricao f1;
		FichaInscricao f2;
		FichaInscricao f3;
		FichaInscricao f4;

		do {
			f1 = new FichaInscricao();
			f2 = new FichaInscricao();
			f3 = new FichaInscricao();
			f4 = new FichaInscricao();

			if (listaAuxiliar1.size() == 0) {
				break;
			}

			f1 = listaAuxiliar1.get(pos1).getFichasInscricoes().get(0);
			f2 = listaAuxiliar1.get(pos1).getFichasInscricoes().get(1);

			f3 = listaAuxiliar2.get(pos2).getFichasInscricoes().get(0);
			f4 = listaAuxiliar2.get(pos2).getFichasInscricoes().get(1);

			if (f1.getCompetidor().getNome().equals(f3.getCompetidor().getNome())
					&& f2.getCompetidor().getNome().equals(f4.getCompetidor().getNome())) {

				if (teste) {
					pos1++;
					teste = false;
				} else {
					pos2++;
					teste = true;
				}
				continue;
			}

			if (f1.getCompetidor().getNome().equals(f4.getCompetidor().getNome())
					&& f2.getCompetidor().getNome().equals(f3.getCompetidor().getNome())) {
				if (teste) {
					pos1++;
					teste = false;
				} else {
					pos2++;
					teste = true;
				}
				continue;
			}

			passadasCompetidores.add(listaAuxiliar1.get(pos1));
			passadasCompetidores.add(listaAuxiliar2.get(pos2));

			listaAuxiliar1.remove(listaAuxiliar1.get(pos1));
			listaAuxiliar2.remove(listaAuxiliar2.get(pos2));

		} while (listaAuxiliar1.size() > 0 && listaAuxiliar2.size() > 0);

		int contador = 1;

		for (Passada p : passadasCompetidores) {
			p.setNumeroDupla(new Long(contador));
			contador++;
		}

		return passadasCompetidores;
	}

	public List<Passada> trataOutrasPassadas2(List<Passada> passadasCompetidores, FichaInscricaoFilter filtro) {

		// embaralha
		Collections.shuffle(passadasCompetidores);
		
		int contador = 1;

		for (Passada p : passadasCompetidores) {
			p.setNumeroDupla(new Long(contador));
			contador++;
		}

		return passadasCompetidores;
	}

}
