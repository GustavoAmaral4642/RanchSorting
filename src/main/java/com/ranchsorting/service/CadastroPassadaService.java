package com.ranchsorting.service;

import java.io.Serializable;

import javax.inject.Inject;

import com.ranchsorting.model.Passada;
import com.ranchsorting.repository.Passadas;
import com.ranchsorting.util.jpa.Transactional;


public class CadastroPassadaService implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private Passadas passadas;
	
	@Transactional
	public Passada salvar(Passada passada) {
		return passadas.guardar(passada);
	}

}
