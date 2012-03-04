package br.com.triadworks.issuetracker.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.triadworks.issuetracker.dao.UsuarioDao;
import br.com.triadworks.issuetracker.model.Usuario;

@Repository("usuarioDao")
@Transactional
public class UsuarioDaoImpl implements UsuarioDao {

	@PersistenceContext
	private EntityManager entityManager;
	
	@SuppressWarnings("unchecked")
	@Override
	@Transactional(readOnly=true)
	public List<Usuario> listaTudo() {
		return entityManager
				.createQuery("from Usuario")
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
	public Usuario buscaPor(String login, String senha) {
		String hql = "from Usuario u where u.login = :login and u.senha = :senha";
		Query query = entityManager.createQuery(hql)
						.setParameter("login", login)
						.setParameter("senha", senha);
		try {
			return (Usuario) query.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

	@Override
	public Usuario carrega(Long id) {
		return entityManager.find(Usuario.class, id);
	}

}
