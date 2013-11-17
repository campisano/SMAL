package br.com.smal.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "problema")
public class Problema {

	@Id
	@GeneratedValue
	private Long id;
	private String nome;

	@OneToMany
	@JoinColumn(name = "ID_PROBLEMA", referencedColumnName = "ID")
	private List<Subproblema> subproblemas = new ArrayList<Subproblema>();

	public List<Subproblema> getSubproblemas() {
		return subproblemas;
	}

	public void setSubproblemas(List<Subproblema> subproblemas) {
		this.subproblemas = subproblemas;
	}

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
}
