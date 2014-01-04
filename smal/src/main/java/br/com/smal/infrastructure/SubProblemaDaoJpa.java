package br.com.smal.infrastructure;

import java.util.List;

import org.springframework.stereotype.Component;

import br.com.smal.domain.Problema;
import br.com.smal.domain.Subproblema;
import br.com.smal.persistence.SubProblemaDao;

@Component
public class SubProblemaDaoJpa extends GenericDaoJpa<Subproblema> implements
		SubProblemaDao {

	public Subproblema obter(long id) {
		return obterPorId(Subproblema.class, id);
	}

	public boolean excluir(long id) {
		return excluir(Subproblema.class, id);
	}

	public List<Subproblema> obterTodos() {
		return obterTodos(Subproblema.class);
	}
}
