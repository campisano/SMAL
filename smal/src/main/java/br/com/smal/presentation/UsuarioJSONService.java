package br.com.smal.presentation;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.smal.controller.UsuarioController;
import br.com.smal.domain.Usuario;
import br.com.smal.util.OperationResult;
import br.com.smal.util.OperationResultObject;
import br.com.smal.util.RespostaJSON;

@Component
@Path("/usuario")
public class UsuarioJSONService {
	@Autowired
	UsuarioController usuarioController;

	@Path("/incluir")
	@POST
	@Consumes("application/json; charset=UTF-8")
	@Produces("application/json; charset=UTF-8")
	public RespostaJSON<Object> incluir(Usuario usuario) {
		try {
			if (usuario.getId() != null) {
				return new RespostaJSON<Object>(false,
						"Erro : para uma inclusão, id deve ser nulo.");
			}

			OperationResult result = usuarioController.incluir(usuario);

			if (result.isSuccess()) {
				return new RespostaJSON<Object>(true, usuario);
			} else {
				return new RespostaJSON<Object>(false, result.getMessage());
			}
		} catch (Exception ex) {
			return new RespostaJSON<Object>(false, "Erro:\n" + ex.getMessage());
		}
	}

	@Path("/obter")
	@POST
	@Consumes("application/json; charset=UTF-8")
	@Produces("application/json; charset=UTF-8")
	public RespostaJSON<Object> obter(Usuario usuario) {
		try {
			OperationResultObject<Usuario> result = usuarioController
					.obter(usuario.getId());

			if (result.isSuccess()) {
				return new RespostaJSON<Object>(true, result.getObject());
			} else {
				return new RespostaJSON<Object>(false, result.getMessage());
			}
		} catch (Exception ex) {
			return new RespostaJSON<Object>(false, "Erro:\n" + ex.getMessage());
		}
	}

	@Path("/obterTodos")
	@GET
	@Produces("application/json; charset=UTF-8")
	public RespostaJSON<Object> obterTodos() {
		try {
			OperationResultObject<List<Usuario>> result = usuarioController
					.obterTodos();

			if (result.isSuccess()) {
				return new RespostaJSON<Object>(true, result.getObject());
			} else {
				return new RespostaJSON<Object>(false, result.getMessage());
			}
		} catch (Exception ex) {
			return new RespostaJSON<Object>(false, "Erro:\n" + ex.getMessage());
		}
	}

	@Path("/alterar")
	@POST
	@Consumes("application/json; charset=UTF-8")
	@Produces("application/json; charset=UTF-8")
	public RespostaJSON<Object> alterar(Usuario usuario) {
		try {
			OperationResult result = usuarioController.alterar(usuario);

			if (result.isSuccess()) {
				return new RespostaJSON<Object>(true, usuario);
			} else {
				return new RespostaJSON<Object>(false, result.getMessage());
			}
		} catch (Exception ex) {
			return new RespostaJSON<Object>(false, "Erro:\n" + ex.getMessage());
		}
	}

	@Path("/excluir")
	@POST
	@Consumes("application/json; charset=UTF-8")
	@Produces("application/json; charset=UTF-8")
	public RespostaJSON<Object> excluir(Usuario usuario) {
		try {
			OperationResult result = usuarioController.excluir(usuario.getId());

			if (result.isSuccess()) {
				return new RespostaJSON<Object>(true, null);
			} else {
				return new RespostaJSON<Object>(false, result.getMessage());
			}
		} catch (Exception ex) {
			return new RespostaJSON<Object>(false, "Erro:\n" + ex.getMessage());
		}
	}
}
