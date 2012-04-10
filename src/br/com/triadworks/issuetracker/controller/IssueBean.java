package br.com.triadworks.issuetracker.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.component.UIForm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.com.triadworks.issuetracker.controller.util.FacesUtils;
import br.com.triadworks.issuetracker.dao.IssueDao;
import br.com.triadworks.issuetracker.model.Issue;

@Controller
@Scope("request")
public class IssueBean {
	
	private static final String ESTADO_DE_NOVO = "_novo";
	private static final String ESTADO_DE_EDICAO = "_edicao";
	private static final String ESTADO_DE_PESQUISA = "_pesquisa";
	
	private String state = ESTADO_DE_PESQUISA;
	
	private UIForm form;
	private Issue issue = new Issue();
	private List<Issue> issues = new ArrayList<Issue>();
	
	private IssueDao issueDao;
	private UsuarioWeb usuarioWeb;
	private FacesUtils facesUtils;
	
	@Autowired
	public IssueBean(IssueDao issueDao, UsuarioWeb usuarioWeb, FacesUtils facesUtils) {
		this.issueDao = issueDao;
		this.usuarioWeb = usuarioWeb;
		this.facesUtils = facesUtils;
	}

	public void lista() {
		issues = issueDao.listaTudo();
		setState(ESTADO_DE_PESQUISA);
	}
	
	public void preparaParaAdicionar() {
		this.issue = new Issue();
//		issue.setProjeto(new Projeto()); // não mais necessário por causa do converter
//		issue.setAssinadoPara(new Usuario());  // não mais necessário por causa do converter
		issue.setReportadoPor(usuarioWeb.getUsuario());
		issue.setReportadoEm(new Date());
		facesUtils.cleanSubmittedValues(form); // limpa arvore
		setState(ESTADO_DE_NOVO);
	}
	
	public void adiciona() {
		issueDao.salva(issue);
		facesUtils.adicionaMensagemDeInformacao("Issue adicionada com sucesso!");
		lista();
	}
	
	public void remove() {
		issueDao.remove(issue);
		facesUtils.adicionaMensagemDeInformacao("Issue removida com sucesso!");
		lista();
	}
	
	public void preparaParaAlterar(Issue projeto) {
		this.issue = issueDao.carrega(projeto.getId()); // evita LazyInitializationException
		facesUtils.cleanSubmittedValues(form); // limpa arvore
		setState(ESTADO_DE_EDICAO);
	}
	
	public void altera() {
		issueDao.atualiza(issue);
		facesUtils.adicionaMensagemDeInformacao("Issue atualizada com sucesso!");
		lista();
	}
	
	public void voltar() {
		this.issue = new Issue();
		facesUtils.cleanSubmittedValues(form); // limpa arvore
		lista();
	}
	
	public boolean isAdicionando() {
		return ESTADO_DE_NOVO.equals(state);
	}
	public boolean isEditando() {
		return ESTADO_DE_EDICAO.equals(state);
	}
	public boolean isPesquisando() {
		return ESTADO_DE_PESQUISA.equals(state);
	}
	
	public List<Issue> getIssues() {
		return issues;
	}
	public Issue getIssue() {
		return issue;
	}
	public void setIssue(Issue projeto) {
		this.issue = projeto;
	}
	public void setIssues(List<Issue> issues) {
		this.issues = issues;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public UIForm getForm() {
		return form;
	}
	public void setForm(UIForm form) {
		this.form = form;
	}
	
}





