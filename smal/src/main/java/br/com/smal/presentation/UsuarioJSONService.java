package br.com.smal.presentation;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.smal.domain.Usuario;
import br.com.smal.persistence.UsuarioRepositorio;

@Component
@Path("/usuario")
public class UsuarioJSONService {
	@Autowired
	UsuarioRepositorio usuarioRepoitorio;

	@Path("/obterTodos")
	@GET
	@Produces("application/json; charset=UTF-8")
	public List<Usuario> obterTodos() {
		return usuarioRepoitorio.obterTodos();
	}
}