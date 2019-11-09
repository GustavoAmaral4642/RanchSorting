package com.ranchsorting.modellazy;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import com.ranchsorting.model.OrdemEntrada;
import com.ranchsorting.repository.OrdensEntradas;
import com.ranchsorting.repository.filter.OrdemEntradaFilter;

public class LazyOrdemEntradaDataModel extends LazyDataModel<OrdemEntrada> implements Serializable{

	private static final long serialVersionUID = 1L;

	private OrdensEntradas ordens;
	
	private OrdemEntradaFilter filtro;
	private String ordenar;
	private String tipoOrdenacao;
	
	public LazyOrdemEntradaDataModel(OrdensEntradas ordens){
		this.ordens = ordens;
	}
	
	public LazyOrdemEntradaDataModel(OrdensEntradas ordens, OrdemEntradaFilter filtro){
		this.ordens = ordens;
		this.filtro = filtro;
	}
	
	public LazyOrdemEntradaDataModel(OrdensEntradas ordens, OrdemEntradaFilter filtro, String ordenar, String tipoOrdenacao){
		this.ordens = ordens;
		this.filtro = filtro;
		this.ordenar = ordenar;
		this.tipoOrdenacao = tipoOrdenacao;
	}
	
	@Override
	public List<OrdemEntrada> load(int first, int pageSize, String sortField, SortOrder sortOrder,
			Map<String, Object> filters) {
		
		List<OrdemEntrada> listaOrdens = this.ordens.buscarOrdensEntradasComPaginacao(first, pageSize, filtro, ordenar, tipoOrdenacao);
		
		this.setRowCount(this.ordens.encontrarQuantidadeTotalDeOrdensEntradas().intValue());
		
		return listaOrdens;
	}
	
	
}
