package com.ranchsorting.service;

public class NegocioException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	//passará a informação para a classe pai
	public NegocioException(String msg) {
		super(msg);
	}
	
}