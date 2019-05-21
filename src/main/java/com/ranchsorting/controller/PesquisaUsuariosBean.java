package com.ranchsorting.controller;

import java.io.Serializable;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.ranchsorting.model.Usuario;
import com.ranchsorting.repository.Usuarios;
import com.ranchsorting.repository.filter.UsuarioFilter;
import com.ranchsorting.util.jsf.FacesUtil;

@Named
@ViewScoped
public class PesquisaUsuariosBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private Usuarios usuarios;

	private UsuarioFilter filtro;

	private List<Usuario> usuariosFiltrados;

	public PesquisaUsuariosBean() {
		filtro = new UsuarioFilter();
	}

	public void pesquisar() {
		usuariosFiltrados = usuarios.filtrados(filtro);
	}
	
	public void excluir(Usuario usuarioSelecionado) {
		
		usuarios.remover(usuarioSelecionado);

		usuariosFiltrados.remove(usuarioSelecionado);

		FacesUtil.addInfoMessage("Usuário " + usuarioSelecionado.getNome() + " excluído com sucesso.");
	}

	public UsuarioFilter getFiltro() {
		return filtro;
	}

	public void setFiltro(UsuarioFilter filtro) {
		this.filtro = filtro;
	}

	public List<Usuario> getUsuariosFiltrados() {
		return usuariosFiltrados;
	}

}
