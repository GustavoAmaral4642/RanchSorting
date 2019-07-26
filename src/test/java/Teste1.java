//
//import java.util.List;
//import java.util.Random;
//
//import com.ranchsorting.model.FichaInscricao;
//import com.ranchsorting.model.Passada;
//
//import java.util.ArrayList;
//
//public class Teste1{
//	public static void main(String[] args){
//		/*
//		List<String> ab = new ArrayList<>();
//		String inf = new String();
//		String inf2 = new String();
//		boolean valida=false;
//		
//		int a[][] = new int[5][36];
//		int contador=1;
//		for(int i=1; i<=36; i++){
//			for(int k=1; k<=5; k++){
//				a[k-1][i-1] = contador;
//			}
//			contador++;
//		}
//		
//		for(int i=1; i<=5; i++){
//			for(int k=1; k<=36; k++){
//				System.out.print("[" + a[i-1][k-1] + "]");
//			}
//			System.out.println();
//		}
//*/
//		
//		List<String> ab = new ArrayList<>();
//		String inf = new String();
//		String inf2 = new String();
//		boolean valida=false;
//		
//		FichaInscricao a[][] = new FichaInscricao[5][36];
//		int contador=0;
//		int qntComp =(fichasFiltradas.size()/5);
//		
//		System.out.println(qntComp);
//		
//		for(FichaInscricao f : fichasFiltradas){
//			System.out.println(f.getCodigoFicha());
//		}
//		
//		for(int i=1; i<=qntComp; i++){
//			for(int k=1; k<=5; k++){
//				a[k-1][i-1] = fichasFiltradas.get(contador);
//				contador++;
//			}
//			
//		}
//		
//		for(int i=1; i<=5; i++){
//			for(int k=1; k<=qntComp; k++){
//				System.out.print("[" + a[i-1][k-1].getCodigoFicha() + "]");
//			}
//			System.out.println();
//		}
//		int i=0, k=0;
//		int j=0, m=1;
//		int contador1 = 0;
//		
//		do{			
//			inf = new String();
//			inf = String.valueOf(a[i][k]);
//			inf2 = new String();
//			inf2 = String.valueOf(a[j][m]);
//			
//			ab.add(inf+"-"+inf2);
//			
//			i++;
//			
//			if(i==5){
//				i=j+1;
//				k++;
//			}
//			
//			if(k==36){
//				valida=true;
//			}
//			for(String c : ab){
//				System.out.println(c);
//			}
//			
//		} while(!valida);
//		
//		/*
//		ab.add(inf+"-"+inf2);
//		
//		inf = new String();
//		inf2 = new String();
//		
//		inf = String.valueOf(a[1][0]);
//		inf2 = String.valueOf(a[0][2]);
//		
//		
//		
//		inf = new String();
//		inf2 = new String();
//		
//		inf = String.valueOf(a[2][0]);
//		inf2 = String.valueOf(a[0][3]);
//		
//		ab.add(inf+"-"+inf2);
//		
//		*/
//		for(String c : ab){
//			System.out.println(c);
//		}
//		
//		System.out.println();
//	}
//}
//
//
//List<FichaInscricao> auxFichasFiltradas = new ArrayList<>();
//FichaInscricao fic1;
//FichaInscricao fic2;
//boolean valida = false;
//boolean valida2 = false;
//
//int contador = 0;
//int qntComp = (fichasFiltradas.size() / 5);
//
//FichaInscricao a[][] = new FichaInscricao[5][qntComp];
//
//System.out.println(qntComp);
//
//for (FichaInscricao f : fichasFiltradas) {
//	System.out.println(f.getCodigoFicha());
//}
//
//for (int i = 1; i <= qntComp; i++) {
//	for (int k = 1; k <= 5; k++) {
//		a[k - 1][i - 1] = fichasFiltradas.get(contador);
//		contador++;
//	}
//
//}
//
//for (int i = 1; i <= 5; i++) {
//	for (int k = 1; k <= qntComp; k++) {
//		System.out.print("[" + a[i - 1][k - 1].getCodigoFicha() + "]");
//	}
//	System.out.println();
//}
//
//int i = 0, k = 0;
//int j = 0, m = 1;
//Passada pas1;
//
//do {
//	System.out.println("i: " + i);
//	System.out.println("k: " + k);
//	System.out.println("j: " + j);
//	System.out.println("m: " + m);
//
//	if (m != qntComp) {
//
//		fic1 = new FichaInscricao();
//		fic2 = new FichaInscricao();
//		auxFichasFiltradas = new ArrayList<>();
//
//		fic1 = a[i][k];
//		fic2 = a[j][m];
//
//		auxFichasFiltradas.add(fic1);
//		auxFichasFiltradas.add(fic2);
//
//		pas1 = new Passada();
//		pas1.getFichasInscricoes().addAll(auxFichasFiltradas);
//		passadasCompetidores.add(pas1);
//
//		i++;
//		m++;
//	} 
//
//	if (i == 5) {
//		i = j + 1;
//		k++;
//	}
//
//	if (m == qntComp) {
//		m = k + 1;
//		j++;
//	}
//
//	if (m == qntComp) {
//		valida = true;
//	}
//	
//    if (k < m) {
//		System.out.println("entrouaqui");
//		do {
//			fic1 = new FichaInscricao();
//			fic2 = new FichaInscricao();
//			auxFichasFiltradas = new ArrayList<>();
//
//			Random gerador = new Random();
//
//			j = gerador.nextInt(4);
//			m = gerador.nextInt(qntComp-1);
//
//			fic1 = a[i][k];
//			fic2 = a[j][m];
//
//			if (!fic1.getCompetidor().getNome().equals(fic2.getCompetidor().getNome())) {
//
//				auxFichasFiltradas.add(fic1);
//				auxFichasFiltradas.add(fic2);
//
//				pas1 = new Passada();
//				pas1.getFichasInscricoes().addAll(auxFichasFiltradas);
//				passadasCompetidores.add(pas1);
//				
//				valida2=true;
//			}
//		} while (!valida2);
//
//	}
//
//} while (!valida);
//
//int c = 1;
//for (Passada p : passadasCompetidores) {
//	System.out.print("Numero passada: " + c);
//	System.out.print(" - competidores: ");
//	for (FichaInscricao f1 : p.getFichasInscricoes()) {
//		System.out.print(" - " + f1.getCompetidor().getNome() + " - ficha: " + f1.getId());
//	}
//	System.out.println();
//	c++;
//}