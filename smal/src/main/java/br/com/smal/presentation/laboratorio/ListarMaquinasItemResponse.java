package br.com.smal.presentation.laboratorio;

public class ListarMaquinasItemResponse {

	private long id;
	private String patrimonio;
	private long laboratorioId;
	private int fila;
	private int coluna;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getPatrimonio() {
		return patrimonio;
	}

	public void setPatrimonio(String patrimonio) {
		this.patrimonio = patrimonio;
	}

	public long getLaboratorioId() {
		return laboratorioId;
	}

	public void setLaboratorioId(long laboratorioId) {
		this.laboratorioId = laboratorioId;
	}

	public int getFila() {
		return fila;
	}

	public void setFila(int fila) {
		this.fila = fila;
	}

	public int getColuna() {
		return coluna;
	}

	public void setColuna(int coluna) {
		this.coluna = coluna;
	}
}
