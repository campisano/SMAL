package br.com.smal.domain.id;

import java.io.Serializable;

@SuppressWarnings("serial")
public class PosicaoId implements Serializable {

	private int fila;
	private int coluna;

	public PosicaoId() {
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

	public int hashCode() {
		return fila * 1000 + coluna;
	}

	public boolean equals(Object obj) {
		if (obj == this)
			return true;
		if (!(obj instanceof PosicaoId)) // include obj == null check
			return false;
		PosicaoId id = (PosicaoId) obj;
		return id.fila == fila && id.coluna == coluna;
	}
}