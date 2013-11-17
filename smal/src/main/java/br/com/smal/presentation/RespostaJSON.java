package br.com.smal.presentation;

public class RespostaJSON<T> {

	private boolean sucesso;

	private T mensagem;

	public RespostaJSON(boolean sucesso, T mensagem) {
		this.sucesso = sucesso;
		this.mensagem = mensagem;
	}

	public boolean isSucesso() {
		return sucesso;
	}

	public T getMensagem() {
		return mensagem;
	}
}
