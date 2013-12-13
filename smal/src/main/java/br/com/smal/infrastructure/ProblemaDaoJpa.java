package br.com.smal.infrastructure;

import java.util.List;

import org.springframework.stereotype.Component;

import br.com.smal.domain.Problema;
import br.com.smal.persistence.ProblemaDao;

@Component
public class ProblemaDaoJpa extends GenericDaoJpa<Problema> implements
		ProblemaDao {

	public Problema obter(long id) {
		return obterPorId(Problema.class, id);
	}

	public boolean excluir(long id) {
		return excluir(Problema.class, id);
	}

	public List<Problema> obterTodos() {
		return obterTodos(Problema.class);
	}
}
