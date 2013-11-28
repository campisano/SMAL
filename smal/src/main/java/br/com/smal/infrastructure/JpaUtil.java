package br.com.smal.infrastructure;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JpaUtil {

	private static EntityManagerFactory entityManagerFactory = Persistence
			.createEntityManagerFactory("smal-persistence-unit");

	public EntityManager getEntityManager() {

		return entityManagerFactory.createEntityManager();
	}
}