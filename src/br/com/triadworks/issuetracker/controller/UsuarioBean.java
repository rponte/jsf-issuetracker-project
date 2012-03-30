package br.com.triadworks.issuetracker.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;

import br.com.triadworks.issuetracker.controller.util.FacesUtils;
import br.com.triadworks.issuetracker.dao.UsuarioDao;
import br.com.triadworks.issuetracker.model.Usuario;

@ManagedBean
public class UsuarioBean {
	
	private Usuario usuario = new Usuario();
	private String confirmacaoDeSenha;
	private List<Usuario> usuarios = new ArrayList<Usuario>();
	
	@ManagedProperty("#{usuarioDao}")
	private UsuarioDao usuarioDao;
	@ManagedProperty("#{facesUtils}")
	private FacesUtils facesUtils;
	
	public String lista() {
		usuarios = usuarioDao.listaTudo();
		return null;
	}
	
	public String adiciona() {
		
		boolean senhaInvalida = !confirmacaoDeSenha.equals(usuario.getSenha());
		if (senhaInvalida) {
			facesUtils.adicionaMensagemDeErro("Senha e confirmação de senha não conferem.");
			return null;
		}
		
		usuarioDao.salva(usuario);
		facesUtils.adicionaMensagemDeInformacao("Usuário adicionado com sucesso!");
		return "lista";
	}
	
	public String remove() {
		usuarioDao.remove(usuario);
		facesUtils.adicionaMensagemDeInformacao("Usuário removido com sucesso!");
		return lista();
	}
	
	public String preparaParaAlterar(Usuario usuario) {
		this.usuario = usuarioDao.carrega(usuario.getId()); // evita LazyInitializationException
		return "altera";
	}
	
	public String altera() {
		usuarioDao.atualiza(usuario);
		facesUtils.adicionaMensagemDeInformacao("Usuário atualizado com sucesso!");
		return "lista";
	}
	
	public List<Usuario> getUsuarios() {
		return usuarios;
	}
	public void setUsuarioDao(UsuarioDao usuarioDao) {
		this.usuarioDao = usuarioDao;
	}
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	public String getConfirmacaoDeSenha() {
		return confirmacaoDeSenha;
	}
	public void setConfirmacaoDeSenha(String confirmacaoDeSenha) {
		this.confirmacaoDeSenha = confirmacaoDeSenha;
	}
	public void setFacesUtils(FacesUtils facesUtils) {
		this.facesUtils = facesUtils;
	}
	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}
}





