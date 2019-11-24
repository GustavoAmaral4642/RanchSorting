package com.ranchsorting.service;

import java.io.Serializable; 
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.ranchsorting.model.FichaInscricao;
import com.ranchsorting.model.OrdemEntrada;
import com.ranchsorting.model.Passada;
import com.ranchsorting.model.StatusFicha;

public class MontaOrdemEntradaService implements Serializable {

	private static final long serialVersionUID = 1L;

	public List<Passada> montarOrdemEntrada(List<Passada> passadas, OrdemEntrada ordem) {

		Collections.shuffle(passadas);
		
		System.out.println("tamanho do original " + passadas.size());

		for (Passada p : passadas) {
			System.out.print("Competidor 1: ");
			System.out.println(p.getFichasInscricoes().get(0).getCompetidor().getNome());
			System.out.print("Competidor 2: ");
			System.out.println(p.getFichasInscricoes().get(1).getCompetidor().getNome());
			System.out.println();
		}
		
		System.out.println("Começou");
		
		passadas = embaralhar(passadas, ordem);
		
		System.out.println("tamanho do array " + passadas.size());

		for (Passada p : passadas) {
			System.out.print("Competidor 1: ");
			System.out.println(p.getFichasInscricoes().get(0).getCompetidor().getNome());
			System.out.print("Competidor 2: ");
			System.out.println(p.getFichasInscricoes().get(1).getCompetidor().getNome());
			System.out.println();
		}
		System.out.println();
		System.out.println("Array pronto");
		System.out.println();

		return passadas;
	}

	private List<Passada> embaralhar(List<Passada> passadas, OrdemEntrada ordem) {
		
		boolean verificador=false;
		int pos1 = 0;
		int numeroDupla = 1;
		int contador=0; // contador para resetar tudo
		
		List<Passada> listaAuxiliar1 = new ArrayList<Passada>();
		List<Passada> listaAuxiliar2 = new ArrayList<Passada>(); // backup
		listaAuxiliar2.addAll(passadas);
		
		do{
			System.out.println("aqui");
			System.out.print("tamanho dos array: ");
			System.out.println("passadas " + passadas.size());
			System.out.println("listaAuxiliar1 " + listaAuxiliar1.size());
			
			//reseta tudo
			if(contador==100000){
				listaAuxiliar1 = new ArrayList<>();
				passadas = new ArrayList<>();
				passadas.addAll(listaAuxiliar2);
				numeroDupla=1;
				pos1=0;
				contador=0;
			}
			
			if(pos1==passadas.size()) {
				pos1=0;
			}
			
			if(listaAuxiliar1.size()==0) {
				passadas.get(pos1).setNumeroDupla(new Long(numeroDupla));
				passadas.get(pos1).setOrdemEntrada(ordem);
				listaAuxiliar1.add(passadas.get(pos1));
				System.out.println();
				System.out.println("Comparou e adicionou");
				System.out.println("passadas");
				System.out.println(passadas.get(pos1).getFichasInscricoes().get(0).getCompetidor().getNome());
				System.out.println(passadas.get(pos1).getFichasInscricoes().get(1).getCompetidor().getNome());
				System.out.println("ListaAuxiliar");
				System.out.println(listaAuxiliar1.get(listaAuxiliar1.size()-1).getFichasInscricoes().get(0).getCompetidor().getNome());
				System.out.println(listaAuxiliar1.get(listaAuxiliar1.size()-1).getFichasInscricoes().get(1).getCompetidor().getNome());
				System.out.println("-----------------");
			} else {
				contador++;
				System.out.println();
				System.out.println("Comparou");
				System.out.println("passadas");
				System.out.println(passadas.get(pos1).getFichasInscricoes().get(0).getCompetidor().getNome());
				System.out.println(passadas.get(pos1).getFichasInscricoes().get(1).getCompetidor().getNome());
				System.out.println("ListaAuxiliar -1");
				System.out.println(listaAuxiliar1.get(listaAuxiliar1.size()-1).getFichasInscricoes().get(0).getCompetidor().getNome());
				System.out.println(listaAuxiliar1.get(listaAuxiliar1.size()-1).getFichasInscricoes().get(1).getCompetidor().getNome());
				System.out.println("-----------------");
				System.out.println("ListaAuxiliar -2");
				System.out.println(listaAuxiliar1.get(listaAuxiliar1.size()-1).getFichasInscricoes().get(0).getCompetidor().getNome());
				System.out.println(listaAuxiliar1.get(listaAuxiliar1.size()-1).getFichasInscricoes().get(1).getCompetidor().getNome());
				System.out.println("-----------------");
				
				if (passadas.size()>2 && verificaPosicoesRepetidas(passadas.get(pos1),
						 listaAuxiliar1.get(listaAuxiliar1.size()-1))){
					pos1++;
					
					System.out.println();
					System.out.println("pulou");
					System.out.println("valor de pos1 " +pos1);
					System.out.println();
					continue;
				}
				if (passadas.size()>4 && 
						listaAuxiliar1.size()>2 
						&& verificaPosicoesRepetidas(passadas.get(pos1),
						 listaAuxiliar1.get(listaAuxiliar1.size()-2))){
					pos1++;
					contador++;
					System.out.println();
					System.out.println("pulou segundo if");
					System.out.println("valor de pos1 " +pos1);
					System.out.println();
					continue;
				}
				System.out.println();
				System.out.println("adicionou");
				System.out.println();
				
				passadas.get(pos1).setNumeroDupla(new Long(numeroDupla));
				passadas.get(pos1).setOrdemEntrada(ordem);
				listaAuxiliar1.add(passadas.get(pos1));
				
			}

			passadas.remove(pos1);
			numeroDupla++;
			contador++;
			
			if(passadas.size()==0) {
				verificador=true;
			}

		}while(!verificador);
		
		passadas = new ArrayList<Passada>();
		passadas.addAll(listaAuxiliar1);
		
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
	public boolean verificaPosicoesRepetidas(Passada pas1, Passada pas2) {

		// se a comparação for verdadeira, continua interação
		if (comparaNomeFichas(pas1.getFichasInscricoes(), pas2.getFichasInscricoes())) {
			return true;
		}
		return false;
	}

}
