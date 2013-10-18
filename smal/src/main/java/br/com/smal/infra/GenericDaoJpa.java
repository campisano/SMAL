package br.com.smal.infra;

import java.util.List;
import java.util.logging.Logger;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

public class GenericDaoJpa<T> {
	
    private static EntityManager em;
	

	Logger logger = Logger.getLogger(GenericDaoJpa.class.getName());
	
	public GenericDaoJpa(){
		GenericDaoJpa.em = new JpaUtil().getEntityManager();
		
	}

	
	public boolean incluir(T entidade) {
		EntityTransaction tx = null;
		try {
			tx = em.getTransaction();
			tx.begin();
			em.persist(entidade);
			tx.commit();
		} catch (Exception ex) {
			if (tx != null && tx.isActive())
				tx.rollback();
			throw new DAOException("Erro na inclus�o de objeto.", ex);
		}
		return true;
	}

	public boolean excluir(T entidade) {
		EntityTransaction tx = null;
		try {
			tx = em.getTransaction();
			tx.begin();
			em.remove(entidade);
			tx.commit();
		} catch (Exception ex) {
			if (tx != null && tx.isActive())
				tx.rollback();
			throw new DAOException("Erro durante a exclus�o.", ex);
		}
		return true;
	}

	public boolean alterar(T entidade) {
		EntityTransaction tx = null;
		try {
			
			tx = em.getTransaction();
			tx.begin();
			entidade = em.merge(entidade);
			tx.commit();
	
		} catch (Exception ex) {
			if (tx != null && tx.isActive())
				tx.rollback();
			throw new DAOException("Erro durante a inclus�o.", ex);
		}
		return true;
	}


	public List<T> obterTodos(Class<T> c) {

		logger.info("Resgatando todos as entidades da classe: " + c.getName());

		String entityName;

		entityName = c.getName().substring(c.getName().lastIndexOf('.') + 1).toLowerCase();
				
		return obterEntidades("SELECT e FROM " + entityName + " e");
	}

	public List<T> obterEntidades(String queryString,
			final Object... positionalParams) {
		Query query = em.createQuery(queryString);
		int i = 0;
		for (Object p : positionalParams) {
			query.setParameter(++i, p);
		}
		@SuppressWarnings("unchecked")
		List<T> l = query.getResultList();

		return l;
	}

	public T obterEntidade(String queryString, final Object... positionalParams) {
		Query query = em.createQuery(queryString);
		int i = 0;
		for (Object p : positionalParams) {
			query.setParameter(++i, p);
		}
		
		@SuppressWarnings("unchecked")
		T entidade = (T) query.getSingleResult();

		return entidade;
	}

	
	

	
}
