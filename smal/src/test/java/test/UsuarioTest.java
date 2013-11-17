package test;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import br.com.smal.domain.Usuario;
import br.com.smal.persistence.UsuarioRepositorio;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class UsuarioTest {

	@Autowired
	private UsuarioRepositorio usuarioRepositorio;

	@Test
	public void testNewUser() {

		EntityManager entityManager = Persistence.createEntityManagerFactory(
				"smal-persistence-unit").createEntityManager();

		entityManager.getTransaction().begin();

		Usuario user = new Usuario();

		user.setMatricula(Long.toString(new Date().getTime()));

		entityManager.persist(user);

		entityManager.getTransaction().commit();

		// see that the ID of the user was set by Hibernate
		System.out.println("user=" + user + ", user.id=" + user.getId());

		Usuario foundUser = entityManager.find(Usuario.class, user.getId());

		// note that foundUser is the same instance as user and is a concrete
		// class (not a proxy)
		System.out.println("foundUser=" + foundUser);

		Assert.assertEquals(user.getMatricula(), foundUser.getMatricula());

		entityManager.close();
	}

	@Test
	public void testUsuarioDao() {
		List<Usuario> usuarios = usuarioRepositorio.obterTodos();
		Assert.assertTrue(usuarios.size() > 0);
	}

	// TODO [CMP] example exception needed test
	@Test(expected = Exception.class)
	public void testNewUserWithTxn() throws Exception {

		EntityManager entityManager = Persistence.createEntityManagerFactory(
				"smal-persistence-unit").createEntityManager();

		entityManager.getTransaction().begin();
		try {
			Usuario user = new Usuario();

			user.setMatricula(Long.toString(new Date().getTime()));

			entityManager.persist(user);

			if (true) {
				throw new Exception();
			}

			entityManager.getTransaction().commit();
		} catch (Exception e) {
			entityManager.getTransaction().rollback();
			throw e;
		}

		entityManager.close();
	}
}
