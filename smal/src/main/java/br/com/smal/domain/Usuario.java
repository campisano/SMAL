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

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

@Entity
@Table(name = "usuario")
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "usuario_nome_class")
public class Usuario {

	@Id
	@GeneratedValue
	private Long id;

	@OneToMany
	@LazyCollection(LazyCollectionOption.FALSE)
	private List<Chamado> chamados = new ArrayList<Chamado>();
	
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

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public List<Chamado> getChamados() {
		return chamados;
	}

	public void addChamados(Chamado chamado) {
		this.chamados.add(chamado);
	}



	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}


	
}
