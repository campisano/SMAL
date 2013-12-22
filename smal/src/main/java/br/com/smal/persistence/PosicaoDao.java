package br.com.smal.persistence;

import java.util.List;

import br.com.smal.domain.Posicao;

public interface PosicaoDao {
	public boolean incluir(Posicao entidade);

	public Posicao obter(Long laboratorio, int fila, int coluna);

	public boolean alterar(Posicao entidade);

	public boolean excluir(Long laboratorio, int fila, int coluna);

	public List<Posicao> obterTodos();
}
