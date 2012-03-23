package br.com.triadworks.issuetracker.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.triadworks.issuetracker.dao.UsuarioDao;
import br.com.triadworks.issuetracker.model.Usuario;

@Repository("usuarioDao")
@Transactional
public class UsuarioDaoImpl implements UsuarioDao {

	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	@Transactional(propagation=Propagation.SUPPORTS, readOnly=true)
	public List<Usuario> listaTudo() {
		return entityManager
				.createQuery("from Usuario", Usuario.class)
				.getResultList();
	}

	@Override
	public void salva(Usuario usuario) {
		entityManager.persist(usuario);
	}

	@Override
	public void atualiza(Usuario usuario) {
		entityManager.merge(usuario);
	}

	@Override
	public void remove(Usuario usuario) {
		entityManager.remove(entityManager.merge(usuario));
	}

	@Override
	@Transactional(propagation=Propagation.SUPPORTS, readOnly=true)
	public Usuario buscaPor(String login, String senha) {
		return (Usuario) createCriteria()
			.add(Restrictions.eq("login", login))
			.add(Restrictions.eq("senha", senha))
			.uniqueResult();
	}

	@Override
	@Transactional(propagation=Propagation.SUPPORTS, readOnly=true)
	public Usuario carrega(Long id) {
		return entityManager.find(Usuario.class, id);
	}
	
	private Criteria createCriteria() {
		Session session = ((Session) entityManager.getDelegate());
		return session.createCriteria(Usuario.class);
	}

}
