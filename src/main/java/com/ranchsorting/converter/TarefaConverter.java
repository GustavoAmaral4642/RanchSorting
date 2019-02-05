package com.ranchsorting.converter;

import javax.faces.component.UIComponent; 
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import com.ranchsorting.model.Tarefa;
import com.ranchsorting.repository.Tarefas;
import com.ranchsorting.util.cdi.CDIServiceLocator;

@FacesConverter(forClass = Tarefa.class)
public class TarefaConverter implements Converter {

	private Tarefas tarefas;

	public TarefaConverter() {

		tarefas = CDIServiceLocator.getBean(Tarefas.class);
	}
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {

		Tarefa retorno = null;

		if (value != null) {
			Long id = new Long(value);

			retorno = tarefas.porId(id);
		}

		return retorno;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {

		if (value != null) {
			return ((Tarefa) value).getId().toString();
		}

		return "";
	}

}
