package br.com.smal.infrastructure;

import java.util.List;

import br.com.smal.domain.Administrador;
import br.com.smal.domain.Tecnico;

public interface AdministradorDao {
	

	public boolean incluir(Administrador entidade);

	public Tecnico obter(long id);

	public boolean alterar(Administrador entidade);

	public boolean excluir(long id);
	
	public boolean existe(Administrador entidade);

	public boolean existeMatriculaSenha(String matricula, String senha);
	
	public List<Administrador> obterTodos();
	
	public Tecnico getAdministradorPorMatricula(String matricula);
	


}
