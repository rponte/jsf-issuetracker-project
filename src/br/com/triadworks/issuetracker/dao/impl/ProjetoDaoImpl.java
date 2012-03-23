package br.com.triadworks.issuetracker.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.triadworks.issuetracker.dao.ProjetoDao;
import br.com.triadworks.issuetracker.model.Projeto;

@Repository("projetoDao")
@Transactional
public class ProjetoDaoImpl implements ProjetoDao {

	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	@Transactional(propagation=Propagation.SUPPORTS, readOnly=true)
	public List<Projeto> listaTudo() {
		return entityManager
				.createQuery("from Projeto", Projeto.class)
				.getResultList();
	}

	@Override
	public void salva(Projeto projeto) {
		entityManager.persist(projeto);
	}

	@Override
	public void atualiza(Projeto projeto) {
		entityManager.merge(projeto);
	}

	@Override
	public void remove(Projeto projeto) {
		entityManager.remove(entityManager.merge(projeto));
	}

	@Override
	@Transactional(propagation=Propagation.SUPPORTS, readOnly=true)
	public Projeto carrega(Long id) {
		return entityManager.find(Projeto.class, id);
	}

}
