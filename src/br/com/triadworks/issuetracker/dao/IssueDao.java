package br.com.triadworks.issuetracker.dao;

import java.util.List;

import br.com.triadworks.issuetracker.model.Comentario;
import br.com.triadworks.issuetracker.model.Issue;

public interface IssueDao {

	public List<Issue> listaTudo();
	
	public void salva(Issue issue);
	
	public void atualiza(Issue issue);
	
	public void remove(Issue issue);
	
	public Issue carrega(Long id);

	public List<Issue> getIssuesDoUsuario(Long id);

	public void comenta(Long id, Comentario comentario);

	public void fecha(Long id, Comentario comentario);
	
}
