package br.com.smal.infrastructure;

import java.util.List;

import org.springframework.stereotype.Component;

import br.com.smal.domain.Laboratorio;
import br.com.smal.persistence.LaboratorioDao;

@Component
public class LaboratorioDaoJpa extends GenericDaoJpa<Laboratorio> implements
		LaboratorioDao {

	public Laboratorio obter(long id) {
		return obterPorId(Laboratorio.class, id);
	}

	public boolean excluir(long id) {
		return excluir(Laboratorio.class, id);
	}

	public List<Laboratorio> obterTodos() {
		return obterTodos(Laboratorio.class);
	}
}
