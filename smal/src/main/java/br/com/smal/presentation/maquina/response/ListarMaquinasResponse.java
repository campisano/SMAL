package br.com.smal.presentation.maquina.response;

import java.util.ArrayList;
import java.util.List;

import br.com.smal.domain.Maquina;

@SuppressWarnings("serial")
public class ListarMaquinasResponse extends
		ArrayList<ListarMaquinasItemResponse> {

	public ListarMaquinasResponse(List<Maquina> maquinas) {
		ListarMaquinasItemResponse item;

		for (Maquina maquina : maquinas) {
			item = new ListarMaquinasItemResponse();
			item.setId(maquina.getId());
			item.setPatrimonio(maquina.getPatrimonio());
			item.setLaboratorioId(maquina.getPosicao().getLaboratorio().getId());
			item.setFila(maquina.getPosicao().getFila());
			item.setColuna(maquina.getPosicao().getColuna());
			add(item);
		}
	}
}
