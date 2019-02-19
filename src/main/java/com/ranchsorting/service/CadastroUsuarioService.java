package com.ranchsorting.service;

import java.io.Serializable;

import javax.inject.Inject;
import javax.validation.ConstraintViolationException;

import com.ranchsorting.model.Usuario;
import com.ranchsorting.repository.Usuarios;
import com.ranchsorting.util.jpa.Transactional;

public class CadastroUsuarioService implements Serializable{

	private static final long serialVersionUID = 1L;

	@Inject
	private Usuarios usuarios;
	
	@Transactional
	public Usuario salvar(Usuario usuario){
		
		Usuario usuarioExistente = usuarios.porEmail(usuario.getEmail());
	
		if(usuarioExistente != null){
			throw new NegocioException("Já existe um usuário cadastro com o e-mail informado.");
		}
	
		if(!usuario.getSenha().equals(usuario.getConfirmaSenha())){
			throw new NegocioException("A confirmação de senha está diferente da senha.");
		}
		
		try{
			return usuarios.guardar(usuario);	
		} catch(ConstraintViolationException ex){
			throw new NegocioException("Ocorreu algum promblema na gravação do usuário." +
										"Entre em contato com o administrador do Sistema.");
		} catch(RuntimeException ex){
			throw new NegocioException("Ocorreu algum promblema na gravação do usuário." +
					"Entre em contato com o administrador do Sistema.");
		}
		
	}
}
