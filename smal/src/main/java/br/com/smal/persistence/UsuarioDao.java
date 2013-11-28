package br.com.smal.persistence;

import java.util.List;

import br.com.smal.domain.Laboratorio;
import br.com.smal.domain.Usuario;

public interface UsuarioDao {
	public boolean incluir(Usuario entidade);

	public List<Usuario> obterTodos();
	public Usuario obter(long id);

	public boolean alterar(Usuario entidade);

	public boolean excluir(long id);
}
