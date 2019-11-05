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

		int pos1=0;
		
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
		
		passadas = embaralhar(passadas);
		/*
		do{
			System.out.print("Competidor 1: ");
			System.out.println(passadas.get(pos1).getFichasInscricoes().get(0).getCompetidor().getNome());
			System.out.print("Competidor 2: ");
			System.out.println(passadas.get(pos1).getFichasInscricoes().get(1).getCompetidor().getNome());
			System.out.println();
			
			//controlarOrdenacao(passadas,pos1);
			
			
			
			System.out.println();
			System.out.println();
			
			pos1++;
		}while(pos1!=passadas.size());*/
		
		System.out.println("tamanho do array " + passadas.size());

		for (Passada p : passadas) {
			System.out.print("Competidor 1: ");
			System.out.println(p.getFichasInscricoes().get(0).getCompetidor().getNome());
			System.out.print("Competidor 2: ");
			System.out.println(p.getFichasInscricoes().get(1).getCompetidor().getNome());
			System.out.println();
		}

		//passadas = controlarOrdenacao(passadas);

		System.out.println();
		System.out.println("Array pronto");
		System.out.println();

		return passadas;
	}

	private List<Passada> embaralhar(List<Passada> passadas) {
		
		boolean verificador=false;
		int pos1 = 0;
		
		List<Passada> listaAuxiliar1 = new ArrayList<Passada>();
		
		do{
			System.out.println("aqui");
			System.out.print("tamanho dos array: ");
			System.out.println("passadas " + passadas.size());
			System.out.println("listaAuxiliar1 " + listaAuxiliar1.size());
			if(pos1==passadas.size()){
				pos1=0;
			}
			
			if(listaAuxiliar1.size()==0){
				listaAuxiliar1.add(passadas.get(pos1));
				passadas.remove(passadas.get(pos1));
			} else {
				
				if (passadas.size() >= 5 && passadas.size()!=0 &&
						verificaPosicoesRepetidas(passadas.get(pos1), listaAuxiliar1.get(listaAuxiliar1.size()-1))){
					pos1++;
					continue;
				}
				
				if(listaAuxiliar1.size()>2 && passadas.size() >= 5 && passadas.size()!=0 &&
						verificaPosicoesRepetidas(passadas.get(pos1), listaAuxiliar1.get(listaAuxiliar1.size()-2))){
					pos1++;
					continue;
				}
				
				listaAuxiliar1.add(passadas.get(pos1));
				passadas.remove(passadas.get(pos1));
			}
			
			if(passadas.size()==0){
				verificador=true;
			}
		}while(!verificador);
		
		passadas = new ArrayList<Passada>();
		passadas.addAll(listaAuxiliar1);
		
		return passadas;
	}

	private void controlarOrdenacao(List<Passada> passadas, int pos1) {
		
		Passada pas1 = new Passada();
		pas1 = passadas.get(pos1);
		boolean ver = false;
			
		do{
			if((pos1+1) < passadas.size() && verificaPosicoesRepetidas(pas1, passadas.get(pos1+1))){
				System.out.println("proximo é repetido");
				ver = false;				
			} else {
				ver = true;
			}
			
			if((pos1+2) < passadas.size() && verificaPosicoesRepetidas(pas1, passadas.get(pos1+2))){
				System.out.println("pula um, próximo é repetido");
				ver = false;				
			} else {
				ver = true;
			}
			
			if(!ver){
				pos1++;
			}
			
			if(pos1==passadas.size()){
				pos1=0;
			}
			System.out.println("passou");
		}while(!ver);
		
		if(ver){
			System.out.println("saiu");
		}

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
