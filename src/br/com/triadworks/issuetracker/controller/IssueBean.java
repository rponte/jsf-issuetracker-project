package br.com.triadworks.issuetracker.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

import br.com.triadworks.issuetracker.controller.util.FacesUtils;
import br.com.triadworks.issuetracker.dao.IssueDao;
import br.com.triadworks.issuetracker.dao.ProjetoDao;
import br.com.triadworks.issuetracker.dao.UsuarioDao;
import br.com.triadworks.issuetracker.model.Issue;
import br.com.triadworks.issuetracker.model.Projeto;
import br.com.triadworks.issuetracker.model.Usuario;

@ManagedBean
@RequestScoped
public class IssueBean {

	private Issue issue = new Issue();
	private List<Issue> issues = new ArrayList<Issue>();
	private List<Usuario> usuarios = new ArrayList<Usuario>();
	private List<Projeto> projetos = new ArrayList<Projeto>();
	
	@ManagedProperty("#{issueDao}")
	private IssueDao dao;
	
	@ManagedProperty("#{facesUtils}")
	private FacesUtils facesUtils;
	
	@ManagedProperty("#{usuarioWeb}")
	private UsuarioWeb usuarioWeb;
	
	@ManagedProperty("#{projetoDao}")
	private ProjetoDao projetoDao;
	
	@ManagedProperty("#{usuarioDao}")
	private UsuarioDao usuarioDao;
	
	public String lista() {
		issues = dao.listaTudo();
		return "lista";
	}
	
	public String preparaParaAdicionar() {
		issue.setReportadoEm(new Date());
		issue.setReportadoPor(usuarioWeb.getUsuario());
		inicializaCombos();
		return "novo";
	}
	
	public String adiciona() {
		dao.salva(issue);
		facesUtils.adicionaMensagemDeInformacao("Issue adicionada com sucesso!");
		return lista();
	}
	
	public void remove() {
		dao.remove(issue);
		facesUtils.adicionaMensagemDeInformacao("Issue removida com sucesso!");
		lista();
	}
	
	public String preparaParaAlterar(Issue issue) {
		this.issue = issue;
		inicializaCombos();
		return "edita";
	}
	
	public String altera() {
		dao.atualiza(issue);
		facesUtils.adicionaMensagemDeInformacao("Issue alterada com sucesso!");
		return lista();
	}
	
	private void inicializaCombos() {
		projetos = projetoDao.listaTudo();
		usuarios = usuarioDao.listaTudo();
	}
	
	public Issue getIssue() {
		return issue;
	}
	public void setIssue(Issue issue) {
		this.issue = issue;
	}
	public List<Issue> getIssues() {
		return issues;
	}
	public void setIssues(List<Issue> issues) {
		this.issues = issues;
	}
	public IssueDao getDao() {
		return dao;
	}
	public void setDao(IssueDao dao) {
		this.dao = dao;
	}
	public FacesUtils getFacesUtils() {
		return facesUtils;
	}
	public void setFacesUtils(FacesUtils facesUtils) {
		this.facesUtils = facesUtils;
	}
	public UsuarioWeb getUsuarioWeb() {
		return usuarioWeb;
	}
	public void setUsuarioWeb(UsuarioWeb usuarioWeb) {
		this.usuarioWeb = usuarioWeb;
	}
	public ProjetoDao getProjetoDao() {
		return projetoDao;
	}
	public void setProjetoDao(ProjetoDao projetoDao) {
		this.projetoDao = projetoDao;
	}
	public UsuarioDao getUsuarioDao() {
		return usuarioDao;
	}
	public void setUsuarioDao(UsuarioDao usuarioDao) {
		this.usuarioDao = usuarioDao;
	}
	public List<Usuario> getUsuarios() {
		return usuarios;
	}
	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}
	public List<Projeto> getProjetos() {
		return projetos;
	}
	public void setProjetos(List<Projeto> projetos) {
		this.projetos = projetos;
	}
	
}
