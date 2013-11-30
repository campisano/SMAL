package br.com.smal.infrastructure;

import java.util.List;

import org.springframework.stereotype.Component;

import br.com.smal.domain.Maquina;
import br.com.smal.persistence.MaquinaDao;

@Component
public class MaquinaDaoJpa extends GenericDaoJpa<Maquina> implements
		MaquinaDao {

	public Maquina obter(long id) {
		return obterPorId(Maquina.class, id);
	}

	public boolean excluir(long id) {
		return excluir(Maquina.class, id);
	}

	public List<Maquina> obterTodos() {
		return obterTodos(Maquina.class);
	}
}
