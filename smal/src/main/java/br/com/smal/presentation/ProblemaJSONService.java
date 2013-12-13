package br.com.smal.presentation;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.smal.domain.Problema;
import br.com.smal.persistence.ProblemaRepositorio;

@Component
@Path("/problema")
public class ProblemaJSONService {
	@Autowired
	ProblemaRepositorio problemaRepositorio;

	@Path("/incluir")
	@POST
	@Consumes("application/json; charset=UTF-8")
	@Produces("application/json; charset=UTF-8")
	public RespostaJSON<Object> incluir(Problema problema) {
		try {
			if (problema.getId() != null) {
				return new RespostaJSON<Object>(false,
						"Erro : para uma inclusão, id deve ser nulo.");
			}

			if (problemaRepositorio.incluir(problema)) {
				return new RespostaJSON<Object>(true, problema);
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
	public RespostaJSON<Object> obter(Problema problema) {
		try {
			return new RespostaJSON<Object>(true,
					problemaRepositorio.obter(problema.getId()));
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
					problemaRepositorio.obterTodos());
		} catch (Exception ex) {
			return new RespostaJSON<Object>(false, "Erro:\n" + ex.getMessage());
		}
	}

	@Path("/alterar")
	@POST
	@Consumes("application/json; charset=UTF-8")
	@Produces("application/json; charset=UTF-8")
	public RespostaJSON<Object> alterar(Problema problema) {
		try {
			Problema problema_salvo = problemaRepositorio.obter(problema
					.getId());

			if (problema_salvo == null) {
				return new RespostaJSON<Object>(false, "problema inexistente");
			}

			problema_salvo.setNome(problema.getNome());

			if (problemaRepositorio.alterar(problema_salvo)) {
				return new RespostaJSON<Object>(true, problema_salvo);
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
	public RespostaJSON<Object> excluir(Problema problema) {
		try {
			if (problemaRepositorio.excluir(problema.getId())) {
				return new RespostaJSON<Object>(true, null);
			} else {
				return new RespostaJSON<Object>(false, "erro");
			}
		} catch (Exception ex) {
			return new RespostaJSON<Object>(false, "Erro:\n" + ex.getMessage());
		}
	}
}
