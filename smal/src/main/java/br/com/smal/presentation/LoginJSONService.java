package br.com.smal.presentation;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.smal.domain.Administrador;
import br.com.smal.domain.Tecnico;
import br.com.smal.persistence.TecnicoRepositorio;
import br.com.smal.util.RespostaJSON;

@Component
@Path("/login")
public class LoginJSONService {

	@Autowired
	TecnicoRepositorio tecnicoRepositorio;

	@Path("/logar")
	@POST
	@Consumes("application/json; charset=UTF-8")
	@Produces("application/json; charset=UTF-8")
	public RespostaJSON<Object> logar(Tecnico usuario,
			@Context HttpServletRequest req) {

		if (usuario.getMatricula() != null
				&& !"".equals(usuario.getMatricula())) {
			if (usuario instanceof Tecnico) {
				if (tecnicoRepositorio.existe(usuario)) {
					req.getSession().setAttribute("autorizacao", "autorizado");
					return new RespostaJSON<Object>(true,
							tecnicoRepositorio.getTecnicoPorMatricula(usuario
									.getMatricula()));
				} else {
					return new RespostaJSON<Object>(false,
							"Usuário não existe!");
				}
			}

			if (usuario instanceof Administrador) {
				req.getSession().setAttribute("autorizacao", "autorizado");
				System.out.println("TESTE");
			} else {
				return new RespostaJSON<Object>(false, "Usuário não existe!");
			}
		}
		return null;
	}

	@Path("/logout")
	@POST
	@Consumes("application/json; charset=UTF-8")
	@Produces("application/json; charset=UTF-8")
	public RespostaJSON<Object> logout(Tecnico usuario,
			@Context HttpServletRequest req) {
		req.getSession().setAttribute("autorizacao", "Nao Autorizado");
		return new RespostaJSON<Object>(false, "Usuário Deslogado!");
	}
}
