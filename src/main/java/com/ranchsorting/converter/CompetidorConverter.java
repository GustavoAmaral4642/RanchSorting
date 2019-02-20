package com.ranchsorting.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import com.ranchsorting.model.Competidor;
import com.ranchsorting.repository.Competidores;
import com.ranchsorting.util.cdi.CDIServiceLocator;

@FacesConverter(forClass = Competidor.class)
public class CompetidorConverter implements Converter {

	private Competidores competidores;

	public CompetidorConverter() {
		competidores = CDIServiceLocator.getBean(Competidores.class);
	}

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {

		Competidor retorno = null;

		if (value != null) {
			Long id = new Long(value);
			
			retorno = competidores.porId(id);
		}

		return retorno;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {

		if (value != null) {
			Competidor competidor = (Competidor) value;
			return competidor.getId() == null ? null : competidor.getId().toString();
		}

		return "";
	}

}
