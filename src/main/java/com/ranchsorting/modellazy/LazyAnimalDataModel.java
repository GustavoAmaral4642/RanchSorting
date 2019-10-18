package com.ranchsorting.modellazy;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import com.ranchsorting.model.Animal;
import com.ranchsorting.repository.Animais;
import com.ranchsorting.repository.filter.AnimalFilter;

public class LazyAnimalDataModel extends LazyDataModel<Animal> implements Serializable{

	private static final long serialVersionUID = 1L;

	private Animais animais;
	
	private AnimalFilter filtro;
	private String ordenar;
	private String tipoOrdenacao;
	
	public LazyAnimalDataModel(Animais animais){
		this.animais = animais;
	}
	
	public LazyAnimalDataModel(Animais animais, AnimalFilter filtro){
		this.animais = animais;
		this.filtro = filtro;
	}
	
	public LazyAnimalDataModel(Animais animais, AnimalFilter filtro, String ordenar, String tipoOrdenacao){
		this.animais = animais;
		this.filtro = filtro;
		this.ordenar = ordenar;
		this.tipoOrdenacao= tipoOrdenacao;
	}
	
	@Override
	public List<Animal> load(int first, int pageSize, String sortField, SortOrder sortOrder,
			Map<String, Object> filters) {
		
		List<Animal> listaAnimais = this.animais.buscarAnimaisComPaginacao(first, pageSize, filtro, ordenar, tipoOrdenacao);
		
		this.setRowCount(this.animais.encontrarQuantidadeTotalDeAnimais().intValue());
		
		return listaAnimais;
	}
	
}
