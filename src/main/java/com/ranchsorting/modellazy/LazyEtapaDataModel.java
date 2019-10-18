package com.ranchsorting.modellazy;

import java.io.Serializable;  
import java.util.List;
import java.util.Map;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import com.ranchsorting.model.Etapa;
import com.ranchsorting.repository.Etapas;
import com.ranchsorting.repository.filter.EtapaFilter;

public class LazyEtapaDataModel extends LazyDataModel<Etapa> implements Serializable{

	private static final long serialVersionUID = 1L;

	private Etapas etapas;
	
	private EtapaFilter filtro;
	private String ordenar;
	private String tipoOrdenacao;
	
	public LazyEtapaDataModel(Etapas etapas){
		this.etapas = etapas;
	}
	
	public LazyEtapaDataModel(Etapas etapas, EtapaFilter filtro){
		this.etapas = etapas;
		this.filtro = filtro;
	}
	
	public LazyEtapaDataModel(Etapas etapas, EtapaFilter filtro, String ordenar, String tipoOrdenacao){
		this.etapas = etapas;
		this.filtro = filtro;
		this.ordenar = ordenar;
		this.tipoOrdenacao = tipoOrdenacao;
	}
	
	@Override
	public List<Etapa> load(int first, int pageSize, String sortField, SortOrder sortOrder,
			Map<String, Object> filters) {
		
		List<Etapa> listaCampeonatos = this.etapas.buscarEtapasComPaginacao(first, pageSize, filtro, ordenar, tipoOrdenacao);
		
		this.setRowCount(this.etapas.encontrarQuantidadeTotalDeEtapas().intValue());
		
		return listaCampeonatos;
	}
	
}
