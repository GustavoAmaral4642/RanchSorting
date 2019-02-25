package com.ranchsorting.converter;

import javax.faces.component.UIComponent; 
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import com.ranchsorting.model.Divisao;
import com.ranchsorting.repository.Divisoes;
import com.ranchsorting.util.cdi.CDIServiceLocator;

@FacesConverter(forClass = Divisao.class)
public class DivisaoConverter implements Converter {

	private Divisoes divisoes;

	public DivisaoConverter() {

		divisoes = CDIServiceLocator.getBean(Divisoes.class);
	}
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {

		Divisao retorno = null;

		if (value != null) {
			Long id = new Long(value);

			retorno = divisoes.porId(id);
		}

		return retorno;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {

		if (value != null) {
			Divisao divisao = (Divisao) value;
			return divisao.getId() == null ? null : divisao.getId().toString();
		}

		return "";
	}
	
	

}
