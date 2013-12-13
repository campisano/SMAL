package br.com.smal.persistence;

import java.util.List;

import br.com.smal.domain.Maquina;

public interface MaquinaDao {
	public boolean incluir(Maquina entidade);

	public Maquina obter(long id);

	public boolean alterar(Maquina entidade);

	public boolean excluir(long id);

	public List<Maquina> obterTodos();
}
