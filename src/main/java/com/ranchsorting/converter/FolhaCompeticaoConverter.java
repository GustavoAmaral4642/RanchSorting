package com.ranchsorting.converter;

import javax.faces.component.UIComponent; 
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import com.ranchsorting.model.FolhaCompeticao;
import com.ranchsorting.repository.FolhasCompeticoes;
import com.ranchsorting.util.cdi.CDIServiceLocator;

@FacesConverter(forClass = FolhaCompeticao.class)
public class FolhaCompeticaoConverter implements Converter {

	private FolhasCompeticoes folhasCompeticoes;

	public FolhaCompeticaoConverter() {
		folhasCompeticoes = CDIServiceLocator.getBean(FolhasCompeticoes.class);
	}

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {

		FolhaCompeticao retorno = null;

		if (value != null) {
			Long id = new Long(value);
			
			retorno = folhasCompeticoes.porId(id);
		}

		return retorno;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {

		if (value != null) {
			FolhaCompeticao folhaCompeticao = (FolhaCompeticao) value;
			return folhaCompeticao.getId() == null ? null : folhaCompeticao.getId().toString();
		}

		return "";
	}

}
