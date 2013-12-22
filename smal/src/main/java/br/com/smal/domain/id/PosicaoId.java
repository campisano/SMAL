package br.com.smal.domain.id;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
@SuppressWarnings("serial")
public class PosicaoId implements Serializable {

	private Long lab_id;
	private Integer fila_id;
	private Integer coluna_id;

	public PosicaoId() {
	}

	public PosicaoId(Long laboratorio, Integer fila, Integer coluna) {
		this.lab_id = laboratorio;
		this.fila_id = fila;
		this.coluna_id = coluna;
	}

	public Long getLab_id() {
		return lab_id;
	}

	public void setLab_id(Long lab_id) {
		this.lab_id = lab_id;
	}

	public Integer getFila_id() {
		return fila_id;
	}

	public void setFila_id(Integer fila_id) {
		this.fila_id = fila_id;
	}

	public Integer getColuna_id() {
		return coluna_id;
	}

	public void setColuna_id(Integer coluna_id) {
		this.coluna_id = coluna_id;
	}

	public int hashCode() {
		return lab_id.intValue() + fila_id.intValue() * 100
				+ coluna_id.intValue() * 10000;
	}

	public boolean equals(Object obj) {
		if (obj == this)
			return true;
		if (!(obj instanceof PosicaoId)) // include obj == null check
			return false;
		PosicaoId id = (PosicaoId) obj;
		return id.getLab_id().longValue() == lab_id.longValue()
				&& id.fila_id.intValue() == fila_id.intValue()
				&& id.coluna_id.intValue() == coluna_id.intValue();
	}
}
