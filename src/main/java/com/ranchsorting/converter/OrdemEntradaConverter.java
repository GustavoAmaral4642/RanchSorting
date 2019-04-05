package com.ranchsorting.converter;

import javax.faces.component.UIComponent; 
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import com.ranchsorting.model.OrdemEntrada;
import com.ranchsorting.repository.OrdensEntradas;
import com.ranchsorting.util.cdi.CDIServiceLocator;

@FacesConverter(forClass = OrdemEntrada.class)
public class OrdemEntradaConverter implements Converter {

	private OrdensEntradas ordens;

	public OrdemEntradaConverter() {
		ordens = CDIServiceLocator.getBean(OrdensEntradas.class);
	}

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {

		OrdemEntrada retorno = null;

		if (value != null) {
			Long id = new Long(value);
			
			retorno = ordens.porId(id);
		}

		return retorno;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {

		if (value != null) {
			OrdemEntrada ordem = (OrdemEntrada) value;
			return ordem.getId() == null ? null : ordem.getId().toString();
		}

		return "";
	}

}
