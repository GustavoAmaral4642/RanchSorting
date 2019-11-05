package com.ranchsorting.service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.ranchsorting.model.FichaInscricao;
import com.ranchsorting.model.Passada;

public class MontaOrdemEntradaService implements Serializable {

	private static final long serialVersionUID = 1L;

	public List<Passada> montarOrdemEntrada(List<Passada> passadas) {

		int pos1 = 0;
		boolean verificador1 = false;

		List<Passada> listaAuxiliar1 = new ArrayList<Passada>();
		List<Passada> listaAuxiliar2 = new ArrayList<Passada>(); // backup
		List<Passada> listaAuxiliar3 = new ArrayList<Passada>(); // resto

		Collections.shuffle(passadas);
		listaAuxiliar1.addAll(passadas);
		listaAuxiliar2.addAll(passadas);

		System.out.println("tamanho do original " + passadas.size());
		System.out.println("tamanho do array 3 " + listaAuxiliar3.size());
		System.out.println("tamanho do array 1 " + listaAuxiliar1.size());

		
		for(Passada p : passadas){
			if()
			
		}
		
		
		
		//passadas = new ArrayList<Passada>();

		do {

			// se o array de passadas estiver vazio
			if (arrayVazio(passadas)) {
				passadas.add(listaAuxiliar1.get(pos1));

			} else {
				if (passadas.size() == 1) {
					System.out.println("tamanho 1");
					if (verificaPosicoesRepetidas(passadas, listaAuxiliar1, pos1, 1)) {
						listaAuxiliar3.add(listaAuxiliar1.get(pos1));
						System.out.println("repetido if 1");
					} else {
						System.out.println("não repetido if 1");
						passadas.add(listaAuxiliar1.get(pos1));
					}
				} else if (passadas.size() == 2) {
					if (verificaPosicoesRepetidas(passadas, listaAuxiliar1, pos1, 2)) {
						listaAuxiliar3.add(listaAuxiliar1.get(pos1));
						System.out.println("repetido if 2");
					} else {
						System.out.println("não repetido if 2");
						passadas.add(listaAuxiliar1.get(pos1));
					}
				} else if (passadas.size() == 3) {
					if (verificaPosicoesRepetidas(passadas, listaAuxiliar1, pos1, 3)) {
						listaAuxiliar3.add(listaAuxiliar1.get(pos1));
						System.out.println("repetido if 3");
					} else {
						System.out.println("não repetido if 3");
						passadas.add(listaAuxiliar1.get(pos1));
					}
				} else {
					if (verificaPosicoesRepetidas(passadas, listaAuxiliar1, pos1, 3)) {
						System.out.println("repetido if 4");
						listaAuxiliar3.add(listaAuxiliar1.get(pos1));
					} else {
						System.out.println("não repetido if 4");
						passadas.add(listaAuxiliar1.get(pos1));
					}
				}
			}

			listaAuxiliar1.remove(pos1);
		} while (listaAuxiliar1.size() != 0);

		System.out.println("tamanho do original " + passadas.size());
		System.out.println("tamanho do array 3 " + listaAuxiliar3.size());
		System.out.println("tamanho do array 1 " + listaAuxiliar1.size());

		do {
			incluirPosicoesRepetidas(passadas, listaAuxiliar1.get(pos1), 3);
			listaAuxiliar1.remove(pos1);
		} while (listaAuxiliar3.size() != 0);

		listaAuxiliar1 = passadas = new ArrayList<>();
		passadas.addAll(listaAuxiliar1);

		System.out.println("tamanho do original " + passadas.size());
		System.out.println("tamanho do array 3 " + listaAuxiliar3.size());
		System.out.println("tamanho do array 1 " + listaAuxiliar1.size());

		return passadas;
	}

	public boolean comparaNomeFichas(List<FichaInscricao> primeira, List<FichaInscricao> segunda) {

		if (primeira.get(0).getCompetidor().getNome().equals(segunda.get(0).getCompetidor().getNome())) {
			return true;
		} else if (primeira.get(0).getCompetidor().getNome().equals(segunda.get(1).getCompetidor().getNome())) {
			return true;
		} else if (primeira.get(1).getCompetidor().getNome().equals(segunda.get(0).getCompetidor().getNome())) {
			return true;
		} else if (primeira.get(1).getCompetidor().getNome().equals(segunda.get(1).getCompetidor().getNome())) {
			return true;
		}

		return false;
	}

	public boolean arrayVazio(List<Passada> passadas) {
		return passadas.size() == 0;
	}

	// método para testar fichas anteriores ver se os competidores são
	// iguais
	public boolean verificaPosicoesRepetidasParaTras(List<Passada> pas1, List<Passada> pas2, int pos1, int iterador) {

		for (int i = pos1-iterador; i < pos1; i++) {
			// se a comparação for verdadeira, continua interação
			if (comparaNomeFichas(pas1.get(i).getFichasInscricoes(), pas2.get(pos1).getFichasInscricoes())) {
				return true;
			}
		}
		return false;
	}

	// método para testar fichas anteriores ver se os competidores são
	// iguais
	public boolean verificaPosicoesRepetidasParaFrente(List<Passada> pas1, List<Passada> pas2, int pos1, int iterador) {

		for (int i = pos1+iterador; i > pos1; i--) {
			// se a comparação for verdadeira, continua interação
			if (comparaNomeFichas(pas1.get(i).getFichasInscricoes(), pas2.get(pos1).getFichasInscricoes())) {
				return true;
			}
		}
		return false;
	}
	
	private List<Passada> incluirPosicoesRepetidas(List<Passada> passadas, Passada passada, int iterador) {

		for (int j = iterador; j < passadas.size()-iterador; j++) {
			if (comparaNomeFichas(passadas.get(j).getFichasInscricoes(), passada.getFichasInscricoes())) {
				System.out.println("incluir repetidos if 1");
				continue;
			}

			if ((j + iterador) < passadas.size()) {
				if (comparaNomeFichas(passadas.get(j + iterador).getFichasInscricoes(),
						listaAuxiliar3.get(i).getFichasInscricoes())) {
					System.out.println("incluir repetidos if 2");
					continue;
				}
			}

			int cont1 = 0;

			List<Passada> auxPas1 = new ArrayList<>();
			auxPas1.addAll(passadas);
			passadas = new ArrayList<Passada>();

			Passada p = new Passada();
			p = auxPas1.get(j);

			for (Passada p1 : auxPas1) {
				if (cont1 == j) {
					passadas.add(listaAuxiliar3.get(i));
					cont1++;
					System.out.println("aqui 1");
				} else if (cont1 == j + 1) {
					passadas.add(p);
					cont1++;
					System.out.println("aqui 2");
				} else {
					cont1++;
					passadas.add(p1);
					System.out.println("aqui 3");
				}
			}
		}

		return passadas;
	}
}
