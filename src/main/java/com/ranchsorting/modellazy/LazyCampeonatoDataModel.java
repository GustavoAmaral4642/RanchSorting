package com.ranchsorting.modellazy;

import java.io.Serializable;  
import java.util.List;
import java.util.Map;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import com.ranchsorting.model.Campeonato;
import com.ranchsorting.repository.Campeonatos;
import com.ranchsorting.repository.filter.CampeonatoFilter;

public class LazyCampeonatoDataModel extends LazyDataModel<Campeonato> implements Serializable{

	private static final long serialVersionUID = 1L;

	private Campeonatos campeonatos;
	
	private CampeonatoFilter filtro;
	private String ordenar;
	private String tipoOrdenacao;
	
	public LazyCampeonatoDataModel(Campeonatos campeonatos){
		this.campeonatos = campeonatos;
	}
	
	public LazyCampeonatoDataModel(Campeonatos campeonatos, CampeonatoFilter filtro){
		this.campeonatos = campeonatos;
		this.filtro = filtro;
	}
	
	public LazyCampeonatoDataModel(Campeonatos campeonatos, CampeonatoFilter filtro, String ordenar, String tipoOrdenacao){
		this.campeonatos = campeonatos;
		this.filtro = filtro;
		this.ordenar = ordenar;
		this.tipoOrdenacao = tipoOrdenacao;
	}
	
	@Override
	public List<Campeonato> load(int first, int pageSize, String sortField, SortOrder sortOrder,
			Map<String, Object> filters) {
		
		List<Campeonato> listaCampeonatos = this.campeonatos.buscarCampeonatosComPaginacao(first, pageSize, filtro, ordenar, tipoOrdenacao);
		
		this.setRowCount(this.campeonatos.encontrarQuantidadeTotalDeCampeonatos().intValue());
		
		return listaCampeonatos;
	}
	
}
