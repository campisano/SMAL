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

	public Posicao obter(int fila, int coluna) {
		return posicaoDao.obter(fila, coluna);
	}

	public boolean alterar(Posicao entidade) {
		return posicaoDao.alterar(entidade);
	}

	public boolean excluir(int fila, int coluna) {
		return posicaoDao.excluir(fila, coluna);
	}

	public List<Posicao> obterTodos() {
		return posicaoDao.obterTodos();
	}
}
