package br.com.triadworks.issuetracker.controller.util;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.component.EditableValueHolder;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;

@ManagedBean
@RequestScoped
public class FacesUtils {

	@ManagedProperty("#{facesContext}")
	private FacesContext facesContext;

	public void adicionaMensagemDeErro(String msg) {
		facesContext.addMessage(null, 
			new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, msg));
	}

	public void adicionaMensagemDeInformacao(String msg) {
		facesContext.addMessage(null, 
			new FacesMessage(FacesMessage.SEVERITY_INFO, msg, msg));
	}

	/**
	 * Limpa os dados dos componentes de edição e de seus filhos,
	 * recursivamente. Checa se o componente é instância de EditableValueHolder
	 * e 'reseta' suas propriedades.
	 * <p>
	 * Quando este método, por algum motivo, não funcionar, parta para ignorância
	 * e limpe o componente assim:
	 * <p><blockquote><pre>
	 * 	component.getChildren().clear()
	 * </pre></blockquote>
	 * :-)
	 */
	public void cleanSubmittedValues(UIComponent component) {
		if (component instanceof EditableValueHolder) {
			EditableValueHolder evh = (EditableValueHolder) component;
			evh.setSubmittedValue(null);
			evh.setValue(null);
			evh.setLocalValueSet(false);
			evh.setValid(true);
		}
		if(component.getChildCount()>0){
			for (UIComponent child : component.getChildren()) {
				cleanSubmittedValues(child);
			}
		}
	}
	
	public void cleanSubmittedValues(String componentName) {
		UIComponent component = facesContext.getViewRoot().findComponent(componentName);
		if (component != null)
			cleanSubmittedValues(component);
	}
	
	public FacesContext getFacesContext() {
		return facesContext;
	}
	public void setFacesContext(FacesContext facesContext) {
		this.facesContext = facesContext;
	}

	public void addFlashAttribute(String key, Object value) {
		facesContext.getExternalContext().getFlash().put(key, value);
	}
	
	public void addViewScopeAttribute(String key, Object value) {
		facesContext.getViewRoot().getViewMap().put(key, value);
	}
	
	@SuppressWarnings("unchecked")
	public <T> T getViewScopeAttribute(String key) {
		return (T) facesContext.getViewRoot().getViewMap().get(key);
	}
	
}
