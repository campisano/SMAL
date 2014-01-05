package br.com.smal.presentation.chamado.request;

public class FecharChamadoRequest {
	private long id;
	private boolean exito;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public boolean isExito() {
		return exito;
	}
	public void setExito(boolean exito) {
		this.exito = exito;
	}
}
