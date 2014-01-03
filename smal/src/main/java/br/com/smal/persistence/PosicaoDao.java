package br.com.smal.persistence;

import java.util.List;

import br.com.smal.domain.Posicao;

public interface PosicaoDao {
	public boolean incluir(Posicao entidade);

	public Posicao obter(long posicao);

	public boolean alterar(Posicao entidade);

	public boolean excluir(long posicao);

	public List<Posicao> obterTodos();
}
