package br.com.smal.presentation;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.smal.controller.MaquinaController;
import br.com.smal.domain.Maquina;
import br.com.smal.presentation.request.CadastrarMaquinaRequest;
import br.com.smal.presentation.request.ListarMaquinasRequest;
import br.com.smal.presentation.response.ListarMaquinasResponse;
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

	/*
	 * @Path("/incluir")
	 * 
	 * @POST
	 * 
	 * @Consumes("application/json; charset=UTF-8")
	 * 
	 * @Produces("application/json; charset=UTF-8") public RespostaJSON<Object>
	 * incluir(Maquina maquina) { try { if (maquina.getId() != null) { return
	 * new RespostaJSON<Object>(false,
	 * "Erro : para uma inclusão, id deve ser nulo."); }
	 * 
	 * if (maquinaRepositorio.incluir(maquina)) { return new
	 * RespostaJSON<Object>(true, maquina); } else { return new
	 * RespostaJSON<Object>(false, "Erro interno :("); } } catch (Exception ex)
	 * { return new RespostaJSON<Object>(false, "Erro:\n" + ex.getMessage()); }
	 * }
	 * 
	 * @Path("/obter")
	 * 
	 * @POST
	 * 
	 * @Consumes("application/json; charset=UTF-8")
	 * 
	 * @Produces("application/json; charset=UTF-8") public RespostaJSON<Object>
	 * obter(Maquina maquina) { try { return new RespostaJSON<Object>(true,
	 * maquinaRepositorio.obter(maquina.getId())); } catch (Exception ex) {
	 * return new RespostaJSON<Object>(false, "Erro:\n" + ex.getMessage()); } }
	 * 
	 * @Path("/obterTodos")
	 * 
	 * @GET
	 * 
	 * @Produces("application/json; charset=UTF-8") public RespostaJSON<Object>
	 * obterTodos() { try { return new RespostaJSON<Object>(true,
	 * maquinaRepositorio.obterTodos()); } catch (Exception ex) { return new
	 * RespostaJSON<Object>(false, "Erro:\n" + ex.getMessage()); } }
	 * 
	 * @Path("/alterar")
	 * 
	 * @POST
	 * 
	 * @Consumes("application/json; charset=UTF-8")
	 * 
	 * @Produces("application/json; charset=UTF-8") public RespostaJSON<Object>
	 * alterar(Maquina maquina) { try { Maquina maquina_salvo =
	 * maquinaRepositorio.obter(maquina.getId());
	 * 
	 * if (maquina_salvo == null) { return new RespostaJSON<Object>(false,
	 * "maquina inexistente"); }
	 * 
	 * maquina_salvo.setPatrimonio(maquina.getPatrimonio());
	 * 
	 * if (maquinaRepositorio.alterar(maquina_salvo)) { return new
	 * RespostaJSON<Object>(true, maquina_salvo); } else { return new
	 * RespostaJSON<Object>(false, "erro"); } } catch (Exception ex) { return
	 * new RespostaJSON<Object>(false, "Erro:\n" + ex.getMessage()); } }
	 * 
	 * @Path("/excluir")
	 * 
	 * @POST
	 * 
	 * @Consumes("application/json; charset=UTF-8")
	 * 
	 * @Produces("application/json; charset=UTF-8") public RespostaJSON<Object>
	 * excluir(Maquina maquina) { try { if
	 * (maquinaRepositorio.excluir(maquina.getId())) { return new
	 * RespostaJSON<Object>(true, null); } else { return new
	 * RespostaJSON<Object>(false, "erro"); } } catch (Exception ex) { return
	 * new RespostaJSON<Object>(false, "Erro:\n" + ex.getMessage()); } }
	 */
}
