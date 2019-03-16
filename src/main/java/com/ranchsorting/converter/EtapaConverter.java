package com.ranchsorting.converter;

import javax.faces.component.UIComponent;  
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import com.ranchsorting.model.Etapa;
import com.ranchsorting.repository.Etapas;
import com.ranchsorting.util.cdi.CDIServiceLocator;

@FacesConverter(forClass = Etapa.class)
public class EtapaConverter implements Converter {

	private Etapas etapas;

	public EtapaConverter() {
		etapas = CDIServiceLocator.getBean(Etapas.class);
	}

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {

		Etapa retorno = null;

		if (value != null) {
			Long id = new Long(value);
			
			retorno = etapas.porId(id);
		}

		return retorno;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {

		if (value != null) {
			Etapa etapa = (Etapa) value;
			return etapa.getId() == null ? null : etapa.getId().toString();
		}

		return "";
	}

}
