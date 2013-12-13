package br.com.smal.persistence;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.smal.domain.Usuario;

@Component
public class UsuarioRepositorio {

	@Autowired
	UsuarioDao usuarioDao;

	public List<Usuario> obterTodos() {
		return usuarioDao.obterTodos();
	}

	public Usuario obter(long id) {
		return usuarioDao.obter(id);
	}

	public boolean alterar(Usuario entidade) {
		return usuarioDao.alterar(entidade);
	}

	public boolean excluir(long id) {
		return usuarioDao.excluir(id);
	}

	public boolean incluir(Usuario usuario) {
		return usuarioDao.incluir(usuario);
	}
}
