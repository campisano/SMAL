package br.com.smal.presentation.laboratorio.response;

import java.util.ArrayList;
import java.util.List;

import br.com.smal.domain.Laboratorio;

@SuppressWarnings("serial")
public class ListarLaboratoriosResponse
		extends
			ArrayList<ListarLaboratoriosItemResponse> {

	public ListarLaboratoriosResponse(List<Laboratorio> laboratorios) {
		ListarLaboratoriosItemResponse item;

		for (Laboratorio laboratorio : laboratorios) {
			item = new ListarLaboratoriosItemResponse();
			item.setId(laboratorio.getId());
			item.setNome(laboratorio.getNome());
			add(item);
		}
	}
}
