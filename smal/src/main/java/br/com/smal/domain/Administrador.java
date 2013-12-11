package br.com.smal.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "administrador")
@DiscriminatorValue("administrador")
public class Administrador extends Tecnico {

	@OneToMany(mappedBy = "designador", fetch = FetchType.LAZY)
	private List<Chamado> designacoes = new ArrayList<Chamado>();

	public List<Chamado> getDesignacoes() {
		return designacoes;
	}

	public void setDesignacoes(List<Chamado> designacoes) {
		this.designacoes = designacoes;
	}
}
