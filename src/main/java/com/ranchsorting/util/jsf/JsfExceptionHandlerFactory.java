package com.ranchsorting.util.jsf;

import javax.faces.context.ExceptionHandler;
import javax.faces.context.ExceptionHandlerFactory;

public class JsfExceptionHandlerFactory extends ExceptionHandlerFactory {

	private ExceptionHandlerFactory parent;

	// construtor que recebe a fabrica padrão.
	public JsfExceptionHandlerFactory(ExceptionHandlerFactory parent) {
		this.parent = parent;
	}

	// Instanciando a classe que criamos e passando para classe que criamos.
	@Override
	public ExceptionHandler getExceptionHandler() {
		return new JsfExceptionHandler(parent.getExceptionHandler());
	}
}