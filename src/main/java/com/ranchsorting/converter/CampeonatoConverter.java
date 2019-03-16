package com.ranchsorting.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import com.ranchsorting.model.Campeonato;
import com.ranchsorting.repository.Campeonatos;
import com.ranchsorting.util.cdi.CDIServiceLocator;

@FacesConverter(forClass = Campeonato.class)
public class CampeonatoConverter implements Converter {

	private Campeonatos campeonatos;

	public CampeonatoConverter() {
		campeonatos = CDIServiceLocator.getBean(Campeonatos.class);
	}

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {

		Campeonato retorno = null;

		if (value != null) {
			Long id = new Long(value);
			
			retorno = campeonatos.porId(id);
		}

		return retorno;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {

		if (value != null) {
			Campeonato campeonato = (Campeonato) value;
			return campeonato.getId() == null ? null : campeonato.getId().toString();
		}

		return "";
	}

}
