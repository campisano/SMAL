package br.com.smal.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "laboratorio")
public class Laboratorio {

	@Id
	@GeneratedValue
	private Long id;
	private String nome;
	@OneToMany(mappedBy = "laboratorio", fetch = FetchType.LAZY)
	private List<Posicao> posicoes = new ArrayList<Posicao>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public List<Posicao> getPosicoes() {
		return posicoes;
	}

	public void setPosicao(List<Posicao> posicoes) {
		this.posicoes = posicoes;
	}
}
