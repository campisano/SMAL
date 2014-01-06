package br.com.smal.presentation.subproblema.response;

import java.util.ArrayList;
import java.util.List;

import br.com.smal.domain.Subproblema;

@SuppressWarnings("serial")
public class ListarSubproblemasResponse
		extends
			ArrayList<ListarSubproblemasItemResponse> {

	public ListarSubproblemasResponse(List<Subproblema> subproblemas) {
		ListarSubproblemasItemResponse item;

		for (Subproblema subproblema : subproblemas) {
			item = new ListarSubproblemasItemResponse();
			item.setId(subproblema.getId());
			item.setNome(subproblema.getNome());
			item.setProblemaId(subproblema.getProblema().getId());
			add(item);
		}
	}
}
