package br.com.smal.presentation;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.smal.OperationResult;
import br.com.smal.OperationResultObject;
import br.com.smal.controller.LaboratorioController;
import br.com.smal.domain.Laboratorio;

@Component
@Path("/laboratorio")
public class LaboratorioJSONService {
	@Autowired
	LaboratorioController laboratorioController;

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

			OperationResult result = laboratorioController.incluir(laboratorio);

			if (result.isSuccess()) {
				return new RespostaJSON<Object>(true, laboratorio);
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
	public RespostaJSON<Object> obter(Laboratorio laboratorio) {
		try {
			OperationResultObject<Laboratorio> result = laboratorioController
					.obter(laboratorio.getId());

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
			OperationResultObject<List<Laboratorio>> result = laboratorioController
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
	public RespostaJSON<Object> alterar(Laboratorio laboratorio) {
		try {
			OperationResultObject<Laboratorio> result_obter = laboratorioController
					.obter(laboratorio.getId());

			if (!result_obter.isSuccess()) {
				return new RespostaJSON<Object>(false,
						result_obter.getMessage());
			}

			Laboratorio laboratorio_salvo = result_obter.getObject();
			laboratorio_salvo.setNome(laboratorio.getNome());

			OperationResult result = laboratorioController
					.alterar(laboratorio_salvo);

			if (result.isSuccess()) {
				return new RespostaJSON<Object>(true, laboratorio_salvo);
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
	public RespostaJSON<Object> excluir(Laboratorio laboratorio) {
		try {
			OperationResult result = laboratorioController.excluir(laboratorio
					.getId());

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
