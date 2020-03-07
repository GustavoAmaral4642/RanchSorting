package com.ranchsorting.service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import com.ranchsorting.model.FichaInscricao;
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
