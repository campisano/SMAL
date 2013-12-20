package br.com.smal.presentation;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.smal.persistence.TecnicoRepositorio;


@Component
@Path("/entrando")
public class FiltroService extends HttpServlet{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	
	@Autowired
	TecnicoRepositorio tecnicoRepositorio;
	
	
	@Path("/filtro")
	@POST
	@Consumes("application/json; charset=UTF-8")
	@Produces("application/json; charset=UTF-8")
	public RespostaJSON<Object> logar(@Context HttpServletRequest req){
		
		HttpSession session = req.getSession(true);
	
		
        Object 	valorSessao = session.getAttribute("autorizacao");	
		
		
		if(valorSessao!=null && valorSessao!="Nao Autorizado" ){
			
			System.out.println("Usuário Logado");
			String usuarioLogado = "Usuário ainda está logado..";
			
			return new RespostaJSON<Object>(true, usuarioLogado);
			
		}else{
			
				
				return new RespostaJSON<Object>(false, "Usuário Deslogado");
			}
			
		
		
	 
	}	



}
