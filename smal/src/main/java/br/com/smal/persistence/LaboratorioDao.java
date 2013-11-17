package br.com.smal.persistence;

import java.util.List;

import br.com.smal.domain.Laboratorio;

public interface LaboratorioDao {
	public boolean incluir(Laboratorio entidade);

	public Laboratorio obter(long id);

	public boolean alterar(Laboratorio entidade);

	public boolean excluir(long id);

	public List<Laboratorio> obterTodos();
}
