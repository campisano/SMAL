package br.com.smal.persistence;

import java.util.List;

import br.com.smal.domain.Chamado;

public interface ChamadoDao {
	public boolean incluir(Chamado entidade);

	public Chamado obter(long id);

	public boolean alterar(Chamado entidade);

	public boolean excluir(long id);

	public List<Chamado> obterTodos();
}
