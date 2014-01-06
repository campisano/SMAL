package br.com.smal.presentation.subproblema;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.smal.controller.SubproblemaController;
import br.com.smal.domain.Subproblema;
import br.com.smal.presentation.subproblema.request.AlterarSubproblemaRequest;
import br.com.smal.presentation.subproblema.request.CadastrarSubproblemaRequest;
import br.com.smal.presentation.subproblema.request.ExcluirSubproblemaRequest;
import br.com.smal.presentation.subproblema.response.ListarSubproblemasResponse;
import br.com.smal.util.OperationResult;
import br.com.smal.util.OperationResultObject;
import br.com.smal.util.RespostaJSON;

@Component
@Path("/subproblema")
public class SubproblemaJSONService {
	@Autowired
	SubproblemaController subproblemaController;

	@Path("/cadastrarSubproblema")
	@POST
	@Consumes("application/json; charset=UTF-8")
	@Produces("application/json; charset=UTF-8")
	public RespostaJSON<Object> cadastrarSubproblema(
			CadastrarSubproblemaRequest request) {
		try {
			OperationResult result = subproblemaController
					.cadastrarSubproblema(request.getNome(),
							request.getProblemaId());

			if (result.isSuccess()) {
				return new RespostaJSON<Object>(true, "");
			} else {
				return new RespostaJSON<Object>(false, result.getMessage());
			}
		} catch (Exception ex) {
			return new RespostaJSON<Object>(false, "Erro:\n" + ex.getMessage());
		}
	}

	@Path("/listarSubproblemas")
	@GET
	@Produces("application/json; charset=UTF-8")
	public RespostaJSON<Object> listarSubproblemas() {
		try {
			OperationResultObject<List<Subproblema>> result = subproblemaController
					.listarSubproblemas();

			if (result.isSuccess()) {
				return new RespostaJSON<Object>(true,
						new ListarSubproblemasResponse(result.getObject()));
			} else {
				return new RespostaJSON<Object>(false, result.getMessage());
			}
		} catch (Exception ex) {
			return new RespostaJSON<Object>(false, "Erro:\n" + ex.getMessage());
		}
	}

	@Path("/alterarSubproblema")
	@POST
	@Consumes("application/json; charset=UTF-8")
	@Produces("application/json; charset=UTF-8")
	public RespostaJSON<Object> alterarSubproblema(
			AlterarSubproblemaRequest request) {
		try {
			OperationResult result = subproblemaController
					.alterarSubproblema(request.getId(), request.getNome(),
							request.getProblemaId());

			if (result.isSuccess()) {
				return new RespostaJSON<Object>(true, null);
			} else {
				return new RespostaJSON<Object>(false, result.getMessage());
			}
		} catch (Exception ex) {
			return new RespostaJSON<Object>(false, "Erro:\n" + ex.getMessage());
		}
	}

	@Path("/excluirSubproblema")
	@POST
	@Consumes("application/json; charset=UTF-8")
	@Produces("application/json; charset=UTF-8")
	public RespostaJSON<Object> excluirSubproblema(
			ExcluirSubproblemaRequest request) {
		try {
			OperationResult result = subproblemaController
					.excluirSubproblema(request.getId());

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
