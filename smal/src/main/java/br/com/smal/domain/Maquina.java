package br.com.smal.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "maquina")
public class Maquina {

	@Id
	@GeneratedValue
	private Long id;
	private String patrimonio;
	@OneToOne(optional = false)
	private Posicao posicao;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPatrimonio() {
		return patrimonio;
	}

	public void setPatrimonio(String patrimonio) {
		this.patrimonio = patrimonio;
	}

	public Posicao getPosicao() {
		return posicao;
	}

	// from
	// http://en.wikibooks.org/wiki/Java_Persistence/OneToMany#Getters_and_Setters
	public void setPosicao(Posicao posicao) {
		if (this.posicao != posicao) {
			this.posicao = posicao;
		}

		if (posicao.getMaquina() != this) {
			posicao.setMaquina(this);
		}
	}
}
