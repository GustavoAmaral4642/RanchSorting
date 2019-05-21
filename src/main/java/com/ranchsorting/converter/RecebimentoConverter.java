package com.ranchsorting.converter;

import javax.faces.component.UIComponent; 
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import com.ranchsorting.model.Recebimento;
import com.ranchsorting.repository.Recebimentos;
import com.ranchsorting.util.cdi.CDIServiceLocator;

@FacesConverter(forClass = Recebimento.class)
public class RecebimentoConverter implements Converter {

	private Recebimentos recebimentos;

	public RecebimentoConverter() {
		recebimentos = CDIServiceLocator.getBean(Recebimentos.class);
	}

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {

		Recebimento retorno = null;

		if (value != null) {
			Long id = new Long(value);
			
			retorno = recebimentos.porId(id);
		}

		return retorno;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {

		if (value != null) {
			Recebimento recebimento = (Recebimento) value;
			return recebimento.getId() == null ? null : recebimento.getId().toString();
		}

		return "";
	}

}
