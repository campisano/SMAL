package br.com.smal.presentation;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.smal.domain.Laboratorio;
import br.com.smal.persistence.LaboratorioRepositorio;

@Component
@Path("/laboratorio")
public class LaboratorioJSONService {
	@Autowired
	LaboratorioRepositorio laboratorioRepositorio;

	@Path("/incluir")
	@POST
	@Consumes("application/json; charset=UTF-8")
	@Produces("application/json; charset=UTF-8")
	public RespostaJSON<Object> incluir(Laboratorio laboratorio) {
		try {
			
			if (laboratorio.getId() != null) {
				return new RespostaJSON<Object>(false,
						"Erro : para uma inclusão, id deve ser nulo.");
			}

			if (laboratorioRepositorio.incluir(laboratorio)) {
				return new RespostaJSON<Object>(true, laboratorio);
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
	public RespostaJSON<Object> obter(Laboratorio laboratorio) {
		try {
			return new RespostaJSON<Object>(true,
					laboratorioRepositorio.obter(laboratorio.getId()));
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
					laboratorioRepositorio.obterTodos());
		} catch (Exception ex) {
			return new RespostaJSON<Object>(false, "Erro:\n" + ex.getMessage());
		}
	}

	@Path("/alterar")
	@POST
	@Consumes("application/json; charset=UTF-8")
	@Produces("application/json; charset=UTF-8")
	public RespostaJSON<Object> alterar(Laboratorio laboratorio) {
		try {
			Laboratorio laboratorio_salvo = laboratorioRepositorio
					.obter(laboratorio.getId().longValue());

			if (laboratorio_salvo == null) {
				return new RespostaJSON<Object>(false,
						"laboratorio inexistente");
			}

			laboratorio_salvo.setNome(laboratorio.getNome());

			if (laboratorioRepositorio.alterar(laboratorio_salvo)) {
				return new RespostaJSON<Object>(true, laboratorio_salvo);
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
	public RespostaJSON<Object> excluir(Laboratorio laboratorio) {
		try {
			if (laboratorioRepositorio.excluir(laboratorio.getId().longValue())) {
				return new RespostaJSON<Object>(true, null);
			} else {
				return new RespostaJSON<Object>(false, "erro");
			}
		} catch (Exception ex) {
			return new RespostaJSON<Object>(false, "Erro:\n" + ex.getMessage());
		}
	}
}
