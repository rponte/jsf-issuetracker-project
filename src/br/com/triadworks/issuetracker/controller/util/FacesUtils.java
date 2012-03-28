package br.com.triadworks.issuetracker.controller.util;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;

@ManagedBean
public class FacesUtils {
	
	@ManagedProperty("#{facesContext}")
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
