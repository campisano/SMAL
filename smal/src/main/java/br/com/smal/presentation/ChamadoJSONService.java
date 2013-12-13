package br.com.smal.presentation;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.smal.domain.Chamado;
import br.com.smal.persistence.ChamadoRepositorio;

@Component
@Path("/chamado")
public class ChamadoJSONService {
	@Autowired
	ChamadoRepositorio chamadoRepositorio;

	@Path("/incluir")
	@POST
	@Consumes("application/json; charset=UTF-8")
	@Produces("application/json; charset=UTF-8")
	public RespostaJSON<Object> incluir(Chamado chamado) {
		try {
			if (chamado.getId() != null) {
				return new RespostaJSON<Object>(false,
						"Erro : para uma inclusão, id deve ser nulo.");
			}

			if (chamadoRepositorio.incluir(chamado)) {
				return new RespostaJSON<Object>(true, chamado);
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
	public RespostaJSON<Object> obter(Chamado chamado) {
		try {
			return new RespostaJSON<Object>(true,
					chamadoRepositorio.obter(chamado.getId()));
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
					chamadoRepositorio.obterTodos());
		} catch (Exception ex) {
			return new RespostaJSON<Object>(false, "Erro:\n" + ex.getMessage());
		}
	}

	@Path("/alterar")
	@POST
	@Consumes("application/json; charset=UTF-8")
	@Produces("application/json; charset=UTF-8")
	public RespostaJSON<Object> alterar(Chamado chamado) {
		try {
			Chamado chamado_salvo = chamadoRepositorio.obter(chamado.getId());

			if (chamado_salvo == null) {
				return new RespostaJSON<Object>(false, "chamado inexistente");
			}

			chamado_salvo.setDescricao(chamado.getDescricao());

			if (chamadoRepositorio.alterar(chamado_salvo)) {
				return new RespostaJSON<Object>(true, chamado_salvo);
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
	public RespostaJSON<Object> excluir(Chamado chamado) {
		try {
			if (chamadoRepositorio.excluir(chamado.getId())) {
				return new RespostaJSON<Object>(true, null);
			} else {
				return new RespostaJSON<Object>(false, "erro");
			}
		} catch (Exception ex) {
			return new RespostaJSON<Object>(false, "Erro:\n" + ex.getMessage());
		}
	}
}
