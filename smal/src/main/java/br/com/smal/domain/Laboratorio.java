package br.com.smal.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

@Entity
public class Laboratorio {
	
    @Id
    @GeneratedValue
	private Long id;
	public List<Posicao> getPosicao() {
		return posicao;
	}

	public void addPosicao(Posicao posicao) {
		this.posicao.add(posicao);
	}

	private String nome;

	@OneToMany
	@JoinColumn(name="ID_LABORATORIO" , referencedColumnName="ID")
	private List<Posicao> posicao = new ArrayList<Posicao>();
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
}
