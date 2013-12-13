package br.com.smal.persistence;

import java.util.List;

import br.com.smal.domain.Problema;

public interface ProblemaDao {
	public boolean incluir(Problema entidade);

	public Problema obter(long id);

	public boolean alterar(Problema entidade);

	public boolean excluir(long id);

	public List<Problema> obterTodos();
}
