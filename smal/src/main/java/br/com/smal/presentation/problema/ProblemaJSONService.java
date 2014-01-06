package br.com.smal.presentation.problema;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.smal.controller.ProblemaController;
import br.com.smal.domain.Problema;
import br.com.smal.presentation.problema.response.ListarProblemasResponse;
import br.com.smal.util.OperationResult;
import br.com.smal.util.OperationResultObject;
import br.com.smal.util.RespostaJSON;

@Component
@Path("/problema")
public class ProblemaJSONService {
	@Autowired
	ProblemaController problemaController;

	@Path("/cadastrarProblema")
	@POST
	@Consumes("application/json; charset=UTF-8")
	@Produces("application/json; charset=UTF-8")
	public RespostaJSON<Object> cadastrarProblema(Problema problema) {
		try {
			OperationResult result = problemaController
					.cadastrarProblema(problema.getNome());

			if (result.isSuccess()) {
				return new RespostaJSON<Object>(true, problema);
			} else {
				return new RespostaJSON<Object>(false, result.getMessage());
			}
		} catch (Exception ex) {
			return new RespostaJSON<Object>(false, "Erro:\n" + ex.getMessage());
		}
	}

	@Path("/listarProblemas")
	@GET
	@Produces("application/json; charset=UTF-8")
	public RespostaJSON<Object> listarProblemas() {
		try {
			OperationResultObject<List<Problema>> result = problemaController
					.listarProblemas();

			if (result.isSuccess()) {
				return new RespostaJSON<Object>(true,
						new ListarProblemasResponse(result.getObject()));
			} else {
				return new RespostaJSON<Object>(false, result.getMessage());
			}
		} catch (Exception ex) {
			return new RespostaJSON<Object>(false, "Erro:\n" + ex.getMessage());
		}
	}

	@Path("/alterarProblema")
	@POST
	@Consumes("application/json; charset=UTF-8")
	@Produces("application/json; charset=UTF-8")
	public RespostaJSON<Object> alterarProblema(Problema problema) {
		try {
			OperationResult result = problemaController.alterarProblema(
					problema.getId(), problema.getNome());

			if (result.isSuccess()) {
				return new RespostaJSON<Object>(true, null);
			} else {
				return new RespostaJSON<Object>(false, result.getMessage());
			}
		} catch (Exception ex) {
			return new RespostaJSON<Object>(false, "Erro:\n" + ex.getMessage());
		}
	}

	@Path("/excluirProblema")
	@POST
	@Consumes("application/json; charset=UTF-8")
	@Produces("application/json; charset=UTF-8")
	public RespostaJSON<Object> excluirProblema(Problema problema) {
		try {
			OperationResult result = problemaController
					.excluirProblema(problema.getId());

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
