package br.com.smal.persistence;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.smal.domain.Subproblema;

@Component
public class SubproblemaRepositorio {

	@Autowired
	SubproblemaDao subproblemaDao;

	public boolean incluir(Subproblema entidade) {
		return subproblemaDao.incluir(entidade);
	}

	public Subproblema obter(long id) {
		return subproblemaDao.obter(id);
	}

	public boolean alterar(Subproblema entidade) {
		return subproblemaDao.alterar(entidade);
	}

	public boolean excluir(long id) {
		return subproblemaDao.excluir(id);
	}

	public List<Subproblema> obterTodos() {
		return subproblemaDao.obterTodos();
	}
}
