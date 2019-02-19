package com.ranchsorting.controller;

import java.io.Serializable;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.ranchsorting.model.Usuario;
import com.ranchsorting.service.CadastroUsuarioService;
import com.ranchsorting.util.jsf.FacesUtil;

@Named
@RequestScoped
public class CadastroUsuarioBean implements Serializable{

	private static final long serialVersionUID = 1L;
		
	@Inject
	private CadastroUsuarioService cadastroUsuarioService;
	
	private Usuario usuario;
	
	public CadastroUsuarioBean(){
		limpar();		
	}
	
	public void inicializar() {
		System.out.println("Inicializando..."); // está chamando este método duas vezes...
	}
	
	public void limpar(){
		usuario = new Usuario();
	}
	
	public void salvar(){
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

}
