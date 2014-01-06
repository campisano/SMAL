package br.com.smal.presentation.subproblema.response;

public class ListarSubproblemasItemResponse {

	private long id;
	private String nome;
	private long problemaId;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public long getProblemaId() {
		return problemaId;
	}

	public void setProblemaId(long problemaId) {
		this.problemaId = problemaId;
	}
}
