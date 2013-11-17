package br.com.smal.persistence;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.smal.domain.Laboratorio;

@Component
public class LaboratorioRepositorio {

	@Autowired
	LaboratorioDao laboratorioDao;

	public boolean incluir(Laboratorio entidade) {
		return laboratorioDao.incluir(entidade);
	}

	public Laboratorio obter(long id) {
		return laboratorioDao.obter(id);
	}

	public boolean alterar(Laboratorio entidade) {
		return laboratorioDao.alterar(entidade);
	}

	public boolean excluir(long id) {
		return laboratorioDao.excluir(id);
	}

	public List<Laboratorio> obterTodos() {
		return laboratorioDao.obterTodos();
	}
}
