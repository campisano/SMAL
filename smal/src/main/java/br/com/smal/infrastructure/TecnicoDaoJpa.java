package br.com.smal.infrastructure;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.stereotype.Component;

import br.com.smal.domain.Tecnico;
import br.com.smal.persistence.TecnicoDao;

@Component
public class TecnicoDaoJpa extends GenericDaoJpa<Tecnico> implements TecnicoDao {

	EntityManager em = new JpaUtil().getEntityManager();

	@Override
	public Tecnico obter(long id) {
		return obterPorId(Tecnico.class, id);
	}

	@Override
	public boolean excluir(long id) {
		return excluir(Tecnico.class, id);
	}

	@Override
	public List<Tecnico> obterTodos() {
		return obterTodos(Tecnico.class);
	}

	@Override
	public boolean existe(Tecnico entidade) {

		Tecnico tecnicoExistente = getTecnicoPorMatricula(entidade
				.getMatricula());

		if (tecnicoExistente != null) {

			return true;

		} else {

			return false;
		}

	}

	public boolean existeMatriculaSenha(String matricula, String senha) {

		StringBuilder cs = new StringBuilder();
		cs.append("SELECT a FROM tecnico a WHERE a.matricula = ?");
		cs.append(" AND a.senha = ?");

		Query query = em.createQuery(cs.toString());
		query.setParameter(1, matricula);
		query.setParameter(2, senha);

		if (query.getSingleResult() != null) {
			return true;

		} else {
			return false;
		}
	}

	public Tecnico getTecnicoPorMatricula(String matricula) {
		String consulta = "SELECT a from tecnico a WHERE a.matricula = ?";
		Object array[] = {matricula};
		return super.obterEntidade(consulta, array);
	}

}
