package br.com.smal.presentation.chamado.request;

public class FecharChamadoRequest {
	private long protocolo;
	private boolean exito;
	public long getProtocolo() {
		return protocolo;
	}
	public void setProtocolo(long protocolo) {
		this.protocolo = protocolo;
	}
	public boolean isExito() {
		return exito;
	}
	public void setExito(boolean exito) {
		this.exito = exito;
	}
}
