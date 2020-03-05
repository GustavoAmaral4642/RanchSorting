package com.ranchsorting.service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import com.ranchsorting.model.FichaInscricao;
import com.ranchsorting.model.Passada;
import com.ranchsorting.model.StatusFicha;

public class EmbaralhaPassadasService implements Serializable {

	private static final long serialVersionUID = 1L;

	// vai tratar passadas da competição Amador
	public List<Passada> embaralharPassadas(List<FichaInscricao> fichasFiltradas, List<Passada> passadasCompetidores) {

		if (fichasFiltradas == null) {
			throw new NegocioException("Não existe fichas para serem embaralhadas");
		}
		
		System.out.println("abaixo");
		for (Passada p : passadasCompetidores) {
			System.out.print("Competidor 1: ");
			System.out.println(p.getFichasInscricoes().get(0).getCompetidor().getNome());
			System.out.print("Competidor 2: ");
			System.out.println(p.getFichasInscricoes().get(1).getCompetidor().getNome());
			System.out.println();
			System.out.print("Id da passada: ");
			System.out.println(p.getId());
			System.out.println();
		}
		System.out.println("acima");
		// chama o método para embaralhar a lista de passadas.
		passadasCompetidores.addAll(loopEmbaralhador(fichasFiltradas));
		
		return passadasCompetidores;
	}

	// este loop embaralha as passadas
	private List<Passada> loopEmbaralhador(List<FichaInscricao> fichasFiltradas) {

		List<FichaInscricao> fichasBackup = new ArrayList<>();
		// cria uma ficha de backup para não perder a referencia
		fichasBackup.addAll(fichasFiltradas);

		int pos1 = 0;
		int pos2 = 1;
		int contador = 0;

		FichaInscricao f1;
		FichaInscricao f2;
		Passada p1;

		// embaralha a lista de fichas principal
		Collections.shuffle(fichasFiltradas);
		List<Passada> passadas = new ArrayList<Passada>();

		do {
			// reseta tudo pra fazer de novo!
			if (contador == 10000) {
				fichasFiltradas = new ArrayList<FichaInscricao>();
				passadas = new ArrayList<>();
				fichasFiltradas = fichasBackup;
				Collections.shuffle(fichasFiltradas);
				contador = 0;
			}

			f1 = new FichaInscricao();
			f2 = new FichaInscricao();

			// contador de controle
			if (pos2 >= fichasFiltradas.size()) {
				contador++;
				pos2 = 1;
			}
			
			f1 = fichasFiltradas.get(pos1);
			f2 = fichasFiltradas.get(pos2);

			// chama verificador de passadas repetidas
			if (verificaRepetidos(passadas, f1, f2)) {
				pos2++;
				contador++;
				continue;
			}

			// competidores iguais não pode
			if (f1.getCompetidor().getNome().equals(f2.getCompetidor().getNome())) {
				contador++;
				pos2++;
				continue;
			}
			
			p1 = new Passada();
			f1.setPassada(p1);
			f2.setPassada(p1);
			p1.getFichasInscricoes().add(f1);
			p1.getFichasInscricoes().add(f2);

			passadas.add(p1);

			fichasFiltradas.remove(f1);
			fichasFiltradas.remove(f2);

			pos1 = 0;
			pos2 = 1;

			// para casos em que resta um competidor
			if (fichasFiltradas.size() == 1) {
				p1 = new Passada();
				p1 = restouUmaFicha(passadas, fichasFiltradas, fichasBackup);
				passadas.add(p1);
				break;
			}
			
		} while (fichasFiltradas.size() != 0);

		return passadas;
	}

	// esse é o verificador de passadas repetidas
	private boolean verificaRepetidos(List<Passada> passadas, FichaInscricao f1, FichaInscricao f2) {

		for (Passada p : passadas) {

			// se achar competidor igual, verifica3 fica false
			if (p.getFichasInscricoes().get(0).getCompetidor().getNome().equals(f1.getCompetidor().getNome())
					&& p.getFichasInscricoes().get(1).getCompetidor().getNome().equals(f2.getCompetidor().getNome())) {
				return true;
			}

			// teste invertido se achar competidor igual, verifica3
			// fica false
			if (p.getFichasInscricoes().get(1).getCompetidor().getNome().equals(f1.getCompetidor().getNome())
					&& p.getFichasInscricoes().get(0).getCompetidor().getNome().equals(f2.getCompetidor().getNome())) {
				return true;
			}
		}
		return false;
	}

	// método para controlar 1 competidor, pega uma ficha aleatória para junção
	private Passada restouUmaFicha(List<Passada> passadas, List<FichaInscricao> fichasFiltradas,List<FichaInscricao> fichasBackup ) {
		
		int num1;
		boolean verifica1=false, verifica2=false;
		FichaInscricao f1, f2, f3;
		Passada pas1;
		List<FichaInscricao> listaInscricaoAuxiliar1;
		
		Random gerador = new Random();
					
		// enquanto as duplas não forem diferentes fica aqui
		do {
			num1 = 0;
			
			f1 = new FichaInscricao();
			f2 = new FichaInscricao();
			f3 = new FichaInscricao();
			pas1 = new Passada();
			listaInscricaoAuxiliar1 = new ArrayList<>();

			// pega a ultima ficha da lista.
			f1 = fichasFiltradas.get(0);

			// pega uma ficha qualquer da lista de referencia
			System.out.println("embaralhaPassada");
			System.out.println("num1 " + num1);
			System.out.println(fichasBackup.size());
			System.out.println("embaralhaPassada");
			if(fichasBackup.size()!=1){
				num1 = gerador.nextInt(fichasBackup.size() - 1);	
			} else {
				num1 = 0;
			}
			
			f2 = fichasBackup.get(num1);
			
			// se competidores iguais, loop
			if (f1.getCompetidor().getNome().equals(f2.getCompetidor().getNome())) {
				continue;
			}

			// para testar duplas repetidas
			verifica2 = true;

			for (Passada p : passadas) {

				if (p.getFichasInscricoes().get(0).getCompetidor().getNome()
						.equals(f1.getCompetidor().getNome())
						&& p.getFichasInscricoes().get(1).getCompetidor().getNome()
								.equals(f2.getCompetidor().getNome())) {
					verifica2 = false;
				}
				if (p.getFichasInscricoes().get(1).getCompetidor().getNome()
						.equals(f1.getCompetidor().getNome())
						&& p.getFichasInscricoes().get(0).getCompetidor().getNome()
								.equals(f2.getCompetidor().getNome())) {
					verifica2 = false;
				}
			}

			if (verifica2 == false) {
				continue;
			}

			verifica1 = true;
			f1.setPassada(pas1);
			f1.setStatusFicha(StatusFicha.EMORDEM);
			
			f3.setCampeonato(f2.getCampeonato());
			f3.setCodigoFicha(f2.getCodigoFicha());
			f3.setCompetidor(f2.getCompetidor());
			f3.setDataAlteracao(f2.getDataAlteracao());
			f3.setDataInscricao(f2.getDataInscricao());
			f3.setDivisao(f2.getDivisao());
			f3.setEtapa(f2.getEtapa());
			f3.setObs(" (Ficha Sorteada: " + f2.getId() + ")");
			f3.setPassada(pas1);
			f3.setQntFichas(f2.getQntFichas());
			f3.setStatusFicha(StatusFicha.EMORDEM);
			f3.setValorComprado(f2.getValorComprado());
			f3.setValorPago(f2.getValorPago());

			listaInscricaoAuxiliar1.add(f1);
			listaInscricaoAuxiliar1.add(f3);

			pas1.getFichasInscricoes().addAll(listaInscricaoAuxiliar1);
			
		} while (!verifica1);
		
		return pas1;
	}

}
