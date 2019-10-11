package com.ranchsorting.modellazy;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import com.ranchsorting.model.Animal;
import com.ranchsorting.repository.Animais;

public class LazyAnimalDataModel extends LazyDataModel<Animal> implements Serializable{

	private static final long serialVersionUID = 1L;

	private Animais animais;
	
	public LazyAnimalDataModel(Animais animais){
		this.animais = animais;
	}
	
	@Override
	public List<Animal> load(int first, int pageSize, String sortField, SortOrder sortOrder,
			Map<String, Object> filters) {
		
		List<Animal> listaAnimais = this.animais.buscarAnimaisComPaginacao(first, pageSize);
		
		this.setRowCount(this.animais.encontrarQuantidadeTotalDeAnimais().intValue());
		
		return listaAnimais;
	}
	
}
