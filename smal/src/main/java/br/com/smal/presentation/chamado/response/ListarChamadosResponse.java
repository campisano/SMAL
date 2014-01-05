package br.com.smal.presentation.chamado.response;

import java.util.ArrayList;
import java.util.List;

import br.com.smal.domain.Chamado;

@SuppressWarnings("serial")
public class ListarChamadosResponse
		extends
			ArrayList<ListarChamadosItemResponse> {

	public ListarChamadosResponse(List<Chamado> chamados) {
		ListarChamadosItemResponse item;

		for (Chamado chamado : chamados) {
			item = new ListarChamadosItemResponse();
			item.setProtocolo(chamado.getProtocolo());
			item.setData_hora_abertura(chamado.getDataHoraAbertura());
			item.setData_hora_fechamento(chamado.getDataHoraFechamento());
			item.setDescricao(chamado.getDescricao());
			item.setAbridorId(chamado.getAbridor().getId());
			if (chamado.getAtendente() != null)
				item.setAtendenteId(chamado.getAtendente().getId());
			if (chamado.getDesignador() != null)
				item.setDesignadorId(chamado.getDesignador().getId());
			item.setStatus(chamado.getStatus().getValor());
			item.setSubproblemaId(chamado.getSubproblema().getId());
			item.setMaquinaId(chamado.getMaquina().getId());
			add(item);
		}
	}
}
