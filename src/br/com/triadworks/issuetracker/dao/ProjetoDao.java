package br.com.triadworks.issuetracker.dao;

import java.util.List;

import br.com.triadworks.issuetracker.model.Projeto;

public interface ProjetoDao {

	public List<Projeto> listaTudo();

	public void salva(Projeto projeto);

	public void atualiza(Projeto projeto);

	public void remove(Projeto projeto);

	public Projeto carrega(Long id);
	
}
