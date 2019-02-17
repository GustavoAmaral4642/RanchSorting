package com.ranchsorting.controller;

import java.io.Serializable;

import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.ranchsorting.model.Usuario;
import com.ranchsorting.service.CadastroUsuarioService;
import com.ranchsorting.util.jsf.FacesUtil;

@Named
@ViewScoped
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
		System.out.println("chamando construtor");
	}
	
	public void salvar(){
		
		System.out.println("nome " + this.usuario.getNome());
		System.out.println(this.usuario.getEmail());
		System.out.println(this.usuario.getSenha());
		
		
		//this.usuario = cadastroUsuarioService.salvar(this.usuario);
		
		//limpar();
		
		FacesUtil.addInfoMessage("Usuario salvo com sucesso!");
	}

	public Usuario getUsuario() {
		
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

}
