package com.ranchsorting.util.jsf;

import java.io.IOException;
import java.util.Iterator;

import javax.faces.FacesException;
import javax.faces.application.ViewExpiredException;
import javax.faces.context.ExceptionHandler;
import javax.faces.context.ExceptionHandlerWrapper;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ExceptionQueuedEvent;
import javax.faces.event.ExceptionQueuedEventContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.ranchsorting.service.NegocioException;

public class JsfExceptionHandler extends ExceptionHandlerWrapper {

	//criar esta variavel estatica do tipo log. 
	//passo o nome da classe para getLog para qual estamos registrando os logs
	private static Log log = LogFactory.getLog(JsfExceptionHandler.class);
	
	// O JSF já tem um tratador de exceção. Vamos criar uma camada acima deste.
	// colocar o ExceptionHandler que
	private ExceptionHandler wrapped;

	// Criando o construtor que receberá o ExceptionHandler
	public JsfExceptionHandler(ExceptionHandler wrapped) {
		this.wrapped = wrapped; // jogando no atributo de instancia criado.
	}

	// retornando a variável de instancia.
	@Override
	public ExceptionHandler getWrapped() {
		return this.wrapped;
	}

	// método que será chamado quando existir uma exceção.
	// este é responsável por tratar as exceções chamadas.
	@Override
	public void handle() throws FacesException {

		// é uma inteface para iteração de elementos. É visto em JAVA.
		// todas as exceções que chegarem, é itereda no events
		Iterator<ExceptionQueuedEvent> events = getUnhandledExceptionQueuedEvents().iterator();

		// enquanto tiver algum evento dentro de events.
		while (events.hasNext()) {

			// pega eventos um a um.
			ExceptionQueuedEvent event = events.next();
			// pegando a origem da exceção contextualizado
			ExceptionQueuedEventContext context = (ExceptionQueuedEventContext) event.getSource();

			// getException retorna a exceção lançada. Throwable é classe pai de
			// todas as exceções.
			Throwable exception = context.getException();
			NegocioException negocioException = getNegocioException(exception);

			// o que faremos com a exceção, neste caso, enviaremos para página
			// inicial.
			// poderia enviar um e-mail.
			boolean handle = false; // criar esta variável antes do try
			
			try {
				if (exception instanceof ViewExpiredException) {
					System.out.println("taqui 5");
					handle = true;
					redirect("/");
				} else if (negocioException != null) {
					
					handle = true;
					FacesUtil.addErrorMessage(negocioException.getMessage());
				} else {
					
					handle = true;
					// usando comando para gravar log. 
					// primeiro parâmetro irá gravar a mensagem, segundo parâmetro é a causa da exceção.
					// importante colocar a causa, para imprimir toda a pilha do erro de exceção.
					log.error("Erro de sistema: " + exception.getMessage(), exception); 
		
					redirect("/error.xhtml");
				}

			} finally {
				if (handle) {
					events.remove();
				}
			}
		}

		getWrapped().handle(); // Estamos dizendo para classe que estamos
								// encapsulando, terminei meu trabalho, agora é
								// com você.
	}

	private NegocioException getNegocioException(Throwable exception){
		if(exception instanceof NegocioException){
			return (NegocioException) exception; // faço um cast retorno a própria
		} else if(exception.getCause() != null){ 
			return getNegocioException(exception.getCause());
		}
		
		return null;
	}

	private void redirect(String page) {

		try {
			FacesContext facesContext = FacesContext.getCurrentInstance();
			ExternalContext externalContext = facesContext.getExternalContext();
			String contextPath = externalContext.getRequestContextPath(); // contextPath
																			// é
																			// o
																			// nome
																			// do
																			// contexto.

			externalContext.redirect(contextPath + page); // recebe a informação
															// /PedidoVenda/ -
															// '/PedidoVenda' é
															// o context e '/' é
															// o page
			facesContext.responseComplete();
		} catch (IOException e) {
			throw new FacesException(e);
		}
	}

}
