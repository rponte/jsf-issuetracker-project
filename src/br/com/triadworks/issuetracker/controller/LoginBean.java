package br.com.triadworks.issuetracker.controller;
import br.com.triadworks.issuetracker.controller.util.FacesUtils;
import br.com.triadworks.issuetracker.model.Usuario;
import br.com.triadworks.issuetracker.service.Autenticador;

public class LoginBean {

	private String login;
	private String senha;
	
	private Autenticador autenticador;
	private UsuarioWeb usuarioWeb;
	private FacesUtils facesUtils;
	
	public String logar() {
		
		Usuario usuario = autenticador.autentica(login, senha);
		if (usuario != null) {
			usuarioWeb.loga(usuario);
			return "home"; // outcome
		}

		facesUtils.adicionaMensagemDeErro("Login ou senha invalida.");
		return null;
	}
	
	public String sair() {
		usuarioWeb.logout();
		return "login";
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
	public void setAutenticador(Autenticador autenticador) {
		this.autenticador = autenticador;
	}
	public void setUsuarioWeb(UsuarioWeb usuarioWeb) {
		this.usuarioWeb = usuarioWeb;
	}
	public void setFacesUtils(FacesUtils facesUtils) {
		this.facesUtils = facesUtils;
	}
	
}
