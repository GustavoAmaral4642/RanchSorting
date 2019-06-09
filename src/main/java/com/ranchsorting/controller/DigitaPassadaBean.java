package com.ranchsorting.controller;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import org.primefaces.PrimeFaces;

@Named
@ViewScoped

public class DigitaPassadaBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	public void pesquisar() {

	}

	// metodo para abrir Dialogo
	public void abrirDialogo() {

		Map<String, Object> opcoes = new HashMap<>();
		opcoes.put("modal", true);
		opcoes.put("resizable", false);
		opcoes.put("contentHeight", 470);

		PrimeFaces.current().dialog().openDynamic("/dialogos/DigitaPassada", opcoes, null);
		
	}

}
