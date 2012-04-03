package br.com.triadworks.issuetracker.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.com.triadworks.issuetracker.controller.util.FacesUtils;
import br.com.triadworks.issuetracker.dao.IssueDao;
import br.com.triadworks.issuetracker.model.Issue;

@Controller
@Scope("request")
public class DashboardBean {

	private Long id;
	private Issue issue;
	private List<Issue> issues = new ArrayList<Issue>();
	
	private IssueDao issueDao;
	private UsuarioWeb usuarioWeb;
	private FacesUtils facesUtils;
	
	@Autowired
	public DashboardBean(IssueDao issueDao, UsuarioWeb usuarioWeb, FacesUtils facesUtils) {
		this.issueDao = issueDao;
		this.usuarioWeb = usuarioWeb;
		this.facesUtils = facesUtils;
	}
	
	@PostConstruct
	public void preload() {
		Long id = usuarioWeb.getUsuario().getId();
		issues = issueDao.getIssuesDoUsuario(id);
	}
	
	public void init() {
		if (id == null) {
            String mensagem = "Bad request. Please use a link from within the system.";
            facesUtils.adicionaMensagemDeErro(mensagem);
            return;
        }
		
		issue = issueDao.carrega(id);
		if (issue == null) {
            String mensagem = "Bad request. Unknown issue.";
            facesUtils.adicionaMensagemDeErro(mensagem);
        }
	}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public List<Issue> getIssues() {
		return issues;
	}
	public Issue getIssue() {
		return issue;
	}
	public void setIssue(Issue issue) {
		this.issue = issue;
	}
	
}
