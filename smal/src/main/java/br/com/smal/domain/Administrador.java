package br.com.smal.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "administrador")
public class Administrador {

	@Id
	@GeneratedValue
	private Long id;

	@OneToMany
	private List<Chamado> chamados = new ArrayList<Chamado>();

	private String nome;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public List<Chamado> getChamados() {
		return chamados;
	}

	public void addChamados(Chamado chamado) {
		this.chamados.add(chamado);
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
}
