package br.com.smal.domain;

//TEste 2 
public enum Status {

	ABERTO(1), EM_ANDAMENTO(2), RESOLVIDO(3), NAO_RESOLVIDO(4);

	private final int valor;

	Status(int valor) {
		this.valor = valor;
	}

	public int getValor() {
		return valor;
	}
}
