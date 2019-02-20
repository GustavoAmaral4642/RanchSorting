package com.ranchsorting.controller;

import java.io.Serializable;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.ranchsorting.model.Usuario;
import com.ranchsorting.service.CadastroUsuarioService;
import com.ranchsorting.util.jsf.FacesUtil;

@Named
@ViewScoped
public class CadastroUsuarioBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private CadastroUsuarioService cadastroUsuarioService;

	private Usuario usuario;

	public CadastroUsuarioBean() {
		limpar();
	}

	public void inicializar() {

	}

	public void limpar() {
		usuario = new Usuario();
	}

	public void salvar() {
		
		this.usuario = cadastroUsuarioService.salvar(this.usuario);
		
		limpar();

		FacesUtil.addInfoMessage("Usuário salvo com sucesso!");
	}

	public Usuario getUsuario() {

		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public boolean isEditando() {
		return this.usuario.getId() != null;
	}
}
