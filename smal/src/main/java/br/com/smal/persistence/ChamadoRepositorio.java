package br.com.smal.persistence;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.smal.domain.Chamado;

@Component
public class ChamadoRepositorio {

	@Autowired
	ChamadoDao chamadoDao;

	public boolean incluir(Chamado entidade) {
		return chamadoDao.incluir(entidade);
	}

	public Chamado obter(long id) {
		return chamadoDao.obter(id);
	}

	public boolean alterar(Chamado entidade) {
		return chamadoDao.alterar(entidade);
	}

	public boolean excluir(long id) {
		return chamadoDao.excluir(id);
	}

	public List<Chamado> obterTodos() {
		return chamadoDao.obterTodos();
	}
}
