package com.ranchsorting.converter;

import javax.faces.component.UIComponent; 
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import com.ranchsorting.model.Animal;
import com.ranchsorting.repository.Animais;
import com.ranchsorting.util.cdi.CDIServiceLocator;

@FacesConverter(forClass = Animal.class)
public class AnimalConverter implements Converter {

	private Animais animais;

	public AnimalConverter() {

		animais = CDIServiceLocator.getBean(Animais.class);
	}
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {

		Animal retorno = null;

		if (value != null) {
			Long id = new Long(value);

			retorno = animais.porId(id);
		}

		return retorno;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {

		if (value != null) {
			Animal animal = (Animal) value;
			return animal.getId() == null ? null : animal.getId().toString();
		}

		return "";
	}
	
	

}
