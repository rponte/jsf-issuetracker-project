package br.com.triadworks.issuetracker.dao;

import java.util.List;

import br.com.triadworks.issuetracker.model.Issue;

public interface IssueDao {

	public List<Issue> listaTudo();
	
	public void salva(Issue issue);
	
	public void atualiza(Issue issue);
	
	public void remove(Issue issue);
	
}
