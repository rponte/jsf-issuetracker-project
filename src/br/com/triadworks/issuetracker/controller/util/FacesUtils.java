package br.com.triadworks.issuetracker.controller.util;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

public class FacesUtils {
	
	private FacesContext facesContext;

	public void adicionaMensagemDeErro(String mensagem) {
		FacesMessage facesMessage 
			= new FacesMessage(FacesMessage.SEVERITY_ERROR, mensagem, mensagem);
		facesContext.addMessage(null, facesMessage);
	}

	public void adicionaMensagemDeInformacao(String mensagem) {
		FacesMessage facesMessage 
			= new FacesMessage(FacesMessage.SEVERITY_INFO, mensagem, mensagem);
		facesContext.addMessage(null, facesMessage);
	}
	
	public void setFacesContext(FacesContext facesContext) {
		this.facesContext = facesContext;
	}
	
}
