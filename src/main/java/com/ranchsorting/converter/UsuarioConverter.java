package com.ranchsorting.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import com.ranchsorting.model.Usuario;
import com.ranchsorting.repository.Usuarios;
import com.ranchsorting.util.cdi.CDIServiceLocator;

@FacesConverter(forClass = Usuario.class)
public class UsuarioConverter implements Converter {

	private Usuarios usuarios;

	public UsuarioConverter() {

		usuarios = CDIServiceLocator.getBean(Usuarios.class);
	}
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {

		Usuario retorno = null;

		if (value != null) {
			Long id = new Long(value);

			retorno = usuarios.porId(id);
		}

		return retorno;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {

		if (value != null) {
			return ((Usuario) value).getId().toString();
		}

		return "";
	}

}
