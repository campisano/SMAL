package br.com.smal.presentation.chamado.request;

public class DesignarChamadoRequest {
	private long id;
	private long tecnicoId;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getTecnicoId() {
		return tecnicoId;
	}
	public void setTecnicoId(long tecnicoId) {
		this.tecnicoId = tecnicoId;
	}
}
