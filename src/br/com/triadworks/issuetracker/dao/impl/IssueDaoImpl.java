package br.com.triadworks.issuetracker.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.triadworks.issuetracker.dao.IssueDao;
import br.com.triadworks.issuetracker.model.Comentario;
import br.com.triadworks.issuetracker.model.Issue;

@Repository("issueDao")
@Transactional
public class IssueDaoImpl implements IssueDao {

	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	@Transactional(propagation=Propagation.SUPPORTS, readOnly=true)
	public List<Issue> listaTudo() {
		return entityManager
				.createQuery("from Issue", Issue.class)
				.getResultList();
	}

	@Override
	public void salva(Issue issue) {
		entityManager.persist(issue);
	}

	@Override
	public void atualiza(Issue issue) {
		entityManager.merge(issue);
	}

	@Override
	public void remove(Issue issue) {
		entityManager.remove(entityManager.merge(issue));
	}

	@Override
	@Transactional(propagation=Propagation.SUPPORTS, readOnly=true)
	public Issue carrega(Long id) {
		return entityManager.find(Issue.class, id);
	}

	@Override
	@Transactional(propagation=Propagation.SUPPORTS, readOnly=true)
	public List<Issue> getIssuesDoUsuario(Long id) {
		return entityManager
				.createQuery("from Issue where assinadoPara.id = :id", Issue.class)
				.setParameter("id", id)
				.getResultList();
	}

	@Override
	public void comenta(Long id, Comentario comentario) {
		Issue issue = carrega(id);
		issue.comenta(comentario); // thanks persistence context ;-)
	}

	@Override
	public void fecha(Long id, Comentario comentario) {
		Issue issue = carrega(id);
		issue.fecha(comentario); // thanks persistence context ;-)
	}
	
}
