package br.com.smal.domain;

//import java.util.ArrayList;
//import java.util.List;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
//import javax.persistence.FetchType;
//import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "tecnico")
@DiscriminatorValue("tecnico")
public class Tecnico extends Usuario {

	private String senha;
	//@OneToMany(mappedBy = "atendente", fetch = FetchType.LAZY)
	//private List<Chamado> atendimentos = new ArrayList<Chamado>();

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	//public List<Chamado> getAtendimentos() {
	//	return atendimentos;
	//}

	//public void setAtendimentos(List<Chamado> atendimentos) {
	//	this.atendimentos = atendimentos;
	//}
}
