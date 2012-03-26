package br.com.triadworks.issuetracker.service;

import br.com.triadworks.issuetracker.model.Usuario;

public interface Autenticador {

	public Usuario autentica(String login, String senha);

}
