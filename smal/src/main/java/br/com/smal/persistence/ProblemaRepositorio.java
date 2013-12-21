package br.com.smal.persistence;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.smal.domain.Problema;

@Component
public class ProblemaRepositorio {

	@Autowired
	ProblemaDao problemaDao;

	public boolean incluir(Problema entidade) {
		return problemaDao.incluir(entidade);
	}

	public Problema obter(long id) {
		return problemaDao.obter(id);
	}

	public boolean alterar(Problema entidade) {
		return problemaDao.alterar(entidade);
	}

	public boolean excluir(long id) {
		return problemaDao.excluir(id);
	}

	public List<Problema> obterTodos() {
		return problemaDao.obterTodos();
	}
}
