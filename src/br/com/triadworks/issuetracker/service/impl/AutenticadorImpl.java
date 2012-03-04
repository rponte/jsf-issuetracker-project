package br.com.triadworks.issuetracker.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.triadworks.issuetracker.dao.UsuarioDao;
import br.com.triadworks.issuetracker.model.Usuario;
import br.com.triadworks.issuetracker.service.Autenticador;

@Service("autenticador")
public class AutenticadorImpl implements Autenticador {

	@Autowired
	private UsuarioDao usuarioDao;
	
	@Override
	public Usuario autentica(String login, String senha) {
		Usuario usuario = usuarioDao.buscaPor(login, senha);
		return usuario;
	}

}
