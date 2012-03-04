package br.com.triadworks.issuetracker.dao;

import java.util.List;

import br.com.triadworks.issuetracker.model.Usuario;

public interface UsuarioDao {

	public List<Usuario> listaTudo();

	public void salva(Usuario usuario);

	public void atualiza(Usuario usuario);

	public void remove(Usuario usuario);

	public Usuario buscaPor(String login, String senha);

	public Usuario carrega(Long id);
	
}
