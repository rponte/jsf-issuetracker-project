package br.com.triadworks.issuetracker.controller.dashboard;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.com.triadworks.issuetracker.controller.UsuarioWeb;
import br.com.triadworks.issuetracker.dao.IssueDao;
import br.com.triadworks.issuetracker.model.Issue;

@Controller
@Scope("request")
public class DashboardBean {

	private List<Issue> issues = new ArrayList<Issue>();
	
	private IssueDao issueDao;
	private UsuarioWeb usuarioWeb;
	
	@Autowired
	public DashboardBean(IssueDao issueDao, UsuarioWeb usuarioWeb) {
		this.issueDao = issueDao;
		this.usuarioWeb = usuarioWeb;
	}
	
	@PostConstruct
	public void preload() {
		Long id = usuarioWeb.getUsuario().getId();
		issues = issueDao.getIssuesDoUsuario(id);
	}
	
	public List<Issue> getIssues() {
		return issues;
	}
	
}
