package com.ranchsorting.converter;

import javax.faces.component.UIComponent;  
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import com.ranchsorting.model.Titulo;
import com.ranchsorting.repository.Titulos;
import com.ranchsorting.util.cdi.CDIServiceLocator;

@FacesConverter(forClass = Titulo.class)
public class TituloConverter implements Converter {

	private Titulos titulos;

	public TituloConverter() {
		titulos = CDIServiceLocator.getBean(Titulos.class);
	}

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {

		Titulo retorno = null;

		if (value != null) {
			Long id = new Long(value);
			
			retorno = titulos.porId(id);
		}

		return retorno;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {

		if (value != null) {
			Titulo titulo = (Titulo) value;
			return titulo.getId() == null ? null : titulo.getId().toString();
		}

		return "";
	}

}
