package br.com.triadworks.issuetracker.controller.util;

import javax.faces.context.FacesContext;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

@Component
public class FacesContextFactory {

	@Bean
	@Scope(value="request", proxyMode=ScopedProxyMode.TARGET_CLASS)
	public FacesContext getInstance() {
		return FacesContext.getCurrentInstance();
	}
	
}
