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

	public void incluir(Usuario usuario) {
		usuarioDao.incluir(usuario);
	}
}
