package br.com.smal.persistence;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.smal.domain.Posicao;

@Component
public class PosicaoRepositorio {

	@Autowired
	PosicaoDao posicaoDao;

	public boolean incluir(Posicao entidade) {
		return posicaoDao.incluir(entidade);
	}

	public Posicao obter(long id) {
		return posicaoDao.obter(id);
	}

	public boolean alterar(Posicao entidade) {
		return posicaoDao.alterar(entidade);
	}

	public boolean excluir(long id) {
		return posicaoDao.excluir(id);
	}

	public List<Posicao> obterTodos() {
		return posicaoDao.obterTodos();
	}
}
