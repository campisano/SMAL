package br.com.smal.persistence;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.smal.domain.Problema;
import br.com.smal.domain.Subproblema;

@Component
public class SubProblemaRepositorio {

	@Autowired
	SubProblemaDao subProblemaDao;

	public boolean incluir(Subproblema entidade) {
		return subProblemaDao.incluir(entidade);
	}

	public Subproblema obter(long id) {
		return subProblemaDao.obter(id);
	}

	public boolean alterar(Subproblema entidade) {
		return subProblemaDao.alterar(entidade);
	}

	public boolean excluir(long id) {
		return subProblemaDao.excluir(id);
	}

	public List<Subproblema> obterTodos() {
		return subProblemaDao.obterTodos();
	}
}
