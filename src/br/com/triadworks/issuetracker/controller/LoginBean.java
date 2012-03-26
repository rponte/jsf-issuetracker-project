package br.com.triadworks.issuetracker.controller;
import br.com.triadworks.issuetracker.model.Usuario;
import br.com.triadworks.issuetracker.service.Autenticador;

public class LoginBean {

	private String login;
	private String senha;
	
	private Autenticador autenticador;
	
	public String logar() {
		
		Usuario usuario = autenticador.autentica(login, senha);
		if (usuario != null) {
			System.out.println("Logado com Sucesso!");
		} else {
			System.out.println("Login ou senha invalida.");
		}
		
		return null;
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
	
}
