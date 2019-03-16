package com.ranchsorting.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import com.ranchsorting.model.FichaInscricao;
import com.ranchsorting.repository.FichasInscricoes;
import com.ranchsorting.util.cdi.CDIServiceLocator;

@FacesConverter(forClass = FichaInscricao.class)
public class FichaInscricaoConverter implements Converter {

	private FichasInscricoes fichasInscricoes;

	public FichaInscricaoConverter() {
		fichasInscricoes = CDIServiceLocator.getBean(FichasInscricoes.class);
	}

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {

		FichaInscricao retorno = null;

		if (value != null) {
			Long id = new Long(value);
			
			retorno = fichasInscricoes.porId(id);
		}

		return retorno;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {

		if (value != null) {
			FichaInscricao fichaInscricao = (FichaInscricao) value;
			return fichaInscricao.getId() == null ? null : fichaInscricao.getId().toString();
		}

		return "";
	}

}
