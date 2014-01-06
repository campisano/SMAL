package br.com.smal.presentation.login;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.smal.domain.Tecnico;
import br.com.smal.persistence.AdministradorRepositorio;
import br.com.smal.persistence.TecnicoRepositorio;
import br.com.smal.presentation.login.request.LoginRequest;
import br.com.smal.util.RespostaJSON;

@Component
@Path("/login")
public class LoginJSONService {

	@Autowired
	TecnicoRepositorio tecnicoRepositorio;
	
	@Autowired
	AdministradorRepositorio administradorRepositorio;

	@Path("/logar")
	@POST
	@Consumes("application/json; charset=UTF-8")
	@Produces("application/json; charset=UTF-8")
	public RespostaJSON<Object> logar(LoginRequest login,
			@Context HttpServletRequest req) {

		if (login.getMatricula() != null && !"".equals(login.getSenha())) {
	
			if (administradorRepositorio.existeMatriculaSenha(login.getMatricula(),login.getSenha())) {
				
				req.getSession().setAttribute("autorizacao", "autorizado");
				
				return new RespostaJSON<Object>(true,administradorRepositorio.getAdministradorPorMatricula(login.getMatricula()));
				
				
			}else{
				
				if (tecnicoRepositorio.existeMatriculaSenha(login.getMatricula(),login.getSenha())) {
				
					req.getSession().setAttribute("autorizacao", "autorizado");
					
					return new RespostaJSON<Object>(true,tecnicoRepositorio.getTecnicoPorMatricula(login.getMatricula()));
				
				} else {
					
					return new RespostaJSON<Object>(false,"não existem dados Cadastrados!");
				
				}
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
