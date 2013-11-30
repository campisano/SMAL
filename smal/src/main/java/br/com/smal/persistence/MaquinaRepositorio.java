package br.com.smal.persistence;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.smal.domain.Maquina;

@Component
public class MaquinaRepositorio {

	@Autowired
	MaquinaDao maquinaDao;

	public boolean incluir(Maquina entidade) {
		return maquinaDao.incluir(entidade);
	}

	public Maquina obter(long id) {
		return maquinaDao.obter(id);
	}

	public boolean alterar(Maquina entidade) {
		return maquinaDao.alterar(entidade);
	}

	public boolean excluir(long id) {
		return maquinaDao.excluir(id);
	}

	public List<Maquina> obterTodos() {
		return maquinaDao.obterTodos();
	}
}
