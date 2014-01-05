package br.com.smal.presentation.usuario.response;

import java.util.ArrayList;
import java.util.List;

import br.com.smal.domain.Administrador;
import br.com.smal.domain.Tecnico;
import br.com.smal.domain.Usuario;

@SuppressWarnings("serial")
public class ObterTodosResponse extends ArrayList<ObterTodosItemResponse> {

	public ObterTodosResponse(List<Usuario> usuarios) {
		ObterTodosItemResponse item;

		for (Usuario usuario : usuarios) {
			item = new ObterTodosItemResponse();
			item.setId(usuario.getId());
			item.setMatricula(usuario.getMatricula());
			item.setNome(usuario.getNome());
			if (usuario.getClass() == Administrador.class)
				item.setTipo(1);
			else if (usuario.getClass() == Tecnico.class)
				item.setTipo(2);
			add(item);
		}
	}
}
