package br.com.smal.presentation;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.smal.domain.Usuario;
import br.com.smal.persistence.UsuarioRepositorio;

@Component
@Path("/usuario")
public class UsuarioJSONService {
	@Autowired
	UsuarioRepositorio usuarioRepositorio;
	@Path("/incluir")
	@POST
	@Consumes("application/json; charset=UTF-8")
	@Produces("application/json; charset=UTF-8")
	public RespostaJSON<Object> incluir(Usuario usuario , @Context HttpServletRequest req ) {
		try {
			if (usuario.getId() != null) {
				return new RespostaJSON<Object>(false,
						"Erro : para uma inclusão, id deve ser nulo.");
			}

			if (usuarioRepositorio.incluir(usuario)) {
				return new RespostaJSON<Object>(true, usuario);
				
			} else {
				return new RespostaJSON<Object>(false, "Erro interno :(");
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
			return new RespostaJSON<Object>(true,
					usuarioRepositorio.obter(usuario.getId()));
		} catch (Exception ex) {
			return new RespostaJSON<Object>(false, "Erro:\n" + ex.getMessage());
		}
	}

	@Path("/obterTodos")
	@GET
	@Produces("application/json; charset=UTF-8")
	public RespostaJSON<Object> obterTodos() {
		try {
			return new RespostaJSON<Object>(true,
					usuarioRepositorio.obterTodos());
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
			Usuario usuario_salvo = usuarioRepositorio
					.obter(usuario.getId().longValue());

			if (usuario_salvo == null) {
				return new RespostaJSON<Object>(false,
						"laboratorio inexistente");
			}

			usuario_salvo.setNome(usuario.getNome());
			usuario_salvo.setMatricula(usuario.getMatricula());

			if (usuarioRepositorio.alterar(usuario_salvo)) {
				return new RespostaJSON<Object>(true, usuario_salvo);
			} else {
				return new RespostaJSON<Object>(false, "erro");
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
			if (usuarioRepositorio.excluir(usuario.getId().longValue())) {
				return new RespostaJSON<Object>(true, null);
			} else {
				return new RespostaJSON<Object>(false, "erro");
			}
		} catch (Exception ex) {
			return new RespostaJSON<Object>(false, "Erro:\n" + ex.getMessage());
		}
	}
}















