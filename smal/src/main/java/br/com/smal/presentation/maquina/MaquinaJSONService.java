package br.com.smal.presentation.maquina;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.smal.controller.MaquinaController;
import br.com.smal.domain.Maquina;
import br.com.smal.presentation.maquina.request.AlterarMaquinaRequest;
import br.com.smal.presentation.maquina.request.CadastrarMaquinaRequest;
import br.com.smal.presentation.maquina.request.ExcluirMaquinaRequest;
import br.com.smal.presentation.maquina.request.ListarMaquinasRequest;
import br.com.smal.presentation.maquina.response.ListarMaquinasResponse;
import br.com.smal.util.OperationResult;
import br.com.smal.util.OperationResultObject;
import br.com.smal.util.RespostaJSON;

@Component
@Path("/maquina")
public class MaquinaJSONService {
	@Autowired
	MaquinaController maquinaController;

	@Path("/listarMaquinas")
	@POST
	@Consumes("application/json; charset=UTF-8")
	@Produces("application/json; charset=UTF-8")
	public RespostaJSON<Object> listarMaquinas(ListarMaquinasRequest request) {
		try {

			OperationResultObject<List<Maquina>> result = maquinaController
					.listarMaquinas(request.getLaboratorioId());

			if (result.isSuccess()) {
				return new RespostaJSON<Object>(true,
						new ListarMaquinasResponse(result.getObject()));
			} else {
				return new RespostaJSON<Object>(false, result.getMessage());
			}
		} catch (Exception ex) {
			return new RespostaJSON<Object>(false, "Erro:\n" + ex.getMessage());
		}
	}

	@Path("/cadastrarMaquina")
	@POST
	@Consumes("application/json; charset=UTF-8")
	@Produces("application/json; charset=UTF-8")
	public RespostaJSON<Object> cadastrarMaquina(CadastrarMaquinaRequest request) {
		try {

			OperationResult result = maquinaController.cadastrarMaquina(
					request.getPatrimonio(), request.getLaboratorioId(),
					request.getFila(), request.getColuna());
			if (result.isSuccess()) {
				return new RespostaJSON<Object>(true, null);
			} else {
				return new RespostaJSON<Object>(false, result.getMessage());
			}
		} catch (Exception ex) {
			return new RespostaJSON<Object>(false, "Erro:\n" + ex.getMessage());
		}
	}

	@Path("/alterarMaquina")
	@POST
	@Consumes("application/json; charset=UTF-8")
	@Produces("application/json; charset=UTF-8")
	public RespostaJSON<Object> alterarMaquina(AlterarMaquinaRequest request) {
		try {
			OperationResult result = maquinaController.alterarMaquina(
					request.getId(), request.getPatrimonio(),
					request.getLaboratorioId(), request.getFila(),
					request.getColuna());

			if (result.isSuccess()) {
				return new RespostaJSON<Object>(true, null);
			} else {
				return new RespostaJSON<Object>(false, result.getMessage());
			}
		} catch (Exception ex) {
			return new RespostaJSON<Object>(false, "Erro:\n" + ex.getMessage());
		}
	}

	@Path("/excluirMaquina")
	@POST
	@Consumes("application/json; charset=UTF-8")
	@Produces("application/json; charset=UTF-8")
	public RespostaJSON<Object> excluirMaquina(ExcluirMaquinaRequest request) {
		try {
			OperationResult result = maquinaController.excluirMaquina(request
					.getMaquinaId());

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
