package br.com.smal.persistence;

import java.util.List;

import br.com.smal.domain.Tecnico;

public interface TecnicoDao {

	public boolean incluir(Tecnico entidade);

	public Tecnico obter(long id);

	public boolean alterar(Tecnico entidade);

	public boolean excluir(long id);
	
	public boolean existe(Tecnico entidade);

	public List<Tecnico> obterTodos();
	
	public Tecnico getTecnicoPorMatricula(String matricula);
	
}
