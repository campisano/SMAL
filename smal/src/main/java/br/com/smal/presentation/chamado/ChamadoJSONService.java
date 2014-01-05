package br.com.smal.presentation.chamado;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.smal.controller.ChamadoController;
import br.com.smal.domain.Chamado;
import br.com.smal.presentation.chamado.request.AbrirChamadoRequest;
import br.com.smal.presentation.chamado.request.DesignarChamadoRequest;
import br.com.smal.presentation.chamado.request.FecharChamadoRequest;
import br.com.smal.presentation.chamado.response.ListarChamadosResponse;
import br.com.smal.util.OperationResult;
import br.com.smal.util.OperationResultObject;
import br.com.smal.util.RespostaJSON;

@Component
@Path("/chamado")
public class ChamadoJSONService {
	@Autowired
	ChamadoController chamadoController;

	@Path("/abrirChamado")
	@POST
	@Consumes("application/json; charset=UTF-8")
	@Produces("application/json; charset=UTF-8")
	public RespostaJSON<Object> abrirChamado(AbrirChamadoRequest request) {
		try {

			OperationResult result = chamadoController.abrirChamado(
					request.getMatricula(), request.getMaquinaId(),
					request.getSubproblemaId(), request.getDescricao());

			if (result.isSuccess()) {
				return new RespostaJSON<Object>(true, "");
			} else {
				return new RespostaJSON<Object>(false, result.getMessage());
			}
		} catch (Exception ex) {
			return new RespostaJSON<Object>(false, "Erro:\n" + ex.getMessage());
		}
	}

	@Path("/listarChamados")
	@GET
	@Produces("application/json; charset=UTF-8")
	public RespostaJSON<Object> listarChamados() {
		try {
			OperationResultObject<List<Chamado>> result = chamadoController
					.listarChamados();

			if (result.isSuccess()) {
				return new RespostaJSON<Object>(true,
						new ListarChamadosResponse(result.getObject()));
			} else {
				return new RespostaJSON<Object>(false, result.getMessage());
			}

		} catch (Exception ex) {
			return new RespostaJSON<Object>(false, "Erro:\n" + ex.getMessage());
		}
	}

	@Path("/designarChamado")
	@POST
	@Consumes("application/json; charset=UTF-8")
	@Produces("application/json; charset=UTF-8")
	public RespostaJSON<Object> designarChamado(DesignarChamadoRequest request) {
		try {
			OperationResult result = chamadoController.designarChamado(
					request.getProtocolo(), request.getAtendenteId());

			if (result.isSuccess()) {
				return new RespostaJSON<Object>(true, "");
			} else {
				return new RespostaJSON<Object>(false, result.getMessage());
			}
		} catch (Exception ex) {
			return new RespostaJSON<Object>(false, "Erro:\n" + ex.getMessage());
		}
	}

	@Path("/fecharChamado")
	@POST
	@Consumes("application/json; charset=UTF-8")
	@Produces("application/json; charset=UTF-8")
	public RespostaJSON<Object> fecharChamado(FecharChamadoRequest request) {
		try {
			OperationResult result = chamadoController.fecharChamado(
					request.getProtocolo(), request.isExito());

			if (result.isSuccess()) {
				return new RespostaJSON<Object>(true, "");
			} else {
				return new RespostaJSON<Object>(false, result.getMessage());
			}
		} catch (Exception ex) {
			return new RespostaJSON<Object>(false, "Erro:\n" + ex.getMessage());
		}
	}
}
