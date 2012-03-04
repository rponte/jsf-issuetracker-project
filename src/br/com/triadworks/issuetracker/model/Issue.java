package br.com.triadworks.issuetracker.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Issue implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	private String sumario;
	private String descricao;
	
	@ManyToOne
	private Projeto projeto;
	
	@Enumerated(EnumType.STRING)
	private TipoDeIssue tipo;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date reportadoEm;
	
	@ManyToOne
	private Usuario reportadoPor;
	
	@ManyToOne
	private Usuario assinadoPara;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date atualizadoEm;
	
	@Enumerated(EnumType.STRING)
	private Status status = Status.ABERTA;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getSumario() {
		return sumario;
	}
	public void setSumario(String sumario) {
		this.sumario = sumario;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public Projeto getProjeto() {
		return projeto;
	}
	public void setProjeto(Projeto projeto) {
		this.projeto = projeto;
	}
	public TipoDeIssue getTipo() {
		return tipo;
	}
	public void setTipo(TipoDeIssue tipo) {
		this.tipo = tipo;
	}
	public Date getReportadoEm() {
		return reportadoEm;
	}
	public void setReportadoEm(Date reportadoEm) {
		this.reportadoEm = reportadoEm;
	}
	public Usuario getReportadoPor() {
		return reportadoPor;
	}
	public void setReportadoPor(Usuario reportadoPor) {
		this.reportadoPor = reportadoPor;
	}
	public Usuario getAssinadoPara() {
		return assinadoPara;
	}
	public void setAssinadoPara(Usuario assinadoPara) {
		this.assinadoPara = assinadoPara;
	}
	public Date getAtualizadoEm() {
		return atualizadoEm;
	}
	public void setAtualizadoEm(Date atualizadoEm) {
		this.atualizadoEm = atualizadoEm;
	}
	public Status getStatus() {
		return status;
	}
	public void setStatus(Status status) {
		this.status = status;
	}
	
	@PrePersist
	@PreUpdate
	public void onSaveOrUpdate() {
		this.atualizadoEm = new Date();
	}
	
}
