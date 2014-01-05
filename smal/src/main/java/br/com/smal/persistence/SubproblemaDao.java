package br.com.smal.persistence;

import java.util.List;

import br.com.smal.domain.Subproblema;

public interface SubproblemaDao {
	public boolean incluir(Subproblema entidade);

	public Subproblema obter(long id);

	public boolean alterar(Subproblema entidade);

	public boolean excluir(long id);

	public List<Subproblema> obterTodos();
}
