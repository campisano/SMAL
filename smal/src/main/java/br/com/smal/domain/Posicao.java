package br.com.smal.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import br.com.smal.domain.id.PosicaoId;

@Entity
@IdClass(PosicaoId.class)
@Table(name = "posicao")
public class Posicao {

	@Id
	private int fila;
	@Id
	private int coluna;
	@OneToOne(optional = true)
	private Maquina maquina;
	@ManyToOne(optional = false)
	private Laboratorio laboratorio;

	public int getFila() {
		return fila;
	}

	public int getColuna() {
		return coluna;
	}

	public void setColuna(int coluna) {
		this.coluna = coluna;
	}

	public void setFila(int fila) {
		this.fila = fila;
	}

	public Maquina getMaquina() {
		return maquina;
	}

	public void setMaquina(Maquina maquina) {
		this.maquina = maquina;
	}

	public Laboratorio getLaboratorio() {
		return laboratorio;
	}

	public void setLaboratorio(Laboratorio laboratorio) {
		this.laboratorio = laboratorio;
	}
}
