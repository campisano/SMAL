package br.com.smal.infrastructure;

import java.util.List;

import org.springframework.stereotype.Component;

import br.com.smal.domain.Posicao;
import br.com.smal.persistence.PosicaoDao;

@Component
public class PosicaoDaoJpa extends GenericDaoJpa<Posicao> implements PosicaoDao {

	public Posicao obter(long id) {
		return obterPorId(Posicao.class, id);
	}

	public boolean excluir(long id) {
		return excluir(Posicao.class, id);
	}

	public List<Posicao> obterTodos() {
		return obterTodos(Posicao.class);
	}
}
