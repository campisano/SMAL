package test;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import br.com.smal.domain.Laboratorio;
import br.com.smal.persistence.LaboratorioRepositorio;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class LaboratorioTest {

	@Autowired
	private LaboratorioRepositorio laboratorioRepositorio;
	private static ApplicationContext context;

	public void setLaboratorioRepositorio(
			LaboratorioRepositorio laboratorioRepositorio) {
		this.laboratorioRepositorio = laboratorioRepositorio;
	}

	public static void main(String[] args) {
		context = new ClassPathXmlApplicationContext(
				new String[] { "applicationContext.xml" });
		LaboratorioRepositorio laboratorioRepositorio = context
				.getBean(LaboratorioRepositorio.class);

		try {
			LaboratorioTest test = new LaboratorioTest();
			test.setLaboratorioRepositorio(laboratorioRepositorio);
			test.testCreate();
			test.testRead();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testCreate() {
		// prepare
		Laboratorio laboratorio_novo = new Laboratorio();
		laboratorio_novo.setId(null);
		laboratorio_novo.setNome("lab_create");

		// run and test
		Assert.assertTrue(laboratorioRepositorio.incluir(laboratorio_novo));
		Assert.assertNotEquals(laboratorio_novo.getId().longValue(), -1l);
	}

	@Test
	public void testRead() {
		// prepare
		Long novo_id;
		String novo_nome;
		{
			Laboratorio laboratorio_novo = new Laboratorio();
			laboratorio_novo.setId(null);
			laboratorio_novo.setNome("lab_read");
			laboratorioRepositorio.incluir(laboratorio_novo);
			Assert.assertNotEquals(laboratorio_novo.getId().longValue(), -1l);
			novo_id = laboratorio_novo.getId();
			novo_nome = laboratorio_novo.getNome();
		}

		// run
		Laboratorio laboratorio_salvo = laboratorioRepositorio.obter(novo_id
				.longValue());

		// test
		Assert.assertEquals(novo_id.longValue(), laboratorio_salvo.getId()
				.longValue());
		Assert.assertEquals(novo_nome, laboratorio_salvo.getNome());
	}

	@Test
	public void testReadAll() {
		// prepare
		{
			Laboratorio laboratorio_novo = new Laboratorio();
			laboratorio_novo.setId(null);
			laboratorio_novo.setNome("lab_read_all");
			laboratorioRepositorio.incluir(laboratorio_novo);
			Assert.assertNotEquals(laboratorio_novo.getId().longValue(), -1l);
		}

		// run
		List<Laboratorio> laboratorios = laboratorioRepositorio.obterTodos();

		// test
		Assert.assertNotNull(laboratorios);
		Assert.assertTrue(laboratorios.size() > 0);
	}

	@Test
	public void testUpdate() {
		// prepare
		Long novo_id;
		String novo_nome;
		{
			Laboratorio laboratorio_novo = new Laboratorio();
			laboratorio_novo.setId(null);
			laboratorio_novo.setNome("lab_update");
			laboratorioRepositorio.incluir(laboratorio_novo);
			Assert.assertNotEquals(laboratorio_novo.getId().longValue(), -1l);
			novo_id = laboratorio_novo.getId();
			novo_nome = laboratorio_novo.getNome();
		}

		{
			Laboratorio laboratorio_salvo = laboratorioRepositorio
					.obter(novo_id.longValue());
			laboratorio_salvo
					.setNome(laboratorio_salvo.getNome() + "_alterado");

			// run
			Assert.assertTrue(laboratorioRepositorio.alterar(laboratorio_salvo));
		}

		// test
		Laboratorio laboratorio_alterado = laboratorioRepositorio.obter(novo_id
				.longValue());

		Assert.assertEquals(novo_id.longValue(), laboratorio_alterado.getId()
				.longValue());
		Assert.assertNotEquals(novo_nome, laboratorio_alterado.getNome());
	}

	@Test
	public void testDelete() {
		// prepare
		Long novo_id;
		{
			Laboratorio laboratorio_novo = new Laboratorio();
			laboratorio_novo.setId(null);
			laboratorio_novo.setNome("lab_delete");
			laboratorioRepositorio.incluir(laboratorio_novo);
			Assert.assertNotEquals(laboratorio_novo.getId().longValue(), -1l);
			novo_id = laboratorio_novo.getId();
		}

		// run and test
		Assert.assertTrue(laboratorioRepositorio.excluir(novo_id.longValue()));

		Laboratorio laboratorio_salvo = laboratorioRepositorio.obter(novo_id
				.longValue());

		Assert.assertNull(laboratorio_salvo);
	}
}
