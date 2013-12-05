package br.com.smal.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "administrador")
@DiscriminatorValue("administrador")
public class Administrador extends Tecnico {

	@OneToMany
	private List<Chamado> chamados = new ArrayList<Chamado>();


	public List<Chamado> getChamados() {
		return chamados;
	}

	public void addChamados(Chamado chamado) {
		this.chamados.add(chamado);
	}
}
