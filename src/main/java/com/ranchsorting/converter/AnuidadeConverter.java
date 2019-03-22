package com.ranchsorting.converter;

import javax.faces.component.UIComponent; 
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import com.ranchsorting.model.Anuidade;
import com.ranchsorting.repository.Anuidades;
import com.ranchsorting.util.cdi.CDIServiceLocator;

@FacesConverter(forClass = Anuidade.class)
public class AnuidadeConverter implements Converter {

	private Anuidades anuidades;

	public AnuidadeConverter() {

		anuidades = CDIServiceLocator.getBean(Anuidades.class);
	}
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {

		Anuidade retorno = null;

		if (value != null) {
			Long id = new Long(value);

			retorno = anuidades.porId(id);
		}

		return retorno;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {

		if (value != null) {
			Anuidade anuidade = (Anuidade) value;
			return anuidade.getId() == null ? null : anuidade.getId().toString();
		}

		return "";
	}
	
	

}
