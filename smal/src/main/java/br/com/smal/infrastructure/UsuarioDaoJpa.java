package br.com.smal.infrastructure;

import java.util.List;

import org.springframework.stereotype.Component;

import br.com.smal.domain.Usuario;
import br.com.smal.persistence.UsuarioDao;

@Component
public class UsuarioDaoJpa extends GenericDaoJpa<Usuario> implements UsuarioDao {

	public List<Usuario> obterTodos() {
		return obterTodos(Usuario.class);
	}

	public boolean excluir(long id) {
		return excluir(Usuario.class, id);
	}

	@Override
	public Usuario obter(long id) {
		return obterPorId(Usuario.class, id);
	}

}