package br.com.triadworks.issuetracker.controller;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.component.UIForm;

import br.com.triadworks.issuetracker.controller.util.FacesUtils;
import br.com.triadworks.issuetracker.dao.ProjetoDao;
import br.com.triadworks.issuetracker.model.Projeto;

@ManagedBean
public class ProjetoBean {
	
	private Projeto projeto = new Projeto();
	private List<Projeto> projetos = new ArrayList<Projeto>();
	
	private static final String ESTADO_DE_NOVO = "_novo";
	private static final String ESTADO_DE_EDICAO = "_edicao";
	private static final String ESTADO_DE_PESQUISA = "_pesquisa";
	
	private String state = ESTADO_DE_PESQUISA;
	
	private UIForm form;
	
	@ManagedProperty("#{projetoDao}")
	private ProjetoDao projetoDao;
	@ManagedProperty("#{facesUtils}")
	private FacesUtils facesUtils;
	
	public void lista() {
		projetos = projetoDao.listaTudo();
		setState(ESTADO_DE_PESQUISA);
	}
	
	public void preparaParaAdicionar() {
		this.projeto = new Projeto();
		facesUtils.cleanSubmittedValues(form); // limpa arvore
		setState(ESTADO_DE_NOVO);
	}
	
	public void adiciona() {
		projetoDao.salva(projeto);
		facesUtils.adicionaMensagemDeInformacao("Projeto adicionado com sucesso!");
		lista();
	}
	
	public void remove() {
		projetoDao.remove(projeto);
		facesUtils.adicionaMensagemDeInformacao("Projeto removido com sucesso!");
		lista();
	}
	
	public void preparaParaAlterar(Projeto projeto) {
		this.projeto = projetoDao.carrega(projeto.getId()); // evita LazyInitializationException
		facesUtils.cleanSubmittedValues(form); // limpa arvore
		setState(ESTADO_DE_EDICAO);
	}
	
	public void altera() {
		projetoDao.atualiza(projeto);
		facesUtils.adicionaMensagemDeInformacao("Projeto atualizado com sucesso!");
		lista();
	}
	
	public void voltar() {
		this.projeto = new Projeto();
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
	
	public List<Projeto> getprojetos() {
		return projetos;
	}
	public void setProjetoDao(ProjetoDao projetoDao) {
		this.projetoDao = projetoDao;
	}
	public Projeto getprojeto() {
		return projeto;
	}
	public void setprojeto(Projeto projeto) {
		this.projeto = projeto;
	}
	public void setFacesUtils(FacesUtils facesUtils) {
		this.facesUtils = facesUtils;
	}
	public void setprojetos(List<Projeto> projetos) {
		this.projetos = projetos;
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





