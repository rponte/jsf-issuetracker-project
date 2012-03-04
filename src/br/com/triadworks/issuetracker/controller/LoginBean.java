package br.com.triadworks.issuetracker.controller;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

import br.com.triadworks.issuetracker.controller.util.FacesUtils;
import br.com.triadworks.issuetracker.model.Usuario;
import br.com.triadworks.issuetracker.service.Autenticador;

@ManagedBean
@RequestScoped
public class LoginBean {

	private String login;
	private String senha;
	
	@ManagedProperty("#{usuarioWeb}")
	private UsuarioWeb usuarioWeb;
	
	@ManagedProperty("#{autenticador}")
	private Autenticador autenticador;
	
	@ManagedProperty("#{facesUtils}")
	private FacesUtils facesUtils;
	
	public String logar() {
		Usuario usuario = autenticador.autentica(login, senha);
		if (usuario != null) {
			usuarioWeb.loga(usuario);
			return "/home?faces-redirect=true";
		}
		facesUtils.adicionaMensagemDeErro("Login ou senha inválida.");
		return null;
	}
	
	public String sair() {
		usuarioWeb.logout();
		return "/pages/login?faces-redirect=true";
	}
	
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public UsuarioWeb getUsuarioWeb() {
		return usuarioWeb;
	}
	public void setUsuarioWeb(UsuarioWeb usuarioWeb) {
		this.usuarioWeb = usuarioWeb;
	}
	public Autenticador getAutenticador() {
		return autenticador;
	}
	public void setAutenticador(Autenticador autenticador) {
		this.autenticador = autenticador;
	}
	public FacesUtils getFacesUtils() {
		return facesUtils;
	}
	public void setFacesUtils(FacesUtils facesUtils) {
		this.facesUtils = facesUtils;
	}
	
}
