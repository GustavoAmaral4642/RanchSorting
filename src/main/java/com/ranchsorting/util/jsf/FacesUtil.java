package com.ranchsorting.util.jsf;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

public class FacesUtil {

	// crio este método, verificando se é postback.
	public static boolean isPostback() {
		return FacesContext.getCurrentInstance().isPostback();
	}

	// crio este método, verificando se não é postback
	public static boolean isNotPostback() {
		return !isPostback();
	}

	// método estático para adicionar mensagem de erro para apresentação das
	// exceções.
	public static void addErrorMessage(String message) {
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_ERROR, message, message));
	}

	// adicionando mensagem INFO. é um método utilitário
	public static void addInfoMessage(String message) {
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, message, message));
	}

}
