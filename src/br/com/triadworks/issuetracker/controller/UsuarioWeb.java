package br.com.triadworks.issuetracker.controller;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.com.triadworks.issuetracker.model.Usuario;

@Controller
@Scope("session")
public class UsuarioWeb {

	private Usuario usuario;
	
	public void loga(Usuario usuario) {
		this.usuario = usuario;
	}

	public void logout() {
		this.usuario = null;
	}
	
	public boolean isLogado() {
		return this.usuario != null;
	}

	public Usuario getUsuario() {
		return usuario;
	}
	
}
