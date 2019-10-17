package com.ranchsorting.modellazy;

import java.io.Serializable; 
import java.util.List;
import java.util.Map;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import com.ranchsorting.model.Competidor;
import com.ranchsorting.repository.Competidores;
import com.ranchsorting.repository.filter.CompetidorFilter;

public class LazyCompetidorDataModel extends LazyDataModel<Competidor> implements Serializable{

	private static final long serialVersionUID = 1L;

	private Competidores competidores;
	
	private CompetidorFilter filtro;
	
	public LazyCompetidorDataModel(Competidores competidores){
		this.competidores = competidores;
	}
	
	public LazyCompetidorDataModel(Competidores competidores, CompetidorFilter filtro){
		this.competidores = competidores;
		this.filtro = filtro;
	}
	
	@Override
	public List<Competidor> load(int first, int pageSize, String sortField, SortOrder sortOrder,
			Map<String, Object> filters) {
		
		List<Competidor> listaCompetidores = this.competidores.buscarCompetidoresComPaginacao(first, pageSize, filtro);
		
		this.setRowCount(this.competidores.encontrarQuantidadeTotalDeCompetidores().intValue());
		
		return listaCompetidores;
	}
	
}
