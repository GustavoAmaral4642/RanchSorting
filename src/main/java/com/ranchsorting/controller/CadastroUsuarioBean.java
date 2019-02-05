package com.ranchsorting.controller;

import java.io.Serializable; 

import javax.faces.bean.ViewScoped;
import javax.inject.Named;

import com.ranchsorting.model.Usuario;
import com.ranchsorting.util.jsf.FacesUtil;

@Named
@ViewScoped
public class CadastroUsuarioBean implements Serializable{

	private static final long serialVersionUID = 1L;
		
	private Usuario usuario;
	
	public CadastroUsuarioBean(){
		usuario = new Usuario();
	}
	
	public void inicializar() {
		System.out.println("Inicializando..."); // está chamando este método duas vezes...
	}
	
	public void salvar(){
		FacesUtil.addErrorMessage("Está aqui dentro");
	}

	public Usuario getUsuario() {
		
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

}
