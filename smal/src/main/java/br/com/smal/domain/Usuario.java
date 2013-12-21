package br.com.smal.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnore;

@Entity
@Table(name = "usuario")
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "usuario_nome_class")
public class Usuario {

	@Id
	@GeneratedValue
	private Long id;
	private String nome;
	private String matricula;
	@OneToMany(mappedBy = "abridor", fetch = FetchType.LAZY)
	private List<Chamado> aberturas = new ArrayList<Chamado>();

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

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	@JsonIgnore
	public List<Chamado> getAberturas() {
		return aberturas;
	}

	public void setAberturas(List<Chamado> aberturas) {
		this.aberturas = aberturas;
	}
}
