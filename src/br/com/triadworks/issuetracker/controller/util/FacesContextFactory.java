package br.com.triadworks.issuetracker.controller.util;

import javax.faces.context.FacesContext;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

@Component
public class FacesContextFactory {

	/**
	 * Obtem instancia atual do <code>facesContext</code> e permite que o mesmo
	 * seja injetado em beans com escopo maior que request.
	 */
	@Bean
	@Scope(value="request", proxyMode=ScopedProxyMode.TARGET_CLASS)
	public FacesContext getInstance() {
		return FacesContext.getCurrentInstance();
	}
	
}
