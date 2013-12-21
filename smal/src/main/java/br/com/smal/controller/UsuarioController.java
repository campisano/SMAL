package br.com.smal.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.smal.domain.Usuario;
import br.com.smal.persistence.UsuarioRepositorio;
import br.com.smal.util.OperationResult;
import br.com.smal.util.OperationResultObject;

@Component
public class UsuarioController {
	@Autowired
	UsuarioRepositorio usuarioRepositorio;

	public OperationResult incluir(Usuario usuario) {

		List<Usuario> list = usuarioRepositorio.obterTodos();

		for (Usuario user : list) {
			if (user.getMatricula().equals(usuario.getMatricula())) {
				return new OperationResult(false, "Matricula existente.");
			}
		}

		if (usuarioRepositorio.incluir(usuario)) {
			return new OperationResult(true, "");
		} else {
			return new OperationResult(false, "Erro interno.");
		}
	}

	public OperationResultObject<Usuario> obter(long id) {
		Usuario user = usuarioRepositorio.obter(id);

		if (user != null) {
			return new OperationResultObject<Usuario>(true, "", user);
		} else {
			return new OperationResultObject<Usuario>(false, "Erro interno.",
					null);
		}
	}

	public OperationResultObject<List<Usuario>> obterTodos() {
		List<Usuario> users = usuarioRepositorio.obterTodos();

		if (users != null) {
			return new OperationResultObject<List<Usuario>>(true, "", users);
		} else {
			return new OperationResultObject<List<Usuario>>(false,
					"Erro interno.", null);
		}
	}

	public OperationResult alterar(Usuario usuario) {
		Usuario usuario_salvo = usuarioRepositorio.obter(usuario.getId());

		if (usuario_salvo == null) {
			return new OperationResult(false, "Erro interno.");
		}

		usuario_salvo.setNome(usuario.getNome());
		usuario_salvo.setMatricula(usuario.getMatricula());

		if (usuarioRepositorio.alterar(usuario_salvo)) {
			return new OperationResult(true, "");
		} else {
			return new OperationResult(false, "Erro interno.");
		}
	}

	public OperationResult excluir(long id) {
		if (usuarioRepositorio.excluir(id)) {
			return new OperationResult(true, "");
		} else {
			return new OperationResult(false, "Erro interno.");
		}
	}
}
