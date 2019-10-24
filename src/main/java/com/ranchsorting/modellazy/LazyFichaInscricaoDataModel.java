package com.ranchsorting.modellazy;

import java.io.Serializable; 
import java.util.List;
import java.util.Map;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import com.ranchsorting.model.FichaInscricao;
import com.ranchsorting.repository.FichasInscricoes;
import com.ranchsorting.repository.filter.FichaInscricaoFilter;

public class LazyFichaInscricaoDataModel extends LazyDataModel<FichaInscricao> implements Serializable{

	private static final long serialVersionUID = 1L;

	private FichasInscricoes fichas;
	
	private FichaInscricaoFilter filtro;
	private String ordenar;
	private String tipoOrdenacao;
	
	public LazyFichaInscricaoDataModel(FichasInscricoes fichas){
		this.fichas = fichas;
	}
	
	public LazyFichaInscricaoDataModel(FichasInscricoes fichas, FichaInscricaoFilter filtro){
		this.fichas = fichas;
		this.filtro = filtro;
	}
	
	public LazyFichaInscricaoDataModel(FichasInscricoes fichas, FichaInscricaoFilter filtro, String ordenar, String tipoOrdenacao){
		this.fichas = fichas;
		this.filtro = filtro;
		this.ordenar = ordenar;
		this.tipoOrdenacao = tipoOrdenacao;
	}
	
	@Override
	public List<FichaInscricao> load(int first, int pageSize, String sortField, SortOrder sortOrder,
			Map<String, Object> filters) {
		
		List<FichaInscricao> listaFichas = this.fichas.buscarFichasInscricoesComPaginacao(first, pageSize, filtro, ordenar, tipoOrdenacao);
		
		this.setRowCount(this.fichas.encontrarQuantidadeTotalDeFichasInscricoes().intValue());
		
		return listaFichas;
	}
	
	
}
