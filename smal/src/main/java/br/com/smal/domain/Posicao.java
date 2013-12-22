package br.com.smal.domain;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import br.com.smal.domain.id.PosicaoId;

@Entity
@Table(name = "posicao")
public class Posicao {

	@EmbeddedId
	private PosicaoId id;

	@ManyToOne(optional = false, fetch = FetchType.EAGER)
	@JoinColumn(name = "lab_id", referencedColumnName = "id", insertable = false, updatable = false)
	private Laboratorio laboratorio;

	@OneToOne(optional = true)
	private Maquina maquina;

	public Posicao() {
		id = new PosicaoId();
	}

	public Posicao(Long laboratorio, Integer fila, Integer coluna) {
		id = new PosicaoId(laboratorio, fila, coluna);
	}

	public Laboratorio getLaboratorio() {
		return laboratorio;
	}

	public void setLaboratorio(Laboratorio laboratorio) {
		this.laboratorio = laboratorio;
	}

	public int getFila() {
		return id.getFila_id();
	}

	public void setFila(int fila) {
		id.setFila_id(fila);
	}

	public int getColuna() {
		return id.getColuna_id();
	}

	public void setColuna(int coluna) {
		id.setColuna_id(coluna);
	}

	public Maquina getMaquina() {
		return maquina;
	}

	public void setMaquina(Maquina maquina) {
		this.maquina = maquina;
	}
}
