package br.com.smal.presentation.problema.response;

import java.util.ArrayList;
import java.util.List;

import br.com.smal.domain.Problema;

@SuppressWarnings("serial")
public class ListarProblemasResponse
		extends
			ArrayList<ListarProblemasItemResponse> {

	public ListarProblemasResponse(List<Problema> problemas) {
		ListarProblemasItemResponse item;

		for (Problema problema : problemas) {
			item = new ListarProblemasItemResponse();
			item.setId(problema.getId());
			item.setNome(problema.getNome());
			add(item);
		}
	}
}
