package br.com.smal.infrastructure;

import java.util.List;

import org.springframework.stereotype.Component;

import br.com.smal.domain.Chamado;
import br.com.smal.persistence.ChamadoDao;

@Component
public class ChamadoDaoJpa extends GenericDaoJpa<Chamado> implements ChamadoDao {

	public Chamado obter(long id) {
		return obterPorId(Chamado.class, id);
	}

	public boolean excluir(long id) {
		return excluir(Chamado.class, id);
	}

	public List<Chamado> obterTodos() {
		return obterTodos(Chamado.class);
	}
}
