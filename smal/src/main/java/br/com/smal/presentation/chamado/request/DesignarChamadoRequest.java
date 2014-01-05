package br.com.smal.presentation.chamado.request;

public class DesignarChamadoRequest {
	private long protocolo;
	private long atendenteId;
	public long getProtocolo() {
		return protocolo;
	}
	public void setId(long protocolo) {
		this.protocolo = protocolo;
	}
	public long getAtendenteId() {
		return atendenteId;
	}
	public void setAtendenteId(long atendenteId) {
		this.atendenteId = atendenteId;
	}
}
