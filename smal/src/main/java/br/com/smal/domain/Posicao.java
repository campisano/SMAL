package br.com.smal.domain;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "posicao")
public class Posicao {

	@Id
	@GeneratedValue
	private Long id;
	private int fila;
	private int coluna;

	@ManyToOne(optional = false, fetch = FetchType.EAGER)
	@JoinColumn(name = "laboratorio_fk")
	private Laboratorio laboratorio;

	@OneToOne(mappedBy = "posicao", optional = true)
	private Maquina maquina;

	public Posicao() {
	}

	public Posicao(Laboratorio laboratorio, Integer fila, Integer coluna) {
		setLaboratorio(laboratorio);
		this.fila = fila;
		this.coluna = coluna;
	}

	public Laboratorio getLaboratorio() {
		return laboratorio;
	}

	// from
	// http://en.wikibooks.org/wiki/Java_Persistence/OneToMany#Getters_and_Setters
	public void setLaboratorio(Laboratorio laboratorio) {
		if (this.laboratorio != laboratorio) {
			this.laboratorio = laboratorio;
		}

		if (!laboratorio.getPosicoes().contains(this)) {
			laboratorio.addPosicao(this);
		}
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

	public Maquina getMaquina() {
		return maquina;
	}

	// from
	// http://en.wikibooks.org/wiki/Java_Persistence/OneToMany#Getters_and_Setters
	public void setMaquina(Maquina maquina) {
		if (this.maquina != maquina) {
			this.maquina = maquina;
		}

		if (maquina.getPosicao() != this) {
			maquina.setPosicao(this);
		}
	}
}
